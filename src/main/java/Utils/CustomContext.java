package Utils;
import java.util.HashMap;
import java.util.Map;

public class CustomContext {
    private static CustomContext context;
    private Map<String, String> contextMap;

    public static CustomContext getContext() {
        if(context == null){
            context = new CustomContext();
        }
        return context;
    }

    public void addData(String name, String value) {
        contextMap = (contextMap == null) ? new HashMap<>() : contextMap;
        contextMap.put(name, value);
    }

    public String getData(String name){
        return contextMap.get(name);
    }
}
