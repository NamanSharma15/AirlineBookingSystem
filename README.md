# Airline Booking System

Airline Booking System is a Prototype that uses Java and SQLITE to implement features like user authentication, flight search,ticket booking and boarding pass generation

## Setup

**To get started working with this project you first need to setup the following things:**

### Maven Setup
* Install the latest Maven version for your system from the [<u>official website</u>](https://maven.apache.org/download.cgi) 

* Save the `maven` directory in the `C:/Program Files` directory

* Add the `bin/` folder to the Enviroment Variables

* Restart your pc

### SQLITE3 Setup
* Install the SQLITE3 from the [<u>following website</u>](https://www.sqlite.org/download.html)

* Unzip the contents of the zip file in the `C:/Program Files`

* Add the `root` directory to Enviroment Variables containing the `sqlite3.exe` filed

* Restart your pc

## Execution Command

Use the following command to execute the package for testing purposes
 ```
 mvn package exec:java -D"exec.mainClass"="com.example.App"
 ```

## Compile Command

Use the following command to compile the application code in jar format *(needed for dependencies)*
 ```
 mvn clean compile assembly:single
 ```

## Run Command
After compiling the code to jar using above command you can run it
 ```
 java -jar .\target\air-1.0-jar-with-dependencies.jar
 ```