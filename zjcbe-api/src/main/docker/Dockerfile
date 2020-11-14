FROM java:8
MAINTAINER liuxiaogang <pototallxg@qq.com>

#JVM配置，堆内存和堆外内存限制
ENV JVMOPTS="-Xms512M -Xmx2048m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m"

ADD busline-microservice-calculateservice-*.jar /www/webdev/app.jar
ENTRYPOINT ["sh","-c","java $JVMOPTS -jar /www/webdev/app.jar"]

##docker run -p 30721:8070 -m 2048M -v /tmp:/data/applogs --name buslineservice -d buslineservice:1.0.0