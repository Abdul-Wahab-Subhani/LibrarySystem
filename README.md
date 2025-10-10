# 📚 Library Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-007396?style=for-the-badge&logo=java&logoColor=white)
![License: MIT](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-blue?style=for-the-badge)

A **Java Swing–based desktop application** designed to manage a small library.  
It lets users **add, borrow, return, and search books** with a clean, interactive interface.

---

## 🚀 Features

- 📗 **Add Books** — Register new books with title and author.  
- 🔍 **Search Books** — Find books instantly by title.  
- 📚 **List Books** — View available and borrowed books separately.  
- ✅ **Borrow & Return** — Borrow books and mark them as returned easily.  
- 📊 **Live Dashboard** — Displays total, available, and borrowed book counts.  
- 💾 **User Persistence** — Save and load user data to/from text files.  
- 🧠 **Modern UI** — Clean, professional Swing design with navigation sidebar.

---

## 🧠 Project Structure

LibrarySystem/
│
├── Book.java # Represents a single book
├── Library.java # Core library logic and book operations
├── LibraryApp.java # Main Swing application (UI)
└── User.java # User handling and file persistence

---

## 💡 Class Overview

### 📘 `Book.java`
Represents individual books.  
Handles:
- Title, Author, Borrowed status  
- Methods: `borrow()`, `returnBook()`, `isBorrowed()`, `toString()`

### 🏛️ `Library.java`
Contains the core collection logic:
- Add, search, borrow, and return books  
- Retrieve all, available, or borrowed lists  

### 💻 `LibraryApp.java`
Implements a **Java Swing** user interface:
- Sidebar navigation  
- Dynamic book tables  
- Input/output panels  
- Color-coded dashboard

### 👤 `User.java`
Manages user info and persistence:
- Fields: `username`, `borrowedBooks`  
- Methods: `saveToFile()`, `loadFromFile()`

---

## 🖥️ User Interface Preview

<img width="1472" height="874" alt="Screenshot 2025-06-23 004235" src="https://github.com/user-attachments/assets/69b06b1f-fb9f-445a-a949-23ef630b9018" />
<img width="1468" height="874" alt="Screenshot 2025-06-23 004255" src="https://github.com/user-attachments/assets/2b3155a4-dfe7-43b8-8b51-806d816f77d3" />
<img width="1462" height="870" alt="Screenshot 2025-06-23 004342" src="https://github.com/user-attachments/assets/b8601c30-76dc-42da-9cae-ae5c5baabf56" />


---

## 🛠️ Built With

|     Component     |            Technology              |
|-------------------|------------------------------------|
| Language          | Java 17+                           |
| GUI Toolkit       | Java Swing                         |
| Data Handling     | File I/O                           |
| IDE (recommended) | IntelliJ IDEA / Eclipse / NetBeans |

---

## ⚙️ How to Run

```bash
# Clone the repository
git clone https://github.com/Abdul-Wahab-Subhani/LibrarySystem.git
cd LibrarySystem

# Compile and run
javac LibrarySystem/*.java
java LibrarySystem.LibraryApp
The GUI will launch automatically.

🌟 Future Enhancements

🔐 Add user login & authentication

🗂️ Categorize books by genre or author

💾 Save books persistently (JSON or database)

⏰ Add due dates and fine calculation system

👨‍💻 Author

Abdul Wahab Subhani
📧 abdulwahabsubhani2003@gmail.com

🌐 GitHub – Abdul-Wahab-Subhani

📝 License

This project is licensed under the MIT License — feel free to use and modify it with attribution.

⭐ If you like this project, give it a star! ⭐
