# Build the Spring Boot application
FROM maven:3.9.7-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Set up the runtime environment
FROM eclipse-temurin:21

RUN apt update && \
    apt install python3.12 python3-pip python3.12-venv screen -y

COPY ./chatbot chatbot
RUN /bin/bash -c "cd /chatbot && \
    python3.12 -m venv venv && \
    source venv/bin/activate && \
    pip install -r requirements.txt"

COPY --from=build /target/portal-0.0.1-SNAPSHOT.jar portal.jar

COPY ./startup.sh startup.sh
RUN chmod +x startup.sh
RUN echo "shell /bin/bash" >> /root/.screenrc

EXPOSE 8080
ENTRYPOINT ["/startup.sh"]