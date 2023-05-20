package symbolTable.symbolTableItems;

import ast.node.declaration.ArgDeclaration;
import ast.node.declaration.FuncDeclaration;
import ast.node.declaration.MainDeclaration;
import ast.type.Type;
import symbolTable.SymbolTable;

import java.util.ArrayList;

public class MainItem extends SymbolTableItem {

    protected SymbolTable mainSymbolTable;
    protected MainDeclaration mainDeclaration;
    public static final String STARTKEY = "MainItem_";

    public MainItem() { }

    public MainItem(MainDeclaration mainDeclaration)
    {
        this.mainDeclaration = mainDeclaration;
    }

    public void setMainItemSymbolTable(SymbolTable symbolTable)
    {
        this.mainSymbolTable = symbolTable;
    }

    public SymbolTable getMainItemSymbolTable()
    {
        return this.mainSymbolTable;
    }

    public void setMainItemDeclaration(MainDeclaration mainDeclaration)
    {
        this.mainDeclaration = mainDeclaration;
    }



    @Override
    public String getKey() {
        return MainItem.STARTKEY;
    }
}
