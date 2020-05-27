FROM openjdk:9.0.4
EXPOSE 8080
# copy WAR into image
ADD target/CamelDemo-0.0.1-SNAPSHOT.jar CamelDemo-0.0.1-SNAPSHOT.jar 
# run application with this command line 
ENTRYPOINT [ "java", "-jar","/CamelDemo-0.0.1-SNAPSHOT.jar" ]