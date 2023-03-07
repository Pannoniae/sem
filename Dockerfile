FROM openjdk:latest
COPY ./target/sem-jwd-0.1.0.3.jar ./tmp
WORKDIR /tmp
CMD ["chmod", "+rwx", "./sem-jwd-0.1.0.3.jar"]
CMD ["java", "-jar", "sem-jwd-0.1.0.3.jar"]