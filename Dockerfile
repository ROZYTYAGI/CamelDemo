FROM openjdk:9.0.4
EXPOSE 8080
# copy WAR into image
ADD target/camel_demo-0.1.jar camel_demo-0.1.jar 
# run application with this command line 
ENTRYPOINT [ "java", "-jar","/camel_demo-0.1.jar" ]
