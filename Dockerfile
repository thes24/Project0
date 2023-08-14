FROM openjdk:17-alpine

ADD . /src

RUN cd /src && ./gradlew build

ENTRYPOINT ["java", "-jar", "/src/build/libs/Project0-0.0.1-SNAPSHOT.jar"]
