package ua.ouija.simplecrud;

import java.nio.charset.Charset;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Validation {

	public static List<String> check(Book book) {

		List<String> messages = new ArrayList<String>();
		String name = book.getName();
		String author = book.getAuthor();
		int year = book.getYear();
		int pages = book.getPages();

		if (name == null || name.trim().isEmpty() ||
				!Charset.forName("US-ASCII").newEncoder().canEncode(name)) {
			messages.add("name");
		} 
		if (author == null || author.trim().isEmpty() ||
				!Charset.forName("US-ASCII").newEncoder().canEncode(author)) {
			messages.add("author");
		} 
		if (year < 1501 || year > Year.now().getValue()) {
			messages.add("year");
		} 
		if (pages < 0 || pages > 2000) {
			messages.add("price");
		}
		return messages;
	}
}
