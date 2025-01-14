
package phuongbtt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LAST_NAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_RECORD_CONTROLLER = "UpdateRecordServlet";
    private final String REGISTRATION_CONTROLLER = "AddAccountServlet";
    private final String START_UP_SERVLET = "StartUpServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String LOG_OUT_CONTROLLER = "LogOutServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "DeleteItemsFromCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String MARKET_PAGE = "PageServlet";
    

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
        //.Which button did user click? 
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) {//fisrt time
                //check cookie
                url = START_UP_SERVLET;
            } else if (button.equals("Login")) { //user clicked Login
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_LAST_NAME_CONTROLLER;
            } else if (button.equals("delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_RECORD_CONTROLLER;
            } else if (button.equals("Add Book to Your Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("Logout")) {
                url = LOG_OUT_CONTROLLER;
            } else if (button.equals("market")) {
                url = MARKET_PAGE;
            } else if (button.equals("Remove Selected Items")) {
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            } else if (button.equals("Create Account")){
            url = REGISTRATION_CONTROLLER;
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
