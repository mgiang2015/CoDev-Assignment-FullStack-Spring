# Library MVP Web Application

Requirements have been deleted.

Tech stack: Java Spring Boot, JPA and PostgreSQL for back-end. I use Spring Boot version 2.7.9 for compatibility with the more common Java version 11. Project is built using Maven.

I use React JS and CSS for front-end. The front-end is bootstrapped using Vite.

All local set-up instructions assume you are in the root project directory.

## Database

For this set up, you will need your username and database name. **Please note that this will overwrite all the data with the same table name as the ones used in `db_init.sql`**

```bash
psql -U [username] -d [database_name] -a -f backend/src/main/resources/db_init.sql
```

This would have populated some mock data into the database to test queries.

## Back-end

With Eclipse or IntelliJ IDE: You can navigate to `backend/src/main/java/app/backend/BackendApplication.java` and press the play button to start the server.

With Command Line (Please install Maven if you have not before running these commands):

```bash
cd backend
mvn clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

Both of these options will start a server at address `http://localhost:8080`. Please do not change the port as my front-end application communicates with the backend through this port.

## Front-end

You will need `npm` to run the front-end. Run the following commands:

```bash
cd frontend
npm install
npm run dev
```

If this does not work, please check your corrent directory and make sure you are in the root folder. The application will be served at address `http://localhost:5173`.
