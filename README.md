# Library MVP Web Application

Requirements have been deleted.

Tech stack: Java Spring Boot, JPA and PostgreSQL for back-end. I used Spring Boot version 2.7.9 for compatibility with more common Java 11, built using Maven.

React JS and CSS for front-end. The front-end is bootstrapped using Vite.

All local set-up instructions assume you are in the root project directory.

## Database Set Up

For this set up, you will need your username and database name. 

```bash
psql -U [username] -d [database_name] -a -f backend/src/main/resources/db_init.sql
```

## Local Set Up - Backend

With Eclipse or IntelliJ IDE: You can navigate to `backend/src/main/java/app/backend/BackendApplication.java`
