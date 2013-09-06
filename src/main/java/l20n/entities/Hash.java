package l20n.entities;

import java.util.HashMap;
import java.util.Map;


public class Hash {
    
    private Map<String, Object> hash = new HashMap<String, Object>();
    
    private String defaultKey;
    
    public void addHashItem(String id, Object value, boolean isDefault) {
        if(isDefault && defaultKey != null) {
            throw new EntityFormatException(id + "cannot be a default key, " +
            		"there is already another one : " + defaultKey + ".");
        }
        else if (isDefault) {
            defaultKey = id;
        }
        hash.put(id, value);
    }
    
    /**
     * 
     * @param id the element to retrieve.
     * @return a String or another Hash, depending on what is contained in the hash.
     */
    public Object get(String id) {
        return hash.get(id);
    }
    
    
    
    /**
     * Returns the default value of this hash.
     * 
     * @return the default value.
     */
    public String getDefault() {
        if (defaultKey == null) return null;
        
        Object value = hash.get(defaultKey);
        if (value instanceof Hash) {
            return ((Hash) value).getDefault();
        }
        
        return ((String) value);
    }
    
}
