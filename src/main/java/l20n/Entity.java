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
        // The entity was not correctly initialised.
        if (stringValue == null && hashValue == null) return id;
        
        if(stringValue != null) {
            return stringValue;
        }
        if (index == null) {
            String value = hashValue.getDefault();
            if (value != null) return value;
            else return id;
        }
        
        Object valueFromHash = hashValue;
        for (String expression: index) {
            if (valueFromHash instanceof String) {
                // There is no way to keep going down the hash while we
                // have at least one expression left. This is a dead-end.
                valueFromHash = null;
                break;
            }
            valueFromHash = ((Hash) valueFromHash).get(expression);
        }
        
        if(valueFromHash instanceof String) {
            return (String) valueFromHash;
        }
        else return id;
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
