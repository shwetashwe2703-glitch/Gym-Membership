# 🏋️ Gym Membership Management System

A desktop-based **Gym Membership Management System** developed using Java Swing, JDBC, and MySQL. This application helps manage gym member information efficiently through a simple graphical user interface.

## 📌 Project Overview

The Gym Membership Management System is designed to simplify the process of managing gym members. It allows users to add new members, update existing information, delete records, and view all registered members.

The application connects to a MySQL database using JDBC to store and retrieve member details.

## ✨ Features

- **Add Members:** Register new gym members.
- **Update Members:** Modify existing member details.
- **Delete Members:** Remove member records using Member ID.
- **View Members:** Display all registered members in a table.
- **Membership Plans:** Choose between Monthly, Quarterly, and Yearly plans.
- **Clear Form:** Reset all input fields.
- **User-Friendly Interface:** Simple graphical interface built using Java Swing.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Java Swing | Graphical User Interface (GUI) |
| JDBC | Database connectivity |
| MySQL | Database management |
| MySQL Connector/J | JDBC driver |
| VS Code | Development environment |

## 📂 Project Structure

```text
Gym-Membership/
│
├── lib/
│   └── mysql-connector-j-26.10.0.jar
│
├── GymApp.java
├── .gitignore
└── README.md
```

## 🗄️ Database Setup

1. Open MySQL Workbench.
2. Create a database named `gymdb`.
3. Create the `members` table using the following SQL query:

```sql
CREATE DATABASE gymdb;

USE gymdb;

CREATE TABLE members (
    member_id INT PRIMARY KEY,
    member_name VARCHAR(100),
    age INT,
    phone VARCHAR(15),
    membership_plan VARCHAR(30)
);
```

## ⚙️ How to Run the Project

### Prerequisites

- Java JDK installed.
- MySQL Server installed and running.
- MySQL Workbench.
- MySQL Connector/J library.

### Steps

1. Clone this repository:

```bash
git clone https://github.com/shwetashwe2703-glitch/Gym-Membership.git
```

2. Open the project folder in VS Code.

3. Configure the MySQL database connection in `GymApp.java`.

4. Ensure the MySQL Connector/J JAR is included in the Java classpath.

5. Compile the Java program:

```bash
javac -cp ".;lib/mysql-connector-j-26.10.0.jar" GymApp.java
```

6. Run the application:

```bash
java -cp ".;lib/mysql-connector-j-26.10.0.jar" GymApp
```

## 🔄 JDBC Concepts Implemented

This project demonstrates the use of Java Database Connectivity (JDBC) concepts:

- **Connection:** Establishes a connection between Java and MySQL.
- **DriverManager:** Manages database connectivity.
- **Statement:** Executes SQL queries.
- **PreparedStatement:** Executes parameterized SQL queries.
- **ResultSet:** Retrieves records from the database.
- **executeQuery():** Executes SELECT statements.
- **executeUpdate():** Executes INSERT, UPDATE, and DELETE statements.

## 📸 Application Modules

- Main application window
- Member registration form
- Membership plan selection
- Add, Update, Delete and Clear operations
- View all registered members

## 🎯 Learning Outcomes

- Understanding Java Swing components.
- Implementing JDBC connectivity.
- Performing CRUD operations using MySQL.
- Working with SQL queries and database records.
- Handling user interactions through ActionListener.
- Understanding database-driven desktop applications.

## 👩‍💻 Author

**Shweta Suresh**

Computer Science and Engineering – Cyber Security

## 📄 License

This project was developed for academic and educational purposes.
