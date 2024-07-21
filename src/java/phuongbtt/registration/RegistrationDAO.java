
package phuongbtt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import phuong.utils.DBHelper;


public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT lastname, isAdmin From Registration Where username = ? And password = ?";
                //3. create Statement Obiject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                // System.out.println(rs.toString());
                //5. Process result
                if (rs.next()) {
//                    result = true;
                    //map
                    //get data from rs
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //set data to DTO
                    result = new RegistrationDTO(username, "", fullname, role);

                }//username and password are verified
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;

    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        RegistrationDAO d = new RegistrationDAO();
//        List<RegistrationDTO> list = d.searchLastname("k");
//        System.out.println(list.toString());
//    }

    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. conect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute query
                rs = stm.executeQuery();

                //5. process result
                while (rs.next()) {
                    //.map
                    //get data from ResultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//account is unavailable
                    this.accounts.add(dto);
                }//process each record
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. create Statement Obiject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4. execute query
                int affectedRow = stm.executeUpdate();
                //5. Process result
                if (affectedRow > 0) {
                    result = true;
                }//username and password are verified
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateRecord(String username, String password, String lastname, boolean role) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            //2. Create SQL String
            String sql = "Update Registration Set"
                    + " password = ?,lastname = ?, isAdmin = ?"
                    + " Where username = ?";
            //3. create Statement Obiject
            stm = con.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, lastname);
            stm.setBoolean(3, role);
            stm.setString(4, username);
            //4. execute query
            int affectedRow = stm.executeUpdate();
            //5. Process result
            if (affectedRow > 0) {
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    public boolean createAccount(RegistrationDTO dto) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. connect DB
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "INSERT into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3. create Statement Obiject
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4. execute query
                int affectedRow = stm.executeUpdate();
                //5. Process result
                if (affectedRow > 0) {
                    result = true;
                }//username and password are verified
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
