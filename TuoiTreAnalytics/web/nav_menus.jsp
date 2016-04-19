<%-- 
    Document   : nav_menus
    Created on : Jun 4, 2015, 2:18:08 AM
    Author     : Corncob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.jsp">Tuoi Tre Analysis</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Quản Trị Viên <b class="caret"></b></a>
            <ul class="dropdown-menu">
                
                <li>
                    <a href="logout.jsp"><i class="fa fa-fw fa-power-off"></i> Đăng Xuất </a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <li class="active">
                <a href="index.jsp"><i class="fa fa-fw fa-dashboard"></i> Trang Tổng Quan</a>
            </li>
            <li>
                <a href="allfeeds.jsp"><i class="fa fa-fw fa-bar-chart-o"></i> Danh Sách Bài Viết</a>
            </li>
            <li>
                <a href="search.jsp"><i class="fa fa-fw fa-bar-chart-o"></i> Tìm Kiếm</a>
            </li>
            <li>
                <a href="members.jsp"><i class="fa fa-fw fa-edit"></i> Thành Viên Nhóm</a>
            </li>
            <li>
                <a href="about.jsp"><i class="fa fa-fw fa-desktop"></i> Giới Thiệu</a>
            </li>
            
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>
