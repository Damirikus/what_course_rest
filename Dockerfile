FROM gradle:7.4.2-jdk17 AS GRADLE_BUILD
WORKDIR /home/app
COPY . .
RUN gradle clean build --no-daemon

FROM openjdk:17-oracle
WORKDIR /home/app
COPY --from=GRADLE_BUILD /home/app/build/libs/what_course-0.0.1-SNAPSHOT.jar /home/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]