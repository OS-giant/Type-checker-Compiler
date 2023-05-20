package ast.node.expression.values;

import ast.type.Type;
import ast.node.expression.Expression;

public abstract class Value extends Expression {
    protected Type type;

    public void negateConstant() {}

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}