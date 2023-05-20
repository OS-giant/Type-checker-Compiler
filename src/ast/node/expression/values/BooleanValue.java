package ast.node.expression.values;

import visitor.IVisitor;

public class BooleanValue extends Value {
    private boolean constant;

    public BooleanValue(boolean constant) {
        this.constant = constant;
    }

    @Override
    public void negateConstant() {
        constant = !constant;
    }

    public boolean getConstant() {
        return constant;
    }

    public void setConstant(boolean constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return "BooleanValue " + constant;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
