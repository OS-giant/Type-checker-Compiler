package compileError.Name;

import compileError.CompileError;

public class FunctionRedefinition extends CompileError {
    int line;
    String name;
    public FunctionRedefinition(int line, String name){
        this.line = line;
        this.name = name;
    }

    public String getMessage(){
        return "Line " + line + ": Redefinition of function " + name;
    }
}
