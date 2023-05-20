package compileError.Type;

import compileError.CompileError;

public class ConditionTypeNotBool extends CompileError {
    int line;
    public ConditionTypeNotBool(int line){
        this.line = line;
    }
    public String getMessage(){
        return "Line " + line + ": condition type must be Boolean";
    }

}
