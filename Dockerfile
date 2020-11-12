# building the jar archive with maven
FROM maven:3.6.0-jdk-8-alpine as builder
RUN mkdir /tmp/src
COPY . /tmp/src
WORKDIR /tmp/src

RUN ["mvn", "clean", "package", "-DskipTests"]

# executing the compiled jar
FROM glassfish:4.1
COPY --from=builder /tmp/src/backend/target/backend-1.0-SNAPSHOT.war backend.war
COPY start.sh /
EXPOSE 8080:8080
EXPOSE 8181:8181
EXPOSE 4848:4848

ENTRYPOINT ["/start.sh"]
