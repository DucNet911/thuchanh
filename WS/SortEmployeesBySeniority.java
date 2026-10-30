package WS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortEmployeesBySeniority {
    public static List<EmployeeY> solve(List<EmployeeY> employees) {
        List<EmployeeY> result = new ArrayList<>(employees);

        // Ngay bat dau cang som thi tham nien cang cao.
        // List.sort giu thu tu ban dau khi hai khoa so sanh bang nhau.
        result.sort(Comparator.comparingLong(
                (EmployeeY employee) -> employee.getStartDate()
                        .toGregorianCalendar().getTimeInMillis()));

        return result;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B23DCVT095";
        String qCode = "q2fd0292";

        ObjectService service = new ObjectService();
        SoapObjectService port = service.getSoapObjectServicePort();
        ClientSettings.configure(port);

        List<EmployeeY> data = port.requestListEmployeeY(studentCode, qCode);
        List<EmployeeY> result = solve(data);

        for (EmployeeY employee : result) {
            System.out.println(employee.getName() + " | " + employee.getStartDate());
        }

        port.submitListEmployeeY(studentCode, qCode, result);
        System.out.println("Da gui ket qua.");
    }
}
