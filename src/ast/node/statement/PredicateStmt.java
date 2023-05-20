package ast.node.statement;

import ast.node.expression.Identifier;
import ast.node.expression.Variable;
import visitor.IVisitor;


//Line -> identifier
public class PredicateStmt extends Statement {

    private Identifier identifier;
    private Variable var;

    public PredicateStmt(Identifier identifier, Variable var)
    {
        this.identifier = identifier;
        this.var = var;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Variable getVar() {
        return var;
    }

    public void setVar(Variable var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "PredicateStmt";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
