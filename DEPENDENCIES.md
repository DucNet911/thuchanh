# Thư viện SOAP đi kèm

Nguồn: bản phân phối chính thức **JAX-WS RI 2.3.7** trên Maven Central:

https://repo.maven.apache.org/maven2/com/sun/xml/ws/jaxws-ri/2.3.7/jaxws-ri-2.3.7.zip

Đã chép nguyên 23 JAR nằm trực tiếp trong `jaxws-ri/lib` của bản phân phối. Không cần tải URL này để chạy project.

SHA-256 của bản phân phối gốc:

`809d975efc729c3624738a0b7109d5a1d82d405cdf5fc50beebdf884c3a449eb`

Các tên JAR bắt đầu bằng `jakarta` thuộc dòng phiên bản này vẫn cung cấp API `javax.*` mà các SOAP client được sinh ra đang dùng. Giữ đúng cả bộ phiên bản đi kèm khi chạy project.

| JAR | Phiên bản ghi trong manifest |
| --- | --- |
| `FastInfoset.jar` | 1.2.18 |
| `gmbal-api-only.jar` | 4.0.3 |
| `ha-api.jar` | 3.1.13 |
| `jakarta.activation.jar` | 1.2.2 |
| `jakarta.annotation-api.jar` | 1.3.5 |
| `jakarta.jws-api.jar` | 2.1.0 |
| `jakarta.mail.jar` | 1.6.7 |
| `jakarta.xml.bind-api.jar` | 2.3.3 |
| `jakarta.xml.soap-api.jar` | 1.4.2 |
| `jakarta.xml.ws-api.jar` | 2.3.3 |
| `jaxb-impl.jar` | 2.3.9 |
| `jaxb-jxc.jar` | 2.3.9 |
| `jaxb-xjc.jar` | 2.3.9 |
| `jaxws-rt.jar` | 2.3.7 |
| `jaxws-tools.jar` | 2.3.7 |
| `management-api.jar` | 3.2.3 |
| `mimepull.jar` | 1.9.15 |
| `policy.jar` | 2.7.10 |
| `saaj-impl.jar` | 1.5.3 |
| `stax-ex.jar` | 1.8.3 |
| `stax2-api.jar` | 4.2.2 |
| `streambuffer.jar` | 1.5.10 |
| `woodstox-core.jar` | 6.5.1 |

Thông báo giấy phép gốc được giữ bên trong từng JAR và trích thêm vào `licenses/`. SHA-256 từng JAR nằm trong `lib/SHA256SUMS.txt`.

WSDL/XSD được tải từ ba endpoint của đề vào ngày 30/10/2026:

- http://36.50.135.242:2221/DataService?wsdl
- http://36.50.135.242:2221/CharacterService?wsdl
- http://36.50.135.242:2221/ObjectService?wsdl

`AllServices.wsdl` gộp mô tả của ba dịch vụ có cùng namespace; các địa chỉ SOAP và hợp đồng dữ liệu được giữ nguyên. Các lớp được sinh cùng một lần để `WS.ObjectFactory` chứa đủ kiểu dữ liệu của cả ba dịch vụ. Schema import/include chỉ tham chiếu file cục bộ.
