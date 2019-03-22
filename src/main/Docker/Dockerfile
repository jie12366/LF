FROM java:8
VOLUME /tmp
ADD admin-0.0.1-SNAPSHOT.jar admin-app.jar
RUN bash -c 'touch /admin-app.jar'
EXPOSE 88
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin-app.jar"]
