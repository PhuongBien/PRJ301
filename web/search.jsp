

<%--<%@page import="java.util.List"%>
<%@page import="ngoctb.registration.RegistrationDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            <input type="submit" value="Logout" name="btAction" />
        </form>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction"/>
        </form> <br/>
        
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    
                                    <input type="text" name="txtPassword" value=" ${dto.password}" />
                                </td>
                                <td>
                                    
                                    <input type="text" name="txtLastname" value="${dto.fullName}" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}"> 
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>                                
                                <td>
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h2>
                    <font color="red">
                        No record is matched!!
                    </font>
                </h2>
            </c:if>
        </c:if>
        <%--<%
        //1. get all cookies
        Cookie[] cookies = request.getCookies();
        //2. check existed cookies
        if(cookies !=null){
        Cookie recentCookie = cookies[cookies.length - 1];
                  String username = recentCookie.getName();
                  %>
                  <font color="red" >
                      Welcome, <%= username %>
                  </font>
                  <%
        }
        %>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue") %>" /> <br/>
            <input type="submit" value="Search" name="btAction"/>
        </form><br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue != null){
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                
                if ( result !=null){//have value
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                            for(RegistrationDTO dto : result){
                                String urlRewriting = "DispatchServlet"
                                        + "?btAction=delete"
                                        +"&pk=" + dto.getUsername()
                                        + "&lastSearchValue=" + searchValue;
                            
                                %>
                            <tr>
                                <td>
                                    <%= ++count %>
                                    .</td>
                                <td>
                                <%= dto.getUsername() %>
                                <input type ="hidden" name="txtUsername" value="<%= dto.getUsername() %>" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="<%= dto.getPassword() %>"/>
                                </td>
                                <td>
                                    <input type="text" name="txtLastname" value="<%= dto.getFullName() %>" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ADMIN" <%= dto.isRole()? "checked":"" %>/>
                                </td>
                                <td>
                                    <a href="<%= urlRewriting %>">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="txtSearchValue" value="<%= searchValue %>"/>
                                    <input type="submit" name="btAction" value="Update"/>
                                </td>
                            </tr>
                            <%
                            }// process each dto
                            %>
                        </tbody>
                    </table>

        <%
                }else { //no result
                    %>
                    <h2>
                        No record is matched!!
                    </h2>
        <%
                }
            }//not access directly
        %>
        --%>
    </body>
</html>
