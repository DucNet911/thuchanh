package WS;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortEmployeesBySeniority {

    public static List<EmployeeY> solve(List<EmployeeY> employees) {
        List<EmployeeY> result = new ArrayList<>(employees);

        // Ngày bắt đầu tăng dần = thâm niên giảm dần.
        // Giữ nguyên thứ tự nếu hai giá trị ngày bằng nhau.
        result.sort(Comparator.comparingLong(
                (EmployeeY employee) -> employee.getStartDate()
                        .toGregorianCalendar()
                        .getTimeInMillis()));

        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "q2fd0292";

        ObjectService service = new ObjectService(new URL(
                "http://36.50.135.242:2221/ObjectService?wsdl"));

        SoapObjectService port =
                service.getSoapObjectServicePort();

        List<EmployeeY> data =
                port.requestListEmployeeY(studentCode, qCode);

        List<EmployeeY> result = solve(data);

        for (EmployeeY employee : result) {
            System.out.println(
                    employee.getName()
                    + " | "
                    + employee.getStartDate());
        }

        port.submitListEmployeeY(studentCode, qCode, result);

        System.out.println("Da gui ket qua.");
    }
}s