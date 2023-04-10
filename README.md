Project theme: multi-module web application "Task List" with the ability to view the list of tasks,
add new tasks, edit and delete existing tasks.

Applied technologies: MySQL, Hibernate, Spring, Spring MVC, Thymeleaf.
![2023-04-10_11-12-31](https://user-images.githubusercontent.com/104271423/230855828-6547a06f-9391-472a-85eb-28546a755d3f.png)


To run the project, you need to deploy the database in Docker with the command: 

docker run --name mysql -e MYSQL_ROOT_PASSWORD=qwerty -d -p 3306:3306 mysql:latest

Next, to create a table, you need to run the SQL script located in the root directory of the project "todo-dump.sql".

To run the application use Tomcat 10.
