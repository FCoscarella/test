package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Libro;
import model.LibroDB;
import model.LibroModel;
import model.StatoLibro;
import model.StatoLibroModel;
import model.CategoriaLibroModel;
import model.Utente;

@WebServlet("/catalogue")
public class Catalogue extends HttpServlet {

    private String nextPage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ("redirectInsertBook".equals(request.getParameter("action"))) {
            try {
                request.setAttribute("bookCategoriesList", CategoriaLibroModel.getLista());
                if (!"".equals(request.getParameter("id")) && request.getParameter("id") != null) {
                    request.setAttribute("id", request.getParameter("id"));
                    request.setAttribute("libro", LibroDB.select(Long.parseLong(request.getParameter("id"))));
                }
            } catch (NamingException ex) {
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
                request.setAttribute("error", "Si è verificato un errore SQL");
            }
            nextPage = "insertLibro.jsp";
        }

        if ("redirectCatalogue".equals(request.getParameter("action"))) {

            Integer categoria = 1;

            List<Libro> booksList = null;
            List<StatoLibro> statusList = null;

            if (request.getSession().getAttribute("userSession") != null) {
                Utente u = (Utente) request.getSession().getAttribute("userSession");
                categoria = u.getIdCategoria();
            }

            try {
                booksList = LibroModel.getLista(categoria);
                statusList = StatoLibroModel.getLista();

                request.setAttribute("booksList", booksList);
                request.setAttribute("statusList", statusList);
                request.setAttribute("bookCategoriesList", CategoriaLibroModel.getLista());
            } catch (NamingException ex) {
            	ex.printStackTrace();
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
            	ex.printStackTrace();
                request.setAttribute("error", "Si è verificato un errore SQL");
            }

            nextPage = "catalogue.jsp";
        }
        
        if("disable".equals(request.getParameter("action"))) {
            
            Long id = Long.parseLong(request.getParameter("id"));
            Integer oldState = Integer.parseInt(request.getParameter("oldState"));
            Integer newState = 0;
            
            if(oldState == 1){
                newState = 2;
            }
            else if(oldState == 2){
                newState = 1;
            }
            
            try {
                LibroDB.changeState(id, newState);
            } catch (NamingException ex) {
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
                request.setAttribute("error", "Si è verificato un errore SQL");
            }
            
            nextPage = "index.jsp";
            
        }

        if ("update".equals(request.getParameter("action"))) {

            Libro l = null;

            if ("".equals(request.getParameter("id"))) {
                l = LibroModel.creaLibro(
                        request.getParameter("titolo"),
                        request.getParameter("autore"),
                        Integer.parseInt(request.getParameter("anno")),
                        Long.parseLong(request.getParameter("categoriaLibro"))
                );
            } else {
                l = LibroModel.creaLibro(
                        Long.parseLong(request.getParameter("id")),
                        request.getParameter("titolo"),
                        request.getParameter("autore"),
                        Integer.parseInt(request.getParameter("anno")),
                        Long.parseLong(request.getParameter("categoriaLibro"))
                );
            }

            try {
                if (l.getId() != null) {
                    LibroDB.update(l);
                } else {
                    LibroDB.insert(l);
                }
                request.setAttribute("msg", "Database aggiornato");
                nextPage = "index.jsp";
            } catch (NamingException ex) {
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
                request.setAttribute("error", "Si è verificato un errore SQL");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
