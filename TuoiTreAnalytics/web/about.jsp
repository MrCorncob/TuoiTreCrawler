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

    MongoDAO dao = MongoDAO.getInstance();
    long feedCount = dao.getFeedCount();
    long commentCount = dao.getCommentCount();
    long categoryCount = dao.getCategoryCount();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>TuoiTreAnalysis - Giới Thiệu</title>
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

                <!-- /.container-fluid -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                NHÓM ĐỀ TÀI 1
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-dashboard"></i>  <a href="index.jsp">Dashboard</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-table"></i> Xây dựng công cụ thu thập ý kiến/thảo luận dựa theo chủ
                                    đề trên nền tảng trang tin tức/diễn đàn
                                </li>
                            </ol>
                        </div>
                    </div>
                    <ul>
                        <li>
                            <h3>1.  Giới thiệu</h3>
                            <p>
                                Hiện nay rất nhiều doanh nghiệp/cơ quan quan tâm đến nhận xét/đánh giá của 
                                người dân về  một sản phẩm hay một vấn đề  gì đó. Những phản hồi này giúp 
                                doanh nghiệp/cơ quan có những phản hồi để  tạo ra những kết quả  tích cực/hạn 
                                chế  tiêu cực. Vì vậy,  những đánh giá/thảo luận trên các trang mạng rất hữu ích, 
                                chứa đựng những nội dung giá trị. Việc thu thập các dữ liệu đó càng nhiều, càng 
                                nhanh và các chính xác là một nhu cầu đang được đặt ra cho ngành công nghệ
                                thông tin.
                            </p>
                        </li>
                        <li>
                            <h3>2.  Nội dung chi tiết</h3>
                            <p>
                                Nhóm cần thiết  kế  và xây dựng công cụ  để  lấy các thảo luận (comment) từ  một trong 
                                những trang sau:<br>
                            <ul>
                                <li>+  Trang tin tức:  Vnexpress.net  (2  nhóm),  Dantri.com.vn  (2  nhóm),  Zing.vn  (2  nhóm), 
                                Tuoitre.vn (3 nhóm)</li>
                                <li>
                                    + Diễn đàn: Webtretho.com (2 nhóm), Tinhte.vn (2 nhóm), lamchame.com (2 nhóm)
                                </li>
                                
                            </ul>
                            Yêu cầu cơ bản:
                            <ul>
                                <li>
                                     Thu thập  và lưu trữ  các nội dung thảo luận, mỗi một thảo luận có các nội dung 
                                sau: Thông tin người gởi thảo luận (Tên, email, phone, giới tính…), nội dung thảo 
                                luận,  thời  gian  gởi  thảo  luận,  vị  trí  (location),  platform  (gửi  bằng  di  động, 
                                destop,…), link gốc của thảo luận, chủ đề của thảo luận.
                                </li>
                                <li>
                                    Truy vấn nội dung dựa theo chủ  đề  -  thời gian: hiển thị  các bài thảo luận, thống 
                                kê số lượng thảo luận, số lượng người tham gia thảo luận, giới tính…
                                </li>
                            </ul>   
                             Yêu cầu mở rộng:   
                             <ul>
                                 <li>Lọc các bài thảo luận spam (các thảo luận không liên quan đến chủ đề, hoặc các 
                                thảo luận bị lặp lại nhiều lần)</li>
                                 <li>Phân loại các thảo luận: đồng tình/không đồng tình/trung tính với chủ  đề  tương 
                                ứng (Opinion mining, Sentiment analysis)</li>
                                 <li>
                                     Thu thập theo chủ đề/điều kiện cho trước.
                                 </li>
                             </ul>
                             Yêu cầu về kỹ thuật:
                             <ul>
                                 <li>Phần thu thập được phát triển dựa trên ngôn ngữ Java (ưu tiên) hoặc C# </li>
                                 <li>Phần truy vấn và hiển thị  được phát triển trên các ngôn ngữ/nền tảng  tùy ý (Ưu 
                                tiên nền tảng Web)</li>
                                 <li>Phần lưu trữ database sử dụng các hệ NoSQL: mongoDB…</li>
                                 <li>Các phần phải được thiết kế thành các Module để có thể tái sử dụng.</li>
                                 
                             </ul>
                            Yêu cầu về tài liệu:
                            <ul>
                                <li>Báo cáo các kết quả đã đạt được, thiết kế của hệ thống</li>
                                <li>Tài liệu hướng dẫn sử dụng, cấu hình… (nên kèm video demo)</li>
                                <li>Báo cáo đánh giá điểm mạnh yếu, hướng phát triển tiếp theo</li>
                                
                            </ul>   
                           
                        </li>
                    </ul>

                </div>
                <!-- /#page-wrapper -->
            </div>
        </div>
    </body>
</html>
