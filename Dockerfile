# 该镜像需要依赖的基础镜像
FROM java:8
FROM maven:3.5.4-alpine
# 打包
ADD . /lingfei
WORKDIR /lingfei/
RUN mvn clean package
EXPOSE 80
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar target/admin-0.0.5-SNAPSHOT.jar
