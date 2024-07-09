package ua.ouija.simplecrud;

public class Book {

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", year=" + year + ", pages=" + pages + "]";
	}

	private int id;
	private String name;
	private String author;
	private int year;
	private int pages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
}
