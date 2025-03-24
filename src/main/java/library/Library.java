package library;
import book.Book;
import logger.LibraryLogger;
import patron.Patron;
import java.util.*;
import java.util.logging.Level;

public class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Patron> patrons = new HashMap<>();
    private Set<String> availableBooks = new HashSet<>();
    private Set<String> borrowedBooks = new HashSet<>();

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
        availableBooks.add(book.getIsbn());
        LibraryLogger.log(Level.INFO, "Book added: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        if (books.containsKey(isbn)) {
            books.remove(isbn);
            availableBooks.remove(isbn);
            borrowedBooks.remove(isbn);
            LibraryLogger.log(Level.INFO, "Book removed: " + isbn);
        }
    }

    public Book searchByIsbn(String isbn) {
        return books.get(isbn);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        LibraryLogger.log(Level.INFO, "Patron added: " + patron.getName());
    }

    public void checkoutBook(String isbn, String patronId) {
        Book book = books.get(isbn);
        Patron patron = patrons.get(patronId);
        if (book != null && patron != null && availableBooks.contains(isbn)) {
            book.borrowBook();
            patron.borrowBook(book);
            availableBooks.remove(isbn);
            borrowedBooks.add(isbn);
            LibraryLogger.log(Level.INFO, patron.getName() + " borrowed " + book.getTitle());
        }
    }

    public void returnBook(String isbn, String patronId) {
        Book book = books.get(isbn);
        Patron patron = patrons.get(patronId);
        if (book != null && patron != null && borrowedBooks.contains(isbn)) {
            book.returnBook();
            patron.returnBook(book);
            borrowedBooks.remove(isbn);
            availableBooks.add(isbn);
            LibraryLogger.log(Level.INFO, patron.getName() + " returned " + book.getTitle());
        }
    }

    public Set<Book> getAvailableBooks() {
        Set<Book> result = new HashSet<>();
        for (String isbn : availableBooks) {
            result.add(books.get(isbn));
        }
        return result;
    }

    public Set<Book> getBorrowedBooks() {
        Set<Book> result = new HashSet<>();
        for (String isbn : borrowedBooks) {
            result.add(books.get(isbn));
        }
        return result;
    }
}