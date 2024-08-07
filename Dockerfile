FROM openjdk:17
EXPOSE 9876
ADD target/qrcodegenerator.jar qrcodegenerator.jar
ENTRYPOINT ["java", "-jar", "/qrcodegenerator.jar"]
