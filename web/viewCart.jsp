

<%@page import="java.util.Map"%>
<%@page import="phuongbtt.cart.CartBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart Includes</h1>
        <%
            if (session != null) {
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    if (cart.getItems() != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <form action="DispatchServlet">
                <%
                    Map<String, Integer> items = cart.getItems();
                    int count = 0;
                    for (String key : items.keySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count%>.
                    </td>
                    <td>
                        <%= key%>
                    </td>
                    <td>
                        <%= items.get(key)%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkItem"
                               value="<%= key%>"/>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <tr>
                    <td colspan ="3"> 
                        <a href="market.html">Add more Books to your Cart</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove Selected Items" name="btAction" />
                    </td>
                </tr>
            </form>
        </tbody>
    </table>
    <%
                    return;
                }//end item
            }//end cart
        }//end of session
%>
    <h2>No Cart is Existed</h2>
    <a href="market.html">Click here to Return</a>
</body>
</html>
