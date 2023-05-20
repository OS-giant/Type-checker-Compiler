package symbolTable.symbolTableItems;

import ast.node.declaration.ArgDeclaration;
import ast.node.declaration.FuncDeclaration;
import ast.node.statement.ForloopStmt;
import ast.type.Type;
import symbolTable.SymbolTable;

import java.util.ArrayList;

public class ForLoopItem extends SymbolTableItem{
    protected SymbolTable ForLoopSymbolTable;
    protected ForloopStmt forloopStmt;
    public static final String STARTKEY = "ForLoop_";

    public ForLoopItem(String name) {
        this.name = name;
    }

    public ForLoopItem(ForloopStmt forloopStmt)
    {
        this.name = forloopStmt.toString();
        this.forloopStmt = forloopStmt;
    }

    public SymbolTable getForLoopSymbolTable()
    {
        return this.ForLoopSymbolTable;
    }

    @Override
    public String getKey() {
        return FunctionItem.STARTKEY + this.name;
    }
}
