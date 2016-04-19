<%-- 
    Document   : logout
    Created on : Jun 4, 2015, 5:06:50 AM
    Author     : Corncob
--%>

<%@page import="com.fit.tuoitre.analysis.POJO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("user");
    if (user != null) {
        session.invalidate();

    }
    String site = new String("login.jsp");
    response.sendRedirect(site);
    
%>
<html>
    
</html>

