package ast.node.declaration;

import ast.type.Type;
import visitor.IVisitor;
import ast.node.expression.Identifier;

//Line -> identifier
public class ArgDeclaration extends Declaration {
    private Identifier identifier;
    private Type type;

    public ArgDeclaration(Identifier identifier, Type type) {
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

    @Override
    public String toString() {
        return "ArgDeclaration " + type.toString();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}