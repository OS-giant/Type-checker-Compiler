package symbolTable.symbolTableItems;

import ast.node.statement.ArrayDecStmt;
import ast.type.Type;

public class ArrayItem extends SymbolTableItem {

    protected Type type;
    protected ArrayDecStmt arrayDeclaration;
    public static final String STARTKEY = "Variable_";

    public ArrayItem(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public ArrayItem(ArrayDecStmt arrayDeclaration)
    {
        this.name = arrayDeclaration.getIdentifier().getName();
        this.type = arrayDeclaration.getType();
        this.arrayDeclaration = arrayDeclaration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        arrayDeclaration.getIdentifier().setName(name);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayDecStmt getArrayDeclaration()
    {
        return arrayDeclaration;
    }

    public void setArrayDeclaration(ArrayDecStmt arrayDeclaration)
    {
        this.arrayDeclaration = arrayDeclaration;
    }

    @Override
    public String getKey() {
        return VariableItem.STARTKEY + this.name;
    }
}