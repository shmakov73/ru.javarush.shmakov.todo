Project theme: multi-module web application "Task List" with the ability to view the list of tasks,
add new tasks, edit and delete existing tasks.

Applied technologies: MySQL, Hibernate, Spring, Spring MVC, Thymeleaf.

Now in the application, tasks are divided by users.

First you need to enter your name on the login page:
![2023-04-14_11-50-51](https://user-images.githubusercontent.com/104271423/232039330-1f8ab8f6-11c9-4981-bcc8-19f8cf4642b4.png)

Next, a page with a list of tasks corresponding to the entered user will open:
![2023-04-14_11-51-25](https://user-images.githubusercontent.com/104271423/232039535-630a28be-8051-46a1-991c-481ae5b60de3.png)

To change the user, you need to click the button in the left part of the window above "Change user" and you will see the login page again.

To run the project, you need to deploy the database in Docker with the command: 

docker run --name mysql -e MYSQL_ROOT_PASSWORD=qwerty -d -p 3306:3306 mysql:latest

Next, to create a table, you need to run the SQL script located in the root directory of the project "todo-dump.sql".

To run the application use Tomcat 10.
