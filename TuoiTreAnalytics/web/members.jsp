<%-- 
    Document   : index
    Created on : Jun 4, 2015, 2:13:57 AM
    Author     : Corncob
--%>

<%@page import="com.fit.tuoitre.analysis.DAO.MongoDAO"%>
<%@page import="com.fit.tuoitre.analysis.POJO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    User user = (User) session.getAttribute("user");
    if (user == null) {
        String site = new String("login.jsp");
        response.sendRedirect(site);
        return;
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>TuoiTreAnalysis - Thành Viên</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/sb-admin.css" rel="stylesheet">
        <link href="css/plugins/morris.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Morris Charts JavaScript -->
        <script src="js/plugins/morris/raphael.min.js"></script>
        <script src="js/plugins/morris/morris.min.js"></script>
        <script src="js/plugins/morris/morris-data.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <!--Nav Menus -->
            <%@ include file="nav_menus.jsp" %>

            <!--Page wrapper -->
            <div id="page-wrapper">
                <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Danh Sách Thành Viên
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Tables
                            </li>
                        </ol>
                    </div>
                </div>
                <ul>
                    <li>
                        1212376 – Đinh Công Thắng
                    </li>
                    <li>1212429 – Ngô Nguyễn Thảo Trang</li>
                    <li>
                        1212536 – Đặng Nhựt Yên
                    </li>
                    <li>1212537 – Lê Vũ Đại Yên
                    </li>
                </ul>
                <!-- /.container-fluid -->
            </div>
        </div>
        <!-- /#page-wrapper -->
    </div>
</body>
</html>
