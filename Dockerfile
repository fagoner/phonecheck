FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

RUN mkdir /opt/app
RUN TZ=America/Guatemala
COPY target/phonecheck-d*.jar /opt/app

CMD ["java", "-jar", "-Xms512m", "-Xmx1G", "/opt/app/phonecheck-dev.jar"]
