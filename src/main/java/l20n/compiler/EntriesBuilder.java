package l20n.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import l20n.Entity;
import l20n.compiler.ast.EntityNode;
import l20n.compiler.ast.HashItemNode;
import l20n.compiler.ast.HashValueNode;
import l20n.compiler.ast.IndexNode;
import l20n.compiler.ast.LolAst;
import l20n.compiler.ast.Node;
import l20n.compiler.ast.StringValueNode;
import l20n.compiler.ast.Visitor;
import l20n.entities.Hash;

public class EntriesBuilder implements Visitor {
    
    private Map<String, Entity> entities = new HashMap<>();
    
    private String currentEntityId = "";
    
    private String currentHashId = "";
    
    private Stack<Hash> hashStack = new Stack<>();
    
    private int currentDepth = 0;

    @Override
    public void visit(LolAst lolAst) {
        // Do nothing
    }

    @Override
    public void visit(EntityNode entityNode) {
        EntityNode entity = (EntityNode) entityNode;
        entities.put(entity.getId(), new Entity(entity.getId()));
        currentEntityId = entity.getId();
    }

    public Map<String, Entity> getEntities() {
        return entities;
    }

    @Override
    public void visit(StringValueNode stringValueNode) {
        if (stringValueNode.getParent() instanceof EntityNode) {
            entities.get(currentEntityId).setStringValue(stringValueNode.getValue());
        }
        else {
            // We are in a hash
            boolean isDefault = ((HashItemNode) stringValueNode.getParent()).isDefault();
            hashStack.peek().addHashItem(currentHashId, stringValueNode.getValue(), isDefault);
        }
    }

    @Override
    public void visit(HashValueNode hashValueNode) {
        currentDepth = hashValueNode.getDepth();
        Hash hash = new Hash();
        Node parent = hashValueNode.getParent();
        
        if (parent instanceof EntityNode) {
            // This is the root of the hash
            entities.get(currentEntityId).setHashValue(hash);
        }
        else {
            // We are already inside a hash, this is a hash item.
            boolean isDefault = ((HashItemNode) parent).isDefault();
            hashStack.peek().addHashItem(currentHashId, hash, isDefault);
        }
        hashStack.push(hash);
    }

    @Override
    public void visit(HashItemNode hashItemNode) {
        if(hashItemNode.getDepth() < currentDepth) {
            hashStack.pop();
            currentDepth = hashItemNode.getDepth();
        }
        currentHashId = hashItemNode.getIdentifier();
    }

    @Override
    public void visit(IndexNode indexNode) {
        entities.get(currentEntityId).setIndex(indexNode.getExpressions());
    }
    
    

}
