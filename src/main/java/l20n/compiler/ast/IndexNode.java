package l20n.compiler.ast;

import java.util.ArrayList;
import java.util.List;


public class IndexNode implements Node {
    
    private Node parent;

    //private final String value;
    
    /**
     * For now, the expressions are simply strings.
     */
    List<String> expressions = new ArrayList<String>();

    
//    public IndexNode(String valueNode) {
//        this.value = valueNode;
//    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addExpression(String expression) {
        expressions.add(expression);
    }
    
    public List<String> getExpressions() {
        return expressions;
    }
    
}
