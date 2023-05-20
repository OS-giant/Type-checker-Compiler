package symbolTable.symbolTableItems;

import ast.node.statement.VarDecStmt;
import ast.type.Type;

public class VariableItem extends SymbolTableItem {

    protected Type type;
    protected VarDecStmt varDeclaration;
    public static final String STARTKEY = "Variable_";

    public VariableItem(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public VariableItem(VarDecStmt varDeclaration)
    {
        this.name = varDeclaration.getIdentifier().getName();
        this.type = varDeclaration.getType();
        this.varDeclaration = varDeclaration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        varDeclaration.getIdentifier().setName(name);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public VarDecStmt getVarDeclaration()
    {
        return varDeclaration;
    }

    public void setVarDeclaration(VarDecStmt varDeclaration)
    {
        this.varDeclaration = varDeclaration;
    }

    @Override
    public String getKey() {
        return VariableItem.STARTKEY + this.name;
    }
}