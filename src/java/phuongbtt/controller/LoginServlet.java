
package phuongbtt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuongbtt.registration.RegistrationDAO;
import phuongbtt.registration.RegistrationDTO;
import sun.security.pkcs11.wrapper.Functions;


public class LoginServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "search.html";
    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";

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
        PrintWriter out = response.getWriter();
        //1. get user' information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        String url = INVALID_PAGE;

        try {
            //2. Call method of Model
            //2.1 new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO
            RegistrationDTO result = dao.checkLogin(username, password);

            //3. process result
            if (result!=null) {
                url = SEARCH_PAGE;
                //open session
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                session.setAttribute("USERNAME", username);
                //cookies
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60*30);
//                response.addCookie(cookie);
            }//user is authenticated

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
//           response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
