#=============================================================================
# Two-stage Dockerfile producing an API container image
#
# Stage 1: use an official Maven base image, copy across POMs and download
#          dependencies. Do this separately from the build so that the
#          container layers cache the dependencies for future builds, only
#          re-fetching when the POMs change. Then copy the rest of the source
#          code and build the JAR.
# Stage 2: use a smaller base image (without the build tools and .m2 cache to
#          result in a smaller container image. Copy the JAR from the build
#          image, then expose Tomcat port and launch JAR as usual.
#=============================================================================

FROM maven AS build

WORKDIR /docker-demo/java

COPY java/pom.xml .
COPY java/api/pom.xml ./api/pom.xml
COPY java/admin-server/pom.xml ./admin-server/pom.xml

RUN mvn -B dependency:go-offline

COPY java .

RUN mvn clean package

FROM openjdk:15-alpine
COPY --from=build /docker-demo/java/api/target/api-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app.jar"]
