
# Đồ Án Web Crawler – Tuổi Trẻ

1. Phân Tích Kĩ Thuật

- Mỗi trang bài viết trên Tuổi Trẻ đều có 1 thuộc tính gọi là object\_id
- Khi có truy vấn từ client, Tuổi Trẻ sẽ trả về html chứa nội dung của bài viết trước
- Phần nội dung bình luận sẽ được load sau khi trang đã load xong bằng ajax. Cấu trúc load bình luận: [http://cm.tuoitre.vn/comment/ttocreateiframe](http://cm.tuoitre.vn/comment/ttocreateiframe)

Kèm theo các tham số:

+ app\_id = 6: Mã của api

+ layout = 3, mặc định

+ sort\_type, date hoặc likeCount

+ objectId, ID của bài viết

+ offset, thứ tự trang

- Dựa vào cú pháp truy vấn trên, ta sẽ gửi về server với thông tin đã lấy được, và tăng dần offset từ 1-> … đến khi nào không còn kết quả trả về nữa
- Khi client gửi truy vấn tới api, server sẽ trả về html chứa nội dung của các bình luận, từ đây ta lấy được các nội dung cần thiết

+ Nội dung bình luận

+ Tên người bình luận

+ Ngày giờ bình luận

+ Lượt thích

- Trong quá trình thực hiện đồ án, Tuổi Trẻ đã có sự thay đổi về mặt kĩ thuật, bổ sung thêm chức năng phản hồi bình luận, rút gọn bình luận bằng nút xem thêm. Do khối lượng công việc khá lớn, nhóm không sửa chữa lại mã nguồn để tổ chức lại việc lưu trữ bình luận được. Chỉ chỉnh sửa mã nguồn để có thể lấy được bình luận hoàn chỉnh

1. Thiết kế hệ thống crawler

- Sử dụng MongoDB để lưu trữ thông tin
- Mã nguồn được lập trình bằng Java
- Sử dụng thư viện crawler4j để tạo crawler
- Sử dụng thư viện jsoup để parse dữ liệu cần thiết từ html
- Kèm theo một số thư viện, driver khác để làm việc với cơ sở dữ liệu

1. 1.Quy trình vận hành hệ thống

- Do Tuổi Trẻ lưu trữ các link cũ và truy vấn thông qua ajax nên số lượng link mà crawler lấy được là không nhiều. Ta tiến hành add seed link mồi cho crawler hoạt động. Các link seed sẽ add:

  controller.addSeed("http://tuoitre.vn/");

        controller.addSeed("http://tuyensinh.tuoitre.vn/");

        controller.addSeed("http://diaoc.tuoitre.vn/");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-1-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-2-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-3-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-4-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-5-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-6-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-7-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-8-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-9-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-10-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-11-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-12-2014");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-1-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-2-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-3-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-4-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-5-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-6-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-7-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-8-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-9-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-10-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-11-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-12-2013");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-1-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-2-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-3-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-4-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-5-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-6-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-7-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-8-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-9-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-10-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-11-2012");

        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-12-2012");

- Overide lại phương thức shouldVisit() để kiểm tra xem link nào cần crawl. Thiết kế filter để bỏ qua các resource tĩnh(css, image, js…) và các link ngoại không phải của Tuổi Trẻ
- Overide lại phương thức visit(Page page). Phương thức này sẽ được gọi khi crawler đã crawl page đó về. Tiến hành phân tích xem link đó có phải là link bài viết hay không. Nếu là link bài viết thì tiến hành xử lí để lọc lấy dữ liệu.

1. 2.Chi tiết quá trình lấy comment

Đối với page đã được crawl về, khởi tạo đối tượng mới của class FeedParser và truyền vào nội dung html, class này sẽ đảm nhận việc lọc lấy dữ liệu và crawl comment

- Đầu tiên, lọc lấy các dữ liệu cơ bản có thể lấy được từ html của page đã được crawl về bằng jsoup
- Từ objectId lấy được, khởi tạo mới đối tượng của class CommentParser và truyền vào object\_id để lấy comment
- Class CommentParser sẽ áp dụng kĩ thuật đã nêu ở bên trên để truy vấn lấy dữ liệu lần lượt, lọc lấy comment tới khi đã lấy hết.

1. 3.Lưu trữ dữ liệu

Sử dụng class MongoDAO để thêm mới feed vừa lấy được vào cơ sở dữ liệu

1. 4.Tiếp tục lặp lại quá trình cho đến khi crawl đủ số lượng page hoặc crawler đã crawl hết các link đã tìm thấy

1. Thiết kế hệ thống truy vấn dữ liệu

Hệ thống truy vấn được lập trình bằng ngôn ngữ JSP, sử dụng lại các module, class của crawler đã được thiết kế để truy vấn và hiển thị dữ liệu. Đăng nhập vào hệ thống với username là admin và password là abcxyz

1. Hướng dẫn cài đặt và sử dụng

Cài đặt hệ thống cơ sở dữ liệu MongoDB

Tạo mới cơ sở dữ liệu tên tuoitre

Thêm mới collection tên feeds

Từ file jar đã được build, gọi lệnh sau từ command line:

java –jar TuoiTreCrawler.jar <thư mục lưu cache> <số lượng thread>

Trong đó:

 <thư mục lưu cache> là thư mục để lưu cache cho crawler hoạt động. VD: e:/crawl

 <số lượng thread> là số lượng thread chạy đồng thời để crawl dữ liệu

Có thể xem Video demo đính kèm để biết rõ chi tiết.

Link Video: https://www.youtube.com/watch?v=aQUHFwZbiX8

1. Kết quả đạt được
 ![](data:image/*;base64,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)
- Cài đặt hoàn chỉnh hệ thống bằng Java, MongoDB và JSP
- Lấy đầy đủ dữ liệu yêu cầu, lưu trữ, truy vấn tốt
- Thời gian thu thập nhanh. Thực hiện thử nghiệm crawl trong vòng 25p thu được 621 bài viết và 1011 bình luận trên tốc độ mạng trung bình.
- Hệ thống gọn nhẹ, dễ triển khai, cơ sở dữ liệu được thiết kế gọn nhẹ tối đa, chiếm ít dung lượng lưu trữ. Kết quả thu thập được 4014 bài viết với tổng số 13065 bình luận chỉ chiếm 9.71Mb dung lượng lưu trữ database.
- Phần truy vấn dữ liệu được thiết kế bằng JSP, trực quan, dễ triển khai, sử dụng. Tận dụng lại mã nguồn sẵn có của crawler.
