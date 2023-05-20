package ast.node.statement;

import ast.node.expression.Expression;
import visitor.IVisitor;

//Line -> RETURN
public class ReturnStmt extends Statement {

    private Expression expression;

    public ReturnStmt(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "ReturnStmt";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
        }

}
