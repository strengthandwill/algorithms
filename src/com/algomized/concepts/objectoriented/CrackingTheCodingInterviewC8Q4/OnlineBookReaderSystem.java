package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q4;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Design the data structures for an online book reader system.
 * </p>
 *
 */
public class OnlineBookReaderSystem {
	private UserManager userManager;
	private Library library;
	
	public static void main(String[] args) {		
		OnlineBookReaderSystem obrs = new OnlineBookReaderSystem();
		obrs.addUser(new User(1, "Adam"));
		obrs.addUser(new User(2, "Bob"));
		obrs.addUser(new User(3, "Cal"));
		
		obrs.addBook(new Book(1, "Apple", 10));
		obrs.addBook(new Book(2, "Bery", 9));
		obrs.addBook(new Book(3, "Coconut", 11));
		
		System.out.println(obrs.getUsers());
		System.out.println(obrs.getBooks());
		
		obrs.addBookToUser(1, 1);
		obrs.addBookToUser(1, 2);
		System.out.println(obrs.getBooksFromUser(1));
		
		obrs.addBookToUser(2, 3);
		System.out.println(obrs.getBooksFromUser(2));
		
		System.out.println(obrs.getBookFromUser(1, 1));
		ReadBook book = obrs.getBookFromUser(1, 1);
		book.setBookmarked(10);
		System.out.println(obrs.getBookFromUser(1, 1));		
		
		obrs.removeBookFromUser(2, 3);
		System.out.println(obrs.getBooksFromUser(2));
	}
	
	public OnlineBookReaderSystem() {
		userManager = new UserManager();
		library = new Library();
	}
	
	public boolean addUser(User user) {
		return userManager.addUser(user);
	}
	
	public User getUser(long userId) {
		return userManager.getUser(userId);
	}
	
	public User removeUser(long userId) {
		return userManager.removeUser(userId);
	}
	
	public Iterable<ReadBook> getBooksFromUser(long userId) {
		if (!userManager.contains(userId)) {
			return null;
		}		
		return userManager.getUser(userId).getBooks();
	}
	
	public Iterable<User> getUsers() {
		return userManager.getUsers();
	}
	
	public boolean addBook(Book book) {
		return library.addBook(book);
	}
	
	public Book getBook(long bookId) {
		return library.getBook(bookId);
	}
	
	public Book removeBook(long bookId) {
		return library.removeBook(bookId);
	}
	
	public Iterable<Book> getBooks() {
		return library.getBooks();
	}
	
	public boolean addBookToUser(long userId, long bookId) {
		if (!userManager.contains(userId) || !library.contains(bookId)) {
			return false;
		}
		userManager.getUser(userId).addBook(library.getBook(bookId));
		return true;
	}
	
	public ReadBook getBookFromUser(long userId, long bookId) {
		if (!userManager.contains(userId) || !library.contains(bookId)) {
			return null;
		}
		return userManager.getUser(userId).getBook(bookId);		
	}
	
	public boolean updateBookFromUser(long userId, ReadBook book) {
		if (!userManager.contains(userId) || !library.contains(book.getBook().getId())) {
			return false;
		}
		return userManager.getUser(userId).updateBook(book);		
	}	
	
	public ReadBook removeBookFromUser(long userId, long bookId) {
		if (!userManager.contains(userId) || !library.contains(bookId)) {
			return null;
		}
		return userManager.getUser(userId).removeBook(bookId);		
	}
}
