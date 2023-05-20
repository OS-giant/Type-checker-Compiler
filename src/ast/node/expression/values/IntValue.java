package ast.node.expression.values;

import visitor.IVisitor;

public class IntValue extends Value {
    private int constant;

    public IntValue(int constant) {
        this.constant = constant;
    }

    public int getConstant() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant = constant;
    }

    @Override
    public void negateConstant() {
        constant = - constant;
    }

    @Override
    public String toString() {
        return "IntValue " + constant;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
