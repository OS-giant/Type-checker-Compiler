package ast.node.expression;

import ast.node.expression.operators.UnaryOperator;
import visitor.IVisitor;
import visitor.Visitor;

//Line -> operator
public class UnaryExpression extends Expression {

    private UnaryOperator unaryOperator;
    private Expression operand;

    public UnaryExpression(UnaryOperator unaryOperator, Expression operand) {
        this.unaryOperator = unaryOperator;
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }

    public UnaryOperator getUnaryOperator() {
        return unaryOperator;
    }

    public void setUnaryOperator(UnaryOperator unaryOperator) {
        this.unaryOperator = unaryOperator;
    }

    @Override
    public String toString() {
        return "UnaryExpression " + unaryOperator.name();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}