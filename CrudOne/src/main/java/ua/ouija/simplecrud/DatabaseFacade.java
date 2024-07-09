package ua.ouija.simplecrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFacade {

	public static List<Book> getBooksList() {
		List<Book> booksList = new ArrayList<Book>();
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM books.bookshelf");
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setName(resultSet.getString("name").trim());
				book.setAuthor(resultSet.getString("author").trim());
				book.setYear(resultSet.getInt("year"));
				book.setPages(resultSet.getInt("pages"));
				booksList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksList;
	}

	public Book getBook(int bookId) {
		Book book = new Book();
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			String query = "SELECT * FROM books.bookshelf where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setName(resultSet.getString("name").trim());
				book.setAuthor(resultSet.getString("author").trim());
				book.setYear(resultSet.getInt("year"));
				book.setPages(resultSet.getInt("pages"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public static List<Book> getBooksByAuthor(String author) {
		List<Book> booksList = new ArrayList<Book>();
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			String query = "SELECT * FROM books.bookshelf where author=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, author);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setName(resultSet.getString("name").trim());
				book.setAuthor(resultSet.getString("author").trim());
				book.setYear(resultSet.getInt("year"));
				book.setPages(resultSet.getInt("pages"));
				booksList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksList;
	}

	public static void deleteBook(int bookId) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			String query = "DELETE FROM books.bookshelf where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBook(Book book) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			String query = "insert into books.bookshelf (id, name, author, year, pages) values(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, book.getId());
			preparedStatement.setString(2, book.getName());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setInt(4, book.getYear());
			preparedStatement.setInt(5, book.getPages());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBook(Book book) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			String query = "update books.bookshelf set name=?, author=?, year=?, pages=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(5, book.getId());
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setInt(3, book.getYear());
			preparedStatement.setInt(4, book.getPages());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getMaxId() {
		int bookID = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "1234")) {
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT MAX(id) FROM books.bookshelf LIMIT1");
			resultSet.next();
			bookID = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookID;
	}
}