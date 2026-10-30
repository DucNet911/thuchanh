package tools;

import WS.CharacterService;
import WS.DataService;
import WS.ObjectService;

/** Loads all three SOAP clients without making any network requests. */
public final class EnvironmentCheck {
    public static void main(String[] args) {
        System.out.println("Java: " + System.getProperty("java.version"));
        new DataService().getSoapDataServicePort();
        System.out.println("OK: DataService");
        new CharacterService().getSoapCharacterServicePort();
        System.out.println("OK: CharacterService");
        new ObjectService().getSoapObjectServicePort();
        System.out.println("OK: ObjectService");
        System.out.println("PASS: SOAP libraries and local WSDL are ready.");
        System.out.println("This check does not contact or submit to the grading server.");
    }
}
