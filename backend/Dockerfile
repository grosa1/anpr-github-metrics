FROM glassfish:4.1

COPY target/backend-1.0-SNAPSHOT.war /backend.war
COPY start.sh /

EXPOSE 8080

ENTRYPOINT ["/start.sh"]