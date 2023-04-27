FROM tomcat:10.0.27

COPY /todo-ui/target/todoList.war /usr/local/tomcat/webapps/