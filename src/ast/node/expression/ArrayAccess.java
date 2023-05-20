package ast.node.expression;

import visitor.IVisitor;

//Line -> IDENTIFIER
public class ArrayAccess extends Variable{
    private Expression arrayIndex;

    public ArrayAccess(String name, Expression expression) {
        this.name = name;
        this.arrayIndex = expression;
    }

    public Expression getIndex() {
        return arrayIndex;
    }

    public void setIndex(Expression expression) {
        this.arrayIndex = expression;
    }

    @Override
    public String toString() {
        return "ArrayAccess " + name;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
