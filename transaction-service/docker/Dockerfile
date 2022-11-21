FROM openjdk:17-jdk-slim as builder
ARG VERTSION=0.0.1-SNAPSHOT
WORKDIR app
ADD target/transaction-service-$VERTSION.jar transaction-service-$VERTSION.jar
RUN java -Djarmode=layertools -jar transaction-service-$VERTSION.jar extract

FROM openjdk:17-jdk-slim
LABEL PROJECT_NAME=transaction-service \
      PROJECT=transaction-service

EXPOSE 8080

WORKDIR app
COPY --from=builder app/dependencies ./
COPY --from=builder app/spring-boot-loader ./
COPY --from=builder app/snapshot-dependencies ./
COPY --from=builder app/application ./

ENTRYPOINT ["java", "-Djava.security.egd=file:dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
