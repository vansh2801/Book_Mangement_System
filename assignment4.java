import java.io.*;
import java.util.*;

class Book implements Comparable<Book> {
    int bookId;
    String title;
    String author;
    String category;
    boolean isIssued;

    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    void validateId() throws InvalidIdException {
        if (bookId <= 0) {
            throw new InvalidIdException();
        }
    }

    void displayBookDetails() {
        System.out.println("ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Status: " + (isIssued ? "Issued" : "Available"));
        System.out.println("---------------------------");
    }
}

class InvalidIdException extends Exception {
    InvalidIdException() {
        System.out.println("Error: Book ID must be a positive number.");
    }
}

class LibraryManager {
    Scanner sc = new Scanner(System.in);
    
    ArrayList<Book> books = new ArrayList<>(); 

    void addBook() {
        try {
            System.out.println("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine(); 

            System.out.println("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.println("Enter Author: ");
            String author = sc.nextLine();

            System.out.println("Enter Category: ");
            String category = sc.nextLine();

            Book obj = new Book();
            obj.bookId = id;
            obj.title = title;
            obj.author = author;
            obj.category = category;
            obj.isIssued = false;

            obj.validateId(); 

            books.add(obj); 
            
            saveToFile(obj); 

            System.out.println("Book added and saved to file successfully!");
        } 
        catch (InvalidIdException e) {
        } 
        catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numbers for ID.");
            sc.nextLine(); 
        }
    }

    void saveToFile(Book b) {
        try (FileWriter writer = new FileWriter("books.txt", true)) {
            writer.write(b.bookId + "," + b.title + "," + b.author + "\n");
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    void showBookDetails() {
        System.out.print("Enter Book Title to search: ");
        sc.nextLine(); 
        String searchTitle = sc.nextLine();
        
        boolean found = false;
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(searchTitle)) {
                b.displayBookDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    void sortBooks() {
        Collections.sort(books); 
        System.out.println("Books sorted by Title:");
        for(Book b : books) {
            System.out.println(b.title);
        }
    }

    void mainMenu() {
        while (true) {
            System.out.println("===== City Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Sort Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int n = sc.nextInt();
            
            if (n == 1) {
                addBook();
            }
            if (n == 2) {
                showBookDetails();
            }
            if (n == 3) {
                sortBooks();
            }
            if (n == 4) {
                System.out.println("Exiting System. Thank you!");
                break;
            }
        }
    }
}

class Assign4 {
    public static void main(String[] args) {
        LibraryManager obj1 = new LibraryManager();
        obj1.mainMenu();
    }
}
