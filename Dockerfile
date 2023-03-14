FROM openjdk:8-jdk
RUN mkdir /logs
ENV project=termi\
   TZ='Asia/Shanghai'\
   profile=prod\
   java_opts="-Xms256m -Xmx512m"
ADD target/*.jar /
ENTRYPOINT ["sh","-c","exec java ${java_opts} -Dspring.application.name=${project} -Dspring.profiles.active=${profile} -Dlog4j2.formatMsgNoLookups=true -jar /${project}.jar"]
