package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.LibroModel;
import model.Ordine;
import model.OrdineDB;
import model.OrdineModel;
import model.Utente;

@WebServlet("/order")
public class Order extends HttpServlet {

    private String nextPage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ("createOrder".equals(request.getParameter("action"))) {

            try {
                if (!"".equals(request.getParameter("id")) && request.getParameter("id") != null) {

                    Long idLibro = Long.parseLong(request.getParameter("id"));
                    Utente utente = (Utente) request.getSession().getAttribute("userSession");

                    Ordine ordine = OrdineModel.creaOrdine(Timestamp.valueOf(LocalDateTime.now()), utente.getEmail(), idLibro);
                    OrdineDB.insert(ordine);

                    request.setAttribute("booksList", LibroModel.getLista(2));
                    request.setAttribute("orderList", OrdineModel.getLista(utente.getEmail()));

                    nextPage = "order.jsp";
                }
            } catch (NamingException ex) {
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
                request.setAttribute("error", "Si è verificato un errore SQL");
            }

        }

        if ("redirectOrder".equals(request.getParameter("action"))) {

            try {

                Utente utente = (Utente) request.getSession().getAttribute("userSession");

                request.setAttribute("booksList", LibroModel.getLista(2));
                request.setAttribute("orderList", OrdineModel.getLista(utente.getEmail()));

                nextPage = "order.jsp";

            } catch (NamingException ex) {
                request.setAttribute("error", "Si è verificato un errore Naming");
            } catch (SQLException ex) {
                request.setAttribute("error", "Si è verificato un errore SQL");
            }

        }
        
        if("delete".equals(request.getParameter("action"))){
            
            try {
                
                Utente utente = (Utente) request.getSession().getAttribute("userSession");
                
                Long id = Long.parseLong(request.getParameter("id"));
                
                OrdineDB.delete(id);

                request.setAttribute("booksList", LibroModel.getLista(2));
                request.setAttribute("orderList", OrdineModel.getLista(utente.getEmail()));

                nextPage = "order.jsp";

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
