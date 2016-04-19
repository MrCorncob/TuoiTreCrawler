<%-- 
    Document   : index
    Created on : Jun 4, 2015, 2:13:57 AM
    Author     : Corncob
--%>

<%@page import="com.fit.tuoitre.analysis.POJO.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.fit.tuoitre.analysis.POJO.Feed"%>
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
    
    int objectId = 0;
    try {
        objectId = Integer.parseInt(request.getParameter("objectid"));
    } catch (Exception e) {
    };

    MongoDAO dao = MongoDAO.getInstance();
    Feed feed = dao.getFeed(objectId);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>TuoiTreAnalysis - Chi Tiết Bài Viết</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/sb-admin.css" rel="stylesheet">
        <link href="css/plugins/morris.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Morris Charts JavaScript -->
        <script type="text/javascript" src="js/jquery.tablesorter.js"></script> 
        <script>
            $(document).ready(function ()
            {
                $("#myTable").tablesorter();
            }
            );
            $(document).ready(function ()
            {
                $("#myTable").tablesorter({sortList: [[0, 0], [1, 0]]});
            }
            );
        </script>
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
                                Chi tiết Bài Viết
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
                    <ul class>
                        <li><label>Tên Bài Viết:</label><%=feed.getTitle()%></li>
                        <li><label>Link Gốc: </label><a href="<%=feed.getFeedUrl()%>"><%=feed.getFeedUrl()%></a></li>
                        <li><label>Chuyên Mục: </label><%=feed.getCategory()%></li>
                        <li><label>Ngày Đăng: </label><%=feed.getPublishedDate()%></li>
                        <li><label>Số lượng thảo luận: </label><%=feed.getCommentsCount()%></li>
                        <li><label>Số người tham gia thảo luận: </label><%=feed.getCommentsCount()%></li>
                            <li><label>Tags: </label><% for (String tag : feed.getTags()) {
                                out.print(tag + ", ");
                            } %></li>

                    </ul>
                    <table id="myTable" class="tablesorter table table-responsive table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Tên Người Thảo Luận</th>
                                <th>Thời Gian</th>
                                <th>Số Lượng Like</th>
                                <th>Nội Dung</th>
                                <th>Spam</th>
                                <th>Ý kiến</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Comment comment : feed.getComments()) {
                            %>
                            <tr>
                                <td><%=comment.getAuthor()%></td>
                                <td><%=comment.getTime()%></td>
                                <td><%=comment.getLikeCount()%></td>
                                <td><%=comment.getContent()%></td>
                                <td>Không</td>
                                <td>Trung lập</td>
                            </tr> 
                            <%
                                }
                            %>
                        </tbody>
                    </table>

                    <!-- /.container-fluid -->
                </div>
                <!-- /#page-wrapper -->
            </div>
        </div>
    </body>
</html>
