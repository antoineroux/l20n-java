package l20n.compiler.ast;

public class HashItemNode implements Node {
    
    private final int depth;
    
    private final String identifier;
    
    private final ValueNode value;
    
    private Node parent;
    
    private final boolean isDefault;;
    
    public HashItemNode(String identifier, int depth, boolean isDefault, ValueNode value) {
        super();
        this.identifier = identifier;
        this.depth = depth;
        this.isDefault = isDefault;
        this.value = value;
        value.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        value.accept(visitor);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }
    
    public boolean isDefault() {
        return isDefault;
    }

}
