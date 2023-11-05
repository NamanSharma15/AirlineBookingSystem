# Airline Booking System

Airline Booking System is a Prototype that uses Java and SQLITE to implement features like user authentication, flight search,ticket booking and boarding pass generation

## Setup

**To get started working with this project you first need to setup the following things:**

### Maven Setup
* Install the latest Maven version for your system from the [<u>following website</u>](https://maven.apache.org/download.cgi) 

* Save the `maven` directory in the `C:/Program Files` directory

* Add the `bin/` folder to the Enviroment Variables

* Restart your pc

### SQLITE3 Setup
* Install the SQLITE3 from the [<u>following website</u>](https://www.sqlite.org/download.html)

* Unzip the contents of the zip file in the `C:/Program Files`

* Add the `root` directory to Enviroment Variables that contains the `sqlite3.exe` file

* Restart your pc

## Working

**In this section you could find the details regarding fuctioning of the Airline Booking System(file structure,database schemas and dependencies)**

### File Structure

![not found](/images/files.png)

#### **Main App Directory (src/main/java/com/example)**

**App.java**: It is the main file that is responsable for runnning the main app and intigerate all the other classes to achive the user input based interface with complete funtionality

#### **→ Auth Directory (/auth)**

Auth Directory contains one abstact class **Auth** and two derived classes **Login** and **Signup**  

* **Auth.java**: As an Abstract class it provides  instance variables and methods that are common both for Login and Signup like name,email password.

* **Signup.java**: It inherits the Auth class the takes and checks user input for name, email and password and uses the **Database** object to add a new user

* **Login.java**: It also inherits the Auth class and takes and checks user input for email and password but instead uses the **Database** object to find the user and set the values for the Auth instance varibles

#### **→ Database Directory (/database)**

* **Database.java**: It provides database connectivity with the **Airline.db** sqlite database and preforms operation like getting and adding things likes flights,users and tickets.

#### **→ Functions Directory (/functions)**

Functions Directory contains two interfaces **BookTicket** and **GetPass** and a class **Functions** that implements them.

* **Functions.java**: It implements the other interfaces present in the dirctory and provides a places to access them all.

* **BookTicket.java**: As an Interface it provides and user input based fuctionality to book a flight ticket by seaching for it and add it to the tickets table and creating the tickets files using the **Database** object and **Utils** class.

#### **→ Schemas Directory (/schemas)**

Schemas directory contains all the schemas(structure) for the tables in the airline database like user,flight and ticket

* **User.java**: Contains structure for the user

* **Ticket.java**: Contains structure for the ticket

* **Flight.java**: Contains structure for the flight

#### **→ Utils Directory (/utils)**

* **Utils.java**:This class contains some static methods used for things like getting file contents and creating files

<hr>

### Database schemas

* **Users Table**

```
Users(Uid varchar(6) primary key,Name varchar(30),Email varchar(50) unique not null, Password varchar(50))
```

* **Flights Table**

```
Flights(Flight_No varchar(30) primary key,Destination varchar(30),Time varchar(15),Terminal varchar(5),Gate_No varchar(5),Status varchar(25));
```

* **Tickets Table**

```
Tickets(id varchar(10),booking_time varchar(8),class varchar(20),date varchar(10),uid varchar(6),fid varchar(30),CONSTRAINT fk_users foreign key(uid) references users(uid),CONSTRAINT fk_airline  foreign key (fid) references airline(Flight_No));
```

<hr>

### Dependencies

* **org.sqlite.JDBC**: Used for SQLite and Java connectivity using JDBC


## Execution 

### Testing Command

Use the following command to execute the package for testing purposes
 ```
 mvn package exec:java -D"exec.mainClass"="com.example.App"
 ```

### Compile Command

Use the following command to compile the application code in jar format *(needed for external dependencies)*
 ```
 mvn clean compile assembly:single
 ```

### Run Command
After compiling the code to jar using above command you can run it
 ```
 java -jar .\target\air-1.0-jar-with-dependencies.jar
 ```