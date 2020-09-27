FROM maven:3.6.1-jdk-8 as builder
RUN mkdir /usr/src/alcoholapp
COPY . /usr/src/alcoholapp
WORKDIR /usr/src/alcoholapp
RUN mvn package
FROM java:8-jdk
COPY --from=builder /usr/src/alcoholapp/target/alcoholrecovery-0.0.1-SNAPSHOT.jar /usr/src/alcoholapp/alcoholrecovery-0.0.1-SNAPSHOT.jar
EXPOSE 8080
#CMD ["tail", "-f", "/dev/null"]
CMD ["java","-Dspring.profiles.active=prod", "-jar", "/usr/src/alcoholapp/alcoholrecovery-0.0.1-SNAPSHOT.jar"]
