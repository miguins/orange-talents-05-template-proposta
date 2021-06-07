FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-proposta.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-proposta.jar"]