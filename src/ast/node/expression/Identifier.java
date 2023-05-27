package ast.node.expression;

import visitor.IVisitor;

//Line -> IDENTIFIER
public class Identifier extends Variable {
    public boolean iam_function_variable = false;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Identifier " + name;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
