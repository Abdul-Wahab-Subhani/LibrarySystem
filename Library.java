package LibrarySystem;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
    }

    // Get all books
    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(books); // return a copy to prevent external modification
    }

    // Get only available (not borrowed) books
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> available = new ArrayList<>();
        for (Book b : books) {
            if (!b.isBorrowed()) {
                available.add(b);
            }
        }
        return available;
    }

    // Get only borrowed books
    public ArrayList<Book> getBorrowedBooks() {
        ArrayList<Book> borrowed = new ArrayList<>();
        for (Book b : books) {
            if (b.isBorrowed()) {
                borrowed.add(b);
            }
        }
        return borrowed;
    }

    // Search for a book by title
    public Book searchBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    // Borrow a book by title
    public boolean borrowBook(String title) {
        Book book = searchBook(title);
        if (book != null && !book.isBorrowed()) {
            book.borrow();
            return true;
        }
        return false;
    }

    // Return a book by title
    public boolean returnBook(String title) {
        Book book = searchBook(title);
        if (book != null && book.isBorrowed()) {
            book.returnBook();
            return true;
        }
        return false;
    }

	public void addBookAtStart(Book newBook) {
	    books.add(0, newBook); 
	}
}
