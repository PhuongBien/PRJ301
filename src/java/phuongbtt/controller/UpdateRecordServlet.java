
package phuongbtt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuongbtt.registration.RegistrationDAO;
import phuongbtt.registration.RegistrationDTO;


@WebServlet(name = "UpdateRecordServlet", urlPatterns = {"/UpdateRecordServlet"})
public class UpdateRecordServlet extends HttpServlet {

    private final String RESULT_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = RESULT_PAGE;
        try {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String lastname = request.getParameter("txtLastname");
            String admin = request.getParameter("chkAdmin");
            boolean role = false;
            if (admin != null) {
                role = true;
            }
            String search = request.getParameter("lastSearchValue");
            // Basic input validation
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateRecord(username, password, lastname, role);
            if(result){
            url = "DispatchServlet"
                    + "?btAction=Search"
                    + "&txtSearchValue=" + search;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
            
        }
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
