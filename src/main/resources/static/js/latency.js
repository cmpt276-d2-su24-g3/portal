import { clamp, range } from "./utils.js";


const LATENCY_THRESHOLD = 300;

mapboxgl.accessToken =
  "pk.eyJ1IjoiYmhhdmppdGNoYXVoYW4iLCJhIjoiY2x5MG95ejEzMGhuMDJtb2tvb3RpZHMyMiJ9.fJafGFJkITooEewonltjGw";


const regions = await fetch("/data/regions.json").then((res) => res.json());

const map = new mapboxgl.Map({
  container: "map",
  // TODO: Create a custom style
  style: "mapbox://styles/mapbox/light-v11",
  center: [0, 90],
  zoom: 3,
});

const geolocate = new mapboxgl.GeolocateControl({
  fitBoundsOptions: {
    maxZoom: 3,
  },
});


map.addControl(geolocate);

addRegionMarkers(regions, map);


map.on("load", () => {
  // TODO: Fall back to a geoip service if geolocation fails
  geolocate.trigger();
});

geolocate.on("geolocate", (e) => {
  const { coords: center } = e;

  addRegionLines(regions, center, map);
});


function createRegionLinesSource(regions, center) {
  return regions.map((region) => {
    let centerLongitude = center.longitude;
    // https://docs.mapbox.com/mapbox-gl-js/example/line-across-180th-meridian/
    if (Math.abs(center.longitude - region.longitude) > 180)
      centerLongitude += 360;

    return {
      type: "Feature",
      geometry: {
        type: "LineString",
        coordinates: [
          [centerLongitude, center.latitude],
          [region.longitude, region.latitude],
        ],
      },
      properties: {
        region: region.code,
      },
    };
  });
}

function addRegionMarkers(regions, map) {
  return regions.map((region) => {
    const { name, longitude, latitude } = region;

    // TODO: Use custom markers
    return new mapboxgl.Marker()
      .setLngLat([longitude, latitude])
      .setPopup(new mapboxgl.Popup().setHTML(name))
      .addTo(map);
  });
}

function addRegionLines(regions, center, map) {
  for (const region of createRegionLinesSource(regions, center)) {
    map.addSource(region.properties.region, {
      type: "geojson",
      data: region,
    });
    map.addLayer({
      id: region.properties.region,
      source: region.properties.region,
      type: "line",
      paint: {
        "line-color": "#888",
        "line-width": 0.5,
        "line-dasharray": [4, 4],
      },
    });
  }
}

async function getRegionLatency(region) {
  // TODO: Use our own endpoints?
  const url = `https://dynamodb.${region.code}.amazonaws.com`

  const now = performance.now();
  await fetch(url)
  const latency = performance.now() - now;

  return latency;
}

async function pingRegions(regions, map) {
  // TODO: Use Promise.allSettled to handle errors
  // TODO: Sort by distance?
  const latencies = await Promise.all(regions.map(region =>
    getRegionLatency(region).then(latency => {
      const color = `hsl(${range(clamp(latency, 0, LATENCY_THRESHOLD), 0, LATENCY_THRESHOLD, 120, 0)}, 100%, 50%)`;
      const width = range(clamp(latency, 0, LATENCY_THRESHOLD), 0, LATENCY_THRESHOLD, 3, 0.5);

      map.setPaintProperty(region.code, "line-color", color);
      map.setPaintProperty(region.code, "line-width", width);
      map.setPaintProperty(region.code, "line-dasharray", []);

      // TODO: Update markers?

      return { region: region.code, latency };
    })
  ))

  return latencies.sort((a, b) => a.latency - b.latency);
}

// TODO: Attach to a proper user interaction
addEventListener("click", async () => {
  console.log(await pingRegions(regions, map))
});
