package l20n.compiler.ast;

public class StringValueNode implements ValueNode {
    
    private final String value;
    
    private Node parent;

    public StringValueNode(String value) {
        this.value = value;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getValue() {
        return value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    
}
