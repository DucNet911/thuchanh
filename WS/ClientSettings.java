package WS;

import javax.xml.ws.BindingProvider;

/** Shared connection settings for the bundled Metro SOAP client. */
public final class ClientSettings {
    private ClientSettings() { }

    public static void configure(Object port) {
        BindingProvider provider = (BindingProvider) port;
        provider.getRequestContext().put("com.sun.xml.ws.connect.timeout", 10000);
        provider.getRequestContext().put("com.sun.xml.ws.request.timeout", 30000);
    }
}
