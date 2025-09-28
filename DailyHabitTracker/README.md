# DAILY-HABIT-TRACKER
📝 Daily Habit Tracker

A Java console application that helps users manage their daily habits effectively.
The application supports CRUD operations (Create, Read, Update, Delete) and stores all data in a MySQL database via JDBC.
 
DailyHabitTracker/
│
├── lib/
│   └── mysql-connector-j-9.4.0.jar    
│
├── src/
│   ├── DatabaseConnection.java       
│   ├── Habit.java                     
│   └── HabitTrackerApp.java          
│
└── README.md                          

Features

1. Add Habit – Create a new habit and save it to the database.
2. View Habits – Display all existing habits.
3. Mark Habit as Complete – Update habit status.
4. Delete Habit – Remove a habit permanently.
5. Exit – Close the application gracefully.

🛠️ Technologies & Concepts

Java SE
Classes, Objects, Methods
Console I/O (Scanner)
Exception handling
JDBC (Java Database Connectivity)
Establishing connections
Executing SQL queries with PreparedStatement
Handling SQLException
MySQL Database
Schema & table creation
Storing and retrieving structured data
Project Structure
Separation of concerns with multiple classes (DatabaseConnection, Habit, HabitTrackerApp)
External library integration (mysql-connector-j-9.4.0.jar)

📊 Sample Execution
=== Daily Habit Tracker ===
1. Add Habit
2. View Habits
3. Mark Habit as Complete
4. Delete Habit
5. Exit
Enter choice: 1
Enter habit name: Morning Exercise
Habit added successfully!
