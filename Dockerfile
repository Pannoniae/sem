FROM openjdk:latest
COPY ./target/sem-jwd-0.1.0.3.jar ./tmp
WORKDIR /tmp
CMD ["java", "-jar", "sem-jwd-0.1.0.3.jar", "1"]
