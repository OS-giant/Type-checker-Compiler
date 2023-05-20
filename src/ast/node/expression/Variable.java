package ast.node.expression;

import ast.type.Type;

public abstract class Variable extends Expression{
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String type) {
        this.name = type;
    }

}
