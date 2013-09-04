package l20n.compiler.ast;

import java.util.ArrayList;
import java.util.List;

public class HashValueNode implements ValueNode {
    
    private final int depth;

    private List<HashItemNode> hashItems = new ArrayList<>();
    
    private Node parent;
    
    public HashValueNode(int depth) {
        this.depth = depth;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for(HashItemNode hashItem: hashItems) {
            hashItem.accept(visitor);
        }
    }
    
    public void addHashItem(HashItemNode hashItem) {
        hashItems.add(hashItem);
        hashItem.setParent(this);
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

}
