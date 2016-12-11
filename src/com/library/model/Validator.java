package com.library.model;

import com.library.controller.*;

public class Validator {

	private static final String SpecialCharsRegex = "[@#$%^&*=+~`|\\/?!.,\';:\"]";
	private static final String SpecialReplaceChar = ":";
	private static final String PhoneNumberInvalidCharsRegex = "[a-zA-Z@#$%^&*=~`|\\/?!.,\';:\"]";
	
	/**
	 * @param str
	 * @return
	 */
	public boolean isBookIsbnValid(String str) {
		final String invalidChars = SpecialCharsRegex;

		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		str = str.replaceAll(invalidChars, SpecialReplaceChar);

		if (str.contains(SpecialReplaceChar))
			return false;

		return true;
	}

	public boolean isBookNameValid(String str) {
		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		return true;
	}
	
	public boolean isBookAuthorValid(String str) {
		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		return true;
	}
	
	
	public boolean isContentValid(String str) {
		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		return true;
	}

	public boolean isUserIdValid(String str) {

		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		try {
			@SuppressWarnings("unused")
			int checkInt = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;

	}

	public boolean isUserNameValid(String str) {
		final String invalidChars = SpecialCharsRegex;

		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		str = str.replaceAll(invalidChars, SpecialReplaceChar);

		if (str.contains(SpecialReplaceChar))
			return false;

		return true;
	}

	public boolean isUserPasswordValid(String str) {
		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;

		return true;
	}

	public boolean isUserPhoneNoValid(String str) {
		if (str == null)
			return false;

		str = str.trim();
		if (str.length() == 0)
			return false;
str = str.replaceAll(PhoneNumberInvalidCharsRegex, SpecialReplaceChar);
		if (str.contains(SpecialReplaceChar))
			return false;
		
		return true;
	}

	/**
	 * whether the user is valid. used in Adding user.
	 * @return
	 */
	public boolean isUserValid(User user) {
		if (user==null)
			return false;
		
		boolean bId = isUserIdValid(String.valueOf(user.getUserId()));
		boolean bName = isUserNameValid(user.getUserName());
		boolean bPassword = isUserPasswordValid(user.getPassword());
		boolean bPhoneNo = isUserPhoneNoValid(user.getPhoneNo());
		return (bId && bName && bPassword && bPhoneNo);
	}
	
	public boolean isBookValid(Book book) {
		if (book==null)
			return false;
		
		boolean bIsbn = isBookIsbnValid(book.getIsbn());
		boolean bName = isBookNameValid(book.getBookName());
		boolean bAuthor = isBookAuthorValid(book.getAuthor());
		return (bIsbn && bName && bAuthor );
	}

}
