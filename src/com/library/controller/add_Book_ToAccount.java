package com.library.controller;

import java.util.ArrayList;
import java.util.Date;

public interface add_Book_ToAccount {

	int LIBRARY_OWNER_ID = 0;

	User login(String userName, String password);

	String getBookImgFileFullName(String isbn);

	/**
	 * Copy the book image file into the default Book Image folder.
	 * @param srcImgPath  original image file path e.g. D:/img1
	 * @param srcImgFileName original image filename,  e.g. abc.jpg
	 * @param newImgFilename new image filename, without extension,  e.g.  1234
	 * @return if success copy the file, return true; else return false;
	 * e.g. Will copy D:/img1/abc.jpg into DEFAULT_BOOK_IMG_PATH/1234.jpg. 
	 * @throws Exception
	 */
	boolean copyBookImage(String srcImgPath, String srcImgFileName, String newImgFilename);

	void addBook(Book b);

	void updateBook(String isbn, Book b);

	boolean deleteBook(String isbn);

	boolean addUser(User user);

	ArrayList<Book> showBookList_all();

	ArrayList<Book> showBookList_rented();//

	ArrayList<Book> showBookList_remainder();

	ArrayList<Book> showBookList_BorrowedByCustomer(int customerId);

	ArrayList<Book> showBookList_overdue();

	boolean rentBook(int customerId, String isbn);

	void returnBook(String isbn);

	double fine(String isbn, Date returnDate);

	Book getBookByISBN(String isbn);

}