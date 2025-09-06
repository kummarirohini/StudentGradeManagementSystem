# Student Grade Management System

A console-based Java application designed to manage student records and grades efficiently. Built using core Object-Oriented Programming principles, this system demonstrates encapsulation, data structures, and file handling.

## 🚀 Features

- **Student Management**: Add and store student details (ID, Name)
- **Grade Management**: Add grades for various subjects for each student
- **GPA Calculation**: Automatically calculates Grade Point Average (GPA) for students
- **Report Generation**: Generate detailed student reports with subject-wise grades and overall GPA
- **Data Persistence**: Save and load student data to/from a text file
- **User-Friendly Menu**: Console-based menu system for easy navigation

## 🛠️ Technologies Used

- **Java**: Core programming language
- **OOP Concepts**: Encapsulation, Classes, Objects
- **Data Structures**: HashMap for efficient data storage and retrieval
- **File I/O**: Reading from and writing to text files
- **Exception Handling**: Robust error handling for user inputs and file operations

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt
- Any text editor or IDE (VSCode, IntelliJ, Eclipse, etc.)

## 🖥️ Installation and Execution

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/student-grade-management-system.git
cd student-grade-management-system
```

### 2. Compile the Java Program
```bash
javac StudentGradeManagementSystem.java
```

### 3. Run the Application
```bash
java StudentGradeManagementSystem
```

## 📷 Sample Usage

1. **Add a Student**:
   ```
   Enter Student ID: 001
   Enter Student Name: John Doe
   Student added successfully!
   ```

2. **Add Grades**:
   ```
   Enter Student ID: 001
   Enter Subject: Mathematics
   Enter Grade (0-100): 85
   Grade added successfully!
   ```

3. **Generate Report**:
   ```
   === STUDENT REPORT ===
   Student ID: 001
   Name: John Doe
   Overall GPA: 85.00
   
   Subject-wise Grades:
   Mathematics: 85.00
   ```

## 🏗️ Project Structure

```
StudentGradeManagementSystem/
│
├── StudentGradeManagementSystem.java  # Main application file
├── student_data.txt                   # Data storage file (auto-generated)
└── README.md                          # Project documentation
```

4. **Replace** placeholder text like `your-username` with your actual GitHub username
5. **Save** the file and upload it to GitHub along with your Java code

This README will make your project look professional and help anyone who comes across your repository understand what your project does and how to use it.
