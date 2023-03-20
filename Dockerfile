FROM eclipse-temurin:latest
COPY ./target/sem-jwd-0.1.0.3.jar ./tmp
WORKDIR /tmp
CMD ["java", "-jar", "-Djava.net.preferIPv4Stack=true", "sem-jwd-0.1.0.3.jar", "1"]
#CMD ["tail", "-f", "/dev/null"]