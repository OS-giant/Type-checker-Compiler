package ast.node.expression;

import visitor.IVisitor;

//Line -> QUESTIONMARK
public class QueryExpression extends Expression{

    private Identifier predicateName;
    private Variable var;

    public QueryExpression(Identifier predicateName) {
        this.predicateName = predicateName;
    }

    public Identifier getPredicateName() {
        return predicateName;
    }

    public void setPredicateName(Identifier predicateName) {
        this.predicateName = predicateName;
    }

    public Variable getVar() {
        return var;
    }

    public void setVar(Variable var) {
        this.var = var;
    }


    @Override
    public String toString() {
        return "QueryExpression";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
