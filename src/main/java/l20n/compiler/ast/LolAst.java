package l20n.compiler.ast;

import java.util.ArrayList;
import java.util.List;

public class LolAst implements Node {
    
    List<EntryNode> entries = new ArrayList<EntryNode>();
    
    public void addEntry(EntryNode entry) {
        entries.add(entry);
        entry.setParent(this);
    }

    public List<EntryNode> getEntries() {
        return entries;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for(EntryNode entry: entries) {
            entry.accept(visitor);
        }
    }

    /**
     * Always returns null, as this is the top of the tree.
     */
    @Override
    public Node getParent() {
        return null;
    }

    @Override
    public void setParent(Node parent) {
        // Does nothing, as this is always the top of the tree.
    }
    
}
