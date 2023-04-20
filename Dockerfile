FROM eclipse-temurin
COPY ./target/app-jar-with-dependencies.jar /tmp
COPY ./data/results.md /tmp/data
WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "app-jar-with-dependencies.jar", "db:3306", "14", "Middle East", "5"]
