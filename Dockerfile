FROM tomcat:alpine
COPY services/target/*.war /usr/local/tomcat/webapps/
COPY angular/newsfeedfront /usr/local/tomcat/webapps/newsfeedfront
COPY services/tomcat-users.xml /usr/local/tomcat/conf/
COPY services/server.xml /usr/local/tomcat/conf/
COPY services/context.xml /usr/local/tomcat/webapps/manager/META-INF/