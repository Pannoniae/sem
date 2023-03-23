FROM eclipse-temurin
COPY ./target/app-jar-with-dependencies.jar /tmp
WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "app-jar-with-dependencies.jar", "db:3306", "2", "Asia", "5"]