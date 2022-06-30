package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Utente;
import model.UtenteDB;
import model.UtenteModel;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    private String nextPage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ("redirectRegister".equals(request.getParameter("action"))) {
            nextPage = "registration.jsp";
        }

        if ("register".equals(request.getParameter("action"))) {

            try {

                Utente user = UtenteModel.creaUtente(
                        request.getParameter("email"),
                        request.getParameter("password"),
                        request.getParameter("name"),
                        request.getParameter("surname"),
                        LocalDate.parse(request.getParameter("birthdate")));

                if (!UtenteDB.cerca(user.getEmail())) {
                    UtenteDB.insert(user);
                    request.getSession().setAttribute("userSession", user);
                    nextPage = "index.jsp";
                } else {
                    request.setAttribute("msg", "È già  presente un account con questa e-mail, provane un'altra");
                    request.setAttribute("user", user);
                }

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
