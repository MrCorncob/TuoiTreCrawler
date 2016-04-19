<%-- 
    Document   : index
    Created on : Jun 4, 2015, 2:13:57 AM
    Author     : Corncob
--%>

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
    
    long _page = 1;
    try {
        _page = Integer.parseInt(request.getParameter("page"));
    } catch (Exception e) {
    };

    MongoDAO dao = MongoDAO.getInstance();

    long _totalFeedCount = dao.getFeedCount();
    long _pageCount = _totalFeedCount / 50;
    List<Feed> feeds = dao.getFeeds(_page);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
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
                                <i class="fa fa-table"></i> Tổng cộng <%=_totalFeedCount%> bài viết
                            </li>
                        </ol>
                    </div>
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
                            for(Feed feed:feeds)
                            {
                                %>
                                <tr>
                                    <td><a href="feeddetail.jsp?objectid=<%=feed.getObjectId()%>"><%=feed.getTitle()%></a></td>
                                    <td><a href="<%=feed.getFeedUrl()%>" target="_blank">Link</a></td>
                                    <td><%=feed.getCategory()%></td>
                                    <td><%=feed.getPublishedDate()%></td>
                                    <td><%=feed.getComments().size() %></td>
                                    <td><%=feed.getComments().size()%></td>
                                </tr>
                    
                                <%
                            }
                        %>
                    </tbody>
                </table>
                <%                            if (_totalFeedCount > 0) {
                        out.println("<ul class=\"paging\">");
                        long _minPage = _page > 5 ? _page - 5 : 1;
                        long _maxPage = _page > 5 ? _page+4:10;
                        for (long i = _minPage; i < _maxPage + 1; i++) {
                            if (i != _page) {
                                out.println("<li class=\"arrow\"><a href=\"allfeeds.jsp?page=" + i + "\">" + i + "</a></li>");
                            } else {
                                out.println("<li><span>" + i + "</span></li>");
                            }
                        }

                        out.println("</ul>");
                    }
                %>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        </div>
    </body>
</html>
