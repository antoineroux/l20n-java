package l20n.compiler.ast;

public interface Visitor {

    public void visit(LolAst lolAst);
    
    public void visit(EntityNode entityNode);
    
    public void visit(StringValueNode stringValueNode);
    
    public void visit(HashValueNode hashValueNode);
    
    public void visit(HashItemNode hashItemNode);
    
    public void visit(IndexNode indexNode);
    
}
