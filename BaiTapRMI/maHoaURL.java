package BaiTapRMI;
import RMI.CharacterService;
import java.net.URI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.lang.String;


public class maHoaURL {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
        CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

        String studentCode = "B23DCVT095";
        String qCode = "mtGlg615";

        String data = service.requestCharacter(studentCode, qCode);
        String result = new URI(null, null, data, null).toASCIIString();

        service.submitCharacter(studentCode, qCode, result);
    }
}