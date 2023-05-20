package ast.node.statement;

import ast.node.expression.Expression;
import ast.type.Type;
import visitor.IVisitor;
import ast.node.expression.Identifier;

//Line -> identifier
public class VarDecStmt extends Statement {
    private Identifier identifier;
    private Type type;
    private Expression initialExpression;

    public VarDecStmt(Identifier identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Expression getInitialExpression() {
        return initialExpression;
    }

    public void setInitialExpression(Expression initialExpression) {
        this.initialExpression = initialExpression;
    }

    @Override
    public String toString() {
        return "VarDeclaration";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}