FROM maven:3.8.3-openjdk-17 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17
COPY --from=build /home/app/target/app.war /user/local/lib/app.war
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/user/local/lib/app.war"]