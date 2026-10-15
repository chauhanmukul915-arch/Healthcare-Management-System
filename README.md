# CarePoint — Healthcare Management Tool

A starter college project using Java 17, Jakarta Servlets, JDBC, MySQL, HTML, CSS, and JavaScript.

## Features
- Session-based login/logout
- Dashboard summary
- Patient add/list
- Doctor add/list
- Appointment booking/list
- Simple billing records/list
- Prepared statements and basic server-side validation

> Educational starter, not a certified clinical system. Do not use real patient data. Before production use, add CSRF protection, role-based authorization, audit logs, secure secret management, backups, HTTPS, stronger validation, and privacy/compliance review.

## Requirements
- JDK 17+
- Maven 3.9+
- MySQL 8+
- Apache Tomcat 10.1+
- VS Code extensions: Extension Pack for Java, Maven for Java

## Setup
1. Create a MySQL database and run `database/schema.sql` in MySQL Workbench.
2. Create `src/main/resources/db.properties` from `src/main/resources/db.properties.example` and set your MySQL username/password.
3. Open this folder in VS Code.
4. In terminal, run `mvn clean package`.
5. Copy `target/healthcare.war` to Tomcat's `webapps` folder.
6. Start Tomcat:
   - Windows: open `TOMCAT_HOME\bin` in PowerShell and run `.\startup.bat`
   - Stop with `.\shutdown.bat`
   - Do not run `catalina.bat run` from a folder that is not Tomcat's `bin`.
7. Open `http://localhost:8080/healthcare/`.

## First login
On first startup, the app creates a starter administrator if the `users` table is empty:
- Username: `admin`
- Password: `ChangeMe123!`

Change the password immediately. This starter password is for local development only.

## Configuration
The DB properties file is read from the classpath:
```
db.url=jdbc:mysql://localhost:3306/healthcare_db?useSSL=false&serverTimezone=UTC
db.username=root
db.password=YOUR_MYSQL_PASSWORD
```

## Project structure
```
src/main/java/com/healthcare/
  config/DB.java
  dao/HealthcareDAO.java
  filter/AuthFilter.java
  servlet/...
  util/Html.java
src/main/resources/db.properties.example
src/main/webapp/
  index.jsp, login.jsp, dashboard.jsp
  WEB-INF/web.xml
  patients.jsp, doctors.jsp, appointments.jsp, billing.jsp
  assets/css/style.css, assets/js/app.js
database/schema.sql
```
