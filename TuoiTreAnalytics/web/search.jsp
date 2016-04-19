<%-- 
    Document   : index
    Created on : Jun 4, 2015, 2:13:57 AM
    Author     : Corncob
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.fit.tuoitre.analysis.POJO.Feed"%>
<%@page import="com.fit.tuoitre.analysis.DAO.MongoDAO"%>
<%@page import="com.fit.tuoitre.analysis.POJO.User"%>

<%
    request.setCharacterEncoding("utf-8");
    User user = (User) session.getAttribute("user");
    if (user == null) {
        String site = new String("login.jsp");
        response.sendRedirect(site);
        return;
    }

    String keyword = "";
    List<Feed> feeds;
    MongoDAO dao = MongoDAO.getInstance();
    try {
        keyword = request.getParameter("keyword");
    } catch (Exception e) {
        keyword = "";
    };
    if (keyword == null) {
        keyword = "";
        feeds = new ArrayList<Feed>();
    }
    else{
        feeds = dao.getFeeds(keyword);
    }
    

    long _totalFeedCount = dao.getFeedCount();
    long _pageCount = _totalFeedCount / 50;
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TuoiTreAnalysis - Tất Cả Bài Viết</title>
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
                                DANH SÁCH BÀI VIẾT
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-table"></i> Từ khóa <%=keyword%>
                                </li>
                            </ol>
                        </div>
                    </div>
                    <div class="text-center">
                        <form action="search.jsp" class="form-inline" method="post">
                            <input size=40 type="text" class="form-control" name="keyword">
                            <input type="submit" value="Tìm Kiếm" class="form-control">
                        </form>
                    </div>

                    <table class="table table-responsive table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Tên Bài Viết</th>
                                <th>Link Gốc</th>
                                <th>Chuyên Mục</th>
                                <th>Ngày Đăng</th>
                                <th>Số lượng thảo luận</th>
                                <th>Số người tham gia thảo luận</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Feed feed : feeds) {
                            %>
                            <tr>
                                <td><a href="feeddetail.jsp?objectid=<%=feed.getObjectId()%>"><%=feed.getTitle()%></a></td>
                                <td><a href="<%=feed.getFeedUrl()%>" target="_blank">Link</a></td>
                                <td><%=feed.getCategory()%></td>
                                <td><%=feed.getPublishedDate()%></td>
                                <td><%=feed.getComments().size()%></td>
                                <td><%=feed.getComments().size()%></td>
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
