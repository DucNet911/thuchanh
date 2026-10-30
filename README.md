# WS — 6 bài tập — B23DCVT095

Bộ này đã có mã nguồn bài làm, các lớp SOAP client, đối tượng dữ liệu và toàn bộ thư viện SOAP cần dùng. Dùng JDK 21 đã cài trên máy. Không cần JDK 8, Maven, chạy `wsimport` hoặc tải thêm thư viện.

## Chạy ngay trên Windows

1. Giải nén **toàn bộ ZIP**. Mở thư mục `WS_Project` vừa giải nén.
2. Nhấp đúp `RUN.cmd`.
3. Chờ `BUILD OK`, rồi nhấn số **1–6** tương ứng với bài muốn chạy.

`RUN.cmd` tự tìm JDK, biên dịch và hiện menu. Nó ưu tiên JDK tại `C:\Program Files\Java\jdk-21`, sau đó tìm trong các vị trí cài phổ biến và PATH. Mã sinh viên đã là **B23DCVT095** trong cả sáu bài.

Chọn **7** để kiểm tra các thư viện và ba SOAP client mà không kết nối server. Chọn **0** để thoát. Chọn **1–6** sẽ nhận dữ liệu, tính toán và gửi kết quả cho server của đề, nên cần kết nối mạng và server hoạt động.

Sau khi sửa code, lưu file rồi đóng và mở lại `RUN.cmd` để biên dịch bản mới.

## Chạy trong VS Code

Chọn **File → Open Folder → WS_Project** — mở thư mục có `RUN.cmd`, không mở riêng thư mục con `WS`.

Mở Terminal PowerShell và chạy:

```powershell
.\RUN.cmd
```

Cách này không cần extension Java. Nếu đã có **Extension Pack for Java**, bạn cũng có thể nhấn **F5** và chọn tên bài. Các cấu hình thư viện, build và debug đã được kèm trong `.vscode`.

## Các bài và mã câu hỏi

| Menu | File trong `WS` | Mã câu hỏi | Thuật toán |
| --- | --- | --- | --- |
| 1 | `LargestNumber.java` | `U1Q1BuI6` | Sắp xếp bằng cách so sánh hai cách nối chuỗi |
| 2 | `SumArray.java` | `a9bSLb7z` | Cộng các phần tử, kiểm tra giới hạn int |
| 3 | `RotateCharacters.java` | `AN44ec0F` | Xoay phải theo mã ký tự đầu modulo độ dài |
| 4 | `GroupByVowelCount.java` | `rGybEkjd` | Nhóm theo số lần xuất hiện nguyên âm |
| 5 | `SortEmployeesBySeniority.java` | `q2fd0292` | Ngày bắt đầu tăng dần, giữ thứ tự khi bằng nhau |
| 6 | `FilterPotentialCustomers.java` | `bxMWQ6mq` | `totalSpent > 5000` và `purchaseCount >= 5` |

Bài nhóm nguyên âm giữ cách xử lý đã thống nhất: đếm `a/e/i/o/u` không phân biệt hoa thường; các nhóm theo số nguyên âm tăng dần, từ trong nhóm theo thứ tự từ điển, nối bằng `", "`. Ví dụ trong đề có chỗ mâu thuẫn: `banana` có 3 nguyên âm; `apple`, `grape`, `kiwi`, `pear` có 2. Chưa xác nhận cách chấm của server đối với chỗ mâu thuẫn đó.

## Thành phần đã có sẵn

| Vị trí | Nội dung |
| --- | --- |
| `WS/` | 6 bài, 86 file SOAP được sinh từ WSDL, và `ClientSettings.java` |
| `lib/` | 23 JAR từ JAX-WS RI 2.3.7, gồm SOAP, JAXB, các API và công cụ |
| `wsdl/` | Bản WSDL/XSD cục bộ của cả ba dịch vụ và WSDL gộp |
| `RUN.cmd` | Tự biên dịch và mở menu chạy bài |
| `BUILD.cmd` | Chỉ biên dịch, không gửi dữ liệu |
| `tools/` | Tìm JDK và kiểm tra thư viện |
| `.vscode/` | Cấu hình cho VS Code |
| `licenses/` | Thông báo bản quyền và giấy phép thư viện |

Các lớp dịch vụ dùng WSDL cục bộ qua constructor `new DataService()`, `new CharacterService()` và `new ObjectService()`. Địa chỉ gửi/nhận SOAP vẫn là server của đề. `BUILD.cmd` tự chép tài nguyên WSDL vào `out/wsdl`; không cần tự sửa classpath.

Giữ nguyên các thư mục `WS`, `lib` và `wsdl` trong bộ này. Nếu thay file bài làm bằng bản cũ ở chat, bản cũ dùng URL WSDL từ mạng, nên sẽ tải WSDL khi chạy.

## Nếu gặp lỗi

| Thông báo | Xử lý |
| --- | --- |
| Không tìm thấy JDK 21 | Dùng lệnh bên dưới để chỉ rõ JDK đã cài |
| Thiếu `javax.xml.ws`, `javax.xml.bind` hoặc lớp SOAP | Giải nén đủ ZIP và chạy qua `RUN.cmd` |
| Cannot find `/wsdl/AllServices.wsdl` | Chạy lại `BUILD.cmd`; không xóa thư mục `wsdl` |
| Connection refused / timed out | Thư viện đã nạp; kiểm tra mạng và server của đề |

Chỉ cần dùng phần sau nếu JDK được cài ở vị trí mà script chưa tìm thấy:

```powershell
$env:WS_JAVA_HOME = 'C:\Program Files\Java\jdk-21'
.\RUN.cmd
```

Thay đường dẫn bằng vị trí thật nếu khác. Biến này chỉ dùng trong terminal hiện tại.

## Kiểm tra bản đóng gói

- Biên dịch toàn bộ mã nguồn bằng JDK 21.0.13 trên Linux.
- Khởi tạo đủ ba SOAP client từ WSDL cục bộ.
- Đạt 14 kiểm tra thuật toán, bao gồm biên tổng int, toàn số 0, ngày bằng nhau và ngưỡng lọc khách hàng.
- Chạy cả sáu hàm `main` với server SOAP thử nghiệm cục bộ: 6 lần nhận và 6 lần gửi; kiểm tra đúng mã sinh viên, mã câu hỏi, kết quả và dữ liệu đối tượng.
- Chưa chạy các file `.cmd` trực tiếp trên Windows và chưa gửi bài lên server chấm thật.

Nguồn và phiên bản thư viện: xem `DEPENDENCIES.md`.
