package BaiTapRMI;
import RMI.ObjectService;
import RMI.TicketSla;
import java.lang.String;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class xuLyTicketSla {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

        String studentCode = "B23DCVT095";
        String qCode = "vZQqYU12";

        TicketSla ticket = (TicketSla) service.requestObject(studentCode, qCode);
        String priority = ticket.getPriority();
        int hours = ticket.getOpenedHoursAgo();

        boolean breached = ("CRITICAL".equals(priority) && hours > 2)
                || ("HIGH".equals(priority) && hours > 8)
                || ("MEDIUM".equals(priority) && hours > 24)
                || ("LOW".equals(priority) && hours > 72);

        ticket.setBreached(breached);

        if (!breached) {
            ticket.setAction("MONITOR");
        } else if ("CRITICAL".equals(priority) || hours > 96) {
            ticket.setAction("ESCALATE_L2");
        } else {
            ticket.setAction("ESCALATE_L1");
        }

        service.submitObject(studentCode, qCode, ticket);
    }
}