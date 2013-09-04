package l20n.compiler.ast;

/**
 *  Represents an entity. 
 *
 */
public class EntityNode implements EntryNode {
    
    /**
     * The identifier of this entity.
     */
    private final String id;
    
    private final IndexNode index;
    
    /**
     * The string value of this entity.
     */
    private final ValueNode value;

    private Node parent;

    public EntityNode(String id, IndexNode index, ValueNode value) {
        super();
        this.id = id;
        this.index = index;
        this.value = value;
        value.setParent(this);
    }

    public String getId() {
        return id;
    }

    public ValueNode getValue() {
        return value;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(index != null) index.accept(visitor);
        value.accept(visitor);
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }
    

}
