package symbolTable.symbolTableItems;

import ast.node.declaration.ArgDeclaration;
import ast.node.declaration.FuncDeclaration;
import ast.type.Type;
import symbolTable.SymbolTable;

import java.util.ArrayList;

public class FunctionItem extends SymbolTableItem {

    protected ArrayList<Type> argTypes = new ArrayList<>();
    protected SymbolTable functionSymbolTable;
    protected FuncDeclaration functionDeclaration;
    public static final String STARTKEY = "Function_";

    public FunctionItem(String name, ArrayList<Type> argTypes) {
        this.name = name;
        this.argTypes = argTypes;
    }

    public FunctionItem(FuncDeclaration functionDeclaration)
    {
        this.name = functionDeclaration.getName().getName();
        this.argTypes = new ArrayList<>();
        for(ArgDeclaration arg: functionDeclaration.getArgs())
            argTypes.add(arg.getType());
        this.functionDeclaration = functionDeclaration;
    }

    public void setFunctionSymbolTable(SymbolTable symbolTable)
    {
        this.functionSymbolTable = symbolTable;
    }

    public SymbolTable getFunctionSymbolTable()
    {
        return this.functionSymbolTable;
    }

    public void setFunctionDeclaration(FuncDeclaration functionDeclarationDeclaration)
    {
        this.functionDeclaration = functionDeclaration;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
        functionDeclaration.getName().setName( name );
    }

    public FuncDeclaration getHandlerDeclaration()
    {
        return functionDeclaration;
    }

    @Override
    public String getKey() {
        return FunctionItem.STARTKEY + this.name;
    }
}
