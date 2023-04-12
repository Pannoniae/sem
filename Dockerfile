FROM eclipse-temurin
COPY ./target/app-jar-with-dependencies.jar /tmp
WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "app-jar-with-dependencies.jar", "0.0.0.0", "27", "Asia", "5"]
