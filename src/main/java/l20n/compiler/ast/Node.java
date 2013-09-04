package l20n.compiler.ast;

public interface Node {

    /**
     * A node can be visited in the meaning of the visitor pattern.
     * 
     * @param visitor   The visitor accepted.
     */
    public void accept(Visitor visitor);
    
    public Node getParent();
    
    public void setParent(Node parent);
}
