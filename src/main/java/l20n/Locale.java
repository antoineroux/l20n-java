package l20n;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import l20n.compiler.Compiler;


public class Locale {
    
    private List<Resource> resources = new ArrayList<Resource>();
    
    private Map<String, Entity> entities = new HashMap<String, Entity>();
    
    public void compile() {
        Compiler compiler = new Compiler();
        entities.putAll(compiler.compile(resources));
    }
    
    
    public void linkResource(Resource resource) {
        resources.add(resource);
    }
    
    public String getValue(String id) {
        Entity entity = entities.get(id);
        if (entity == null) return id;
        
        return entity.getValue();
    }

}
