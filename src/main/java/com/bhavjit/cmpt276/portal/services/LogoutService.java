package com.bhavjit.cmpt276.portal.services;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Sends a request to Cognito to invalidate given token
 * required bec
 */
public class LogoutService {

    private static final String CLIENT_ID = "481g1a0ridauh779f34tvsti05";
    private static final String REVOCATION_ENDPOINT = "https://yyc-portal.auth.us-west-2.amazoncognito.com/oauth2/revoke";

    static RestClient restClient = RestClient.create();
    public static void logUserOut(String token) {
        URI uri = UriComponentsBuilder.fromHttpUrl(REVOCATION_ENDPOINT).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "token=" + token + "&client_id=" + CLIENT_ID;
        System.out.println(body);
        ResponseEntity<String> response = restClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .body(body)
            .retrieve()
            .toEntity(String.class);

        System.out.println("Response status: " + response.getStatusCode()); 
        System.out.println("Response headers: " + response.getHeaders()); 
        System.out.println("Contents: " + response.getBody()); 
    }
}