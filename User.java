package LibrarySystem;

import java.io.*;

public class User {
    private String username;
    private int borrowedBooks;

    public User(String username) {
        this.username = username;
        this.borrowedBooks = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook() {
        borrowedBooks++;
    }

    public void returnBook() {
        borrowedBooks--;
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(username + ".txt")) {
            writer.write("Username:" + username + "\n");
            writer.write("BorrowedBooks:" + borrowedBooks + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String nameLine = reader.readLine();
            String borrowedLine = reader.readLine();
            if (nameLine == null || borrowedLine == null) return null;

            String username = nameLine.split(":")[1];
            int borrowed = Integer.parseInt(borrowedLine.split(":")[1]);

            User user = new User(username);
            for (int i = 0; i < borrowed; i++) user.borrowBook();

            return user;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}