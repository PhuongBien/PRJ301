
package phuongbtt.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuongbtt.registration.RegistrationCreateErr;
import phuongbtt.registration.RegistrationDAO;
import phuongbtt.registration.RegistrationDTO;


@WebServlet(name = "AddAccountServlet", urlPatterns = {"/AddAccountServlet"})
public class AddAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE="login.html";

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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        //1. get all user's info
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        boolean foundErr = false;
        RegistrationCreateErr errors = new RegistrationCreateErr();
        String url = ERROR_PAGE;
        try {
            
            //04 user's error and 01 system's error
            //2. check all users error
            if (username.trim().length() < 6 || username.trim().length() > 12) {
                foundErr = true;
                errors.setUsernameLengthErr("Username is required from 6 to 12 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr("Username is required from 6 to 20 characters");
            }
            if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match Password");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullnameLengthErr("Full name is required from 2 to 50 characters");
            }
            if (foundErr) {//error occur
                request.setAttribute("CREATE_ERRORS", errors);
            } else { //no error
                //3. call method of model/dao
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                //4. process result
                if(result){
                    url = LOGIN_PAGE;
                    
                }
            }
        }catch(SQLException e) {
            log("AddAccountServlet _ SQL:" + e.getMessage());
            if(e.getMessage().contains("duplicate")){
                errors.setUsernameIsExisted(username+ "is existed");
                request.setAttribute("CREATE_ERRORS", errors);
                url = ERROR_PAGE;
            }
        }catch(ClassNotFoundException e) {
            log("AddAccountServlet _ ClassNotFound:" + e.getMessage());
            if(e.getMessage().contains("duplicate")){
                errors.setUsernameIsExisted(username+ "is existed");
                request.setAttribute("CREATE_ERRORS", errors);
                url = ERROR_PAGE;
            }
            
        }finally {
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
