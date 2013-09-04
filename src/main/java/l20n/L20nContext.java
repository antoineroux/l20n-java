package l20n;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class L20nContext {
    
    Map<String, Locale> locales = new HashMap<String, Locale>();
    
    /**
     * Loads the resource from the class path.
     * 
     * @param resourcePath the path of the resource on the class path.
     * @return the current context instance.
     */
    public L20nContext loadResource(String resourcePath) {
        Locale locale = locales.get("default");
        if (locale == null) {
            locale = new Locale();
            locales.put("default", locale);
        }
        
        InputStream inputStream = this.getClass().getResourceAsStream(resourcePath);
        Resource resource = new Resource(inputStream);
        locale.linkResource(resource);
        return this;
    }
    
    public L20nContext requestLocales() {
        for(Locale locale: locales.values()) {
            locale.compile();
        }
        return this;
    }
    
    public String get(String identifier) {
        
        return locales.get("default").getValue(identifier);
    }

}
