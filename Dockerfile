FROM eclipse-temurin:17

COPY target/loanapply-0.0.1-SNAPSHOT.jar loanapply.jar

ENV PORT 8080
EXPOSE 8080

ENTRYPOINT ["java", "-Duser.timezone=Europe/Copenhagen", "-jar", "/loanapply.jar"]
