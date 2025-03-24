package main;
import book.Book;
import library.Library;
import patron.Patron;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        Book book1 = new Book("Effective Java", "Joshua Bloch", "9780134685991", 2017);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2008);
        Book book3 = new Book("12 rules", "Jordan P", "9780132350885", 2009);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Add patrons
        Patron patron1 = new Patron("Alice", "P001");
        Patron patron2 = new Patron("Bob", "P002");
        library.addPatron(patron1);
        library.addPatron(patron2);

        // Checkout and return
        library.checkoutBook("9780134685991", "P001");
        library.returnBook("9780134685991", "P001");
        library.checkoutBook("9780132350885", "P002");

        // Inventory Check
        // Display available books
        System.out.println("Available Books:");
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }

        // Display borrowed books
        System.out.println("\nBorrowed Books:");
        for (Book book : library.getBorrowedBooks()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }

    }
}
