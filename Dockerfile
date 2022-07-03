FROM openjdk:8-jdk-alpine
COPY build/libs/SpringTest-0.0.1-SNAPSHOT.jar /SpringTest.jar
CMD ["java", "-jar", "/SpringTest.jar"]