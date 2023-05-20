package ast.node;

import visitor.IVisitor;
import visitor.Visitor;

public abstract class Node {

    private int line;

    public void setLine(int line_num) {
        this.line = line_num;
    }

    public int getLine() {
        return this.line;
    }

    public abstract String toString();
    public abstract <T> T accept(IVisitor<T> visitor);
}
