
FROM openjdk:11

ADD ./target/movie-app-0.0.1-SNAPSHOT.jar /usr/src/movie-app-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "movie-app-0.0.1-SNAPSHOT.jar"]