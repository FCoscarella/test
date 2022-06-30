package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoriaLibroModel;
import model.LibroDB;

@WebServlet("/book")
public class Book extends HttpServlet {
	
	private String nextPage="";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		if ("redirectLibro".equals(request.getParameter("action"))) {

            try {
                if (!"".equals(request.getParameter("id")) && request.getParameter("id") != null) {
                    request.setAttribute("id", request.getParameter("id"));
                    request.setAttribute("libro", LibroDB.select(Long.parseLong(request.getParameter("id"))));
                    request.setAttribute("bookCategoriesList", CategoriaLibroModel.getLista());
                }
            } catch (NamingException ex) {
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
                request.setAttribute("error", "Si è verificato un errore SQL");
            }

            nextPage = "libro.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
