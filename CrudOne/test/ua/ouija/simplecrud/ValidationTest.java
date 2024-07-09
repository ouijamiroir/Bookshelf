package ua.ouija.simplecrud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ValidationTest {

	private Book getTestBook() {
		Book someBook = new Book();
		someBook.setAuthor("This Author");
		someBook.setName("This Book");
		someBook.setYear(1999);
		someBook.setPages(300);
		return someBook;
	}

	@Test
	public void testNameIsNull() {

		Book testBook = getTestBook();
		testBook.setName(null);
		assertEquals("name", Validation.check(testBook).get(0));
	}

	@Test
	public void testNameIsEmpty() {
		Book testBook = getTestBook();
		testBook.setName("");
		assertEquals("name", Validation.check(testBook).get(0));
	}

	@Test
	public void testNameIsSpace() {
		Book testBook = getTestBook();
		testBook.setName(" ");
		assertEquals("name", Validation.check(testBook).get(0));
	}

	@Test
	public void testNameOk() {
		Book testBook = getTestBook();

		testBook.setName("Some Book");
		assertEquals(true, Validation.check(testBook).isEmpty());
	}

}
