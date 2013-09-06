package l20n.compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import l20n.Entity;
import l20n.Resource;
import l20n.compiler.ast.LolAst;

public class Compiler {
    
    public Map<String, Entity> compile(List<Resource> resources) {
        Map<String, Entity> entities = new HashMap<String, Entity>();
        
        for(Resource resource: resources) {
            Parser parser = new Parser(resource.getInputStream());
            LolAst lolAst = parser.parse();
            
            EntriesBuilder builder = new EntriesBuilder();
            lolAst.accept(builder);
            entities.putAll(builder.getEntities());
        }
        
        return entities;
    }

}
