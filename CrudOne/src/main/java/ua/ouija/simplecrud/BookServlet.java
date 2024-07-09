package ua.ouija.simplecrud;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookServlet.do")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseFacade dao = new DatabaseFacade();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forwardTo = "";
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("delete")) {
			forwardTo = "booksList.jsp";
			int bookId = Integer.parseInt(request.getParameter("id"));
			DatabaseFacade.deleteBook(bookId);
			request.setAttribute("books", DatabaseFacade.getBooksList());
		} else if (action.equalsIgnoreCase("edit")) {
			forwardTo = "form.jsp";
			request.setAttribute("message", "Edit a book");
			int bookId = Integer.parseInt(request.getParameter("id"));
			Book book = dao.getBook(bookId);
			request.setAttribute("book", book);
		} else if (action.equalsIgnoreCase("add")) {
			forwardTo = "form.jsp";
			request.setAttribute("message", "Add a book");
		} else if (action.equalsIgnoreCase("by_author")) {
			forwardTo = "booksList.jsp";
			String author = request.getParameter("author");
			request.setAttribute("books", DatabaseFacade.getBooksByAuthor(author));
		} else {
			forwardTo = "booksList.jsp";
			request.setAttribute("books", DatabaseFacade.getBooksList());
		}
		RequestDispatcher view = request.getRequestDispatcher(forwardTo);
		view.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = new Book();
		String bookIdString = request.getParameter("id");
		book.setName(request.getParameter("name"));
		book.setAuthor(request.getParameter("author"));
		book.setYear(Integer.parseInt(request.getParameter("year")));
		book.setPages(Integer.parseInt(request.getParameter("pages")));
		List<String> invalidFields = Validation.check(book);

		if (invalidFields.isEmpty() == false) {
			RequestDispatcher view = request.getRequestDispatcher("form.jsp");
			request.setAttribute("errmessage", "Fields are filled in incorrectly");
			request.setAttribute("errors", invalidFields);
			request.setAttribute("book", book);
			view.forward(request, response);
		} else {

			if (bookIdString == null || bookIdString.isEmpty()) {
				book.setId(dao.getMaxId() + 1);
				dao.addBook(book);
			} else {
				book.setId(Integer.parseInt(request.getParameter("id")));
				dao.updateBook(book);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("booksList.jsp");
		request.setAttribute("books", DatabaseFacade.getBooksList());
		view.forward(request, response);

	}
}
