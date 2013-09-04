package l20n;

import java.util.List;

import l20n.entities.Hash;

public class Entity {
    
    private final String id;
    
    private String stringValue;
    
    private List<String> index;
    
    private Hash hashValue;
    
    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        if (stringValue == null && hashValue == null) return id;
        
        if(stringValue != null) {
            return stringValue;
        }
        if (index == null) {
            String value = hashValue.getDefault();
            if (value != null) return value;
            else return id;
        }
        
        String value = null; 
        
            
        Object valueFromHash = hashValue;
        for (String expression: index) {
            if (valueFromHash instanceof String) {
                // There is no way to keep going down the hash while we
                // have at least on expression left. This is a dead-end.
                valueFromHash = null;
                break;
            }
            valueFromHash = ((Hash) valueFromHash).get(expression);
        }
        
        if(valueFromHash instanceof String) {
            value = (String) valueFromHash;
        }
        
        // If there is no default value.
        if (value == null) return id;
        
        return value;
    }
    
    public void setStringValue(String value) {
        this.stringValue = value;
    }
    
    public void setHashValue(Hash hash) {
        this.hashValue = hash;
    }
    
    public void setIndex(List<String> index) {
        this.index = index;
    }

}
