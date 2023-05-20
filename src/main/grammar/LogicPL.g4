grammar LogicPL;

@header{
import ast.node.*;
import ast.node.expression.*;
import ast.node.statement.*;
import ast.node.declaration.*;
import ast.node.expression.values.*;
import ast.node.expression.operators.*;
import ast.type.primitiveType.*;
import ast.type.*;
}

program returns[Program p]:
    {$p = new Program(); $p.setLine(0);}
    (f = functionDec {$p.addFunc($f.functionDeclaration);})*
    main = mainBlock {$p.setMain($main.main) ;}
    ;

functionDec returns[FuncDeclaration functionDeclaration]:
    {ArrayList<ArgDeclaration> args = new ArrayList<>();
     ArrayList<Statement> statements = new ArrayList<>();}
    FUNCTION name = identifier
    LPAR (arg1 = functionVarDec {args.add($arg1.argDeclaration);} (COMMA arg = functionVarDec {args.add($arg.argDeclaration);})*)? RPAR COLON returnType = type
    LBRACE ((stmt = statement {statements.add($stmt.statementRet);})+) RBRACE
    {$functionDeclaration = new FuncDeclaration($name.identifierRet, $returnType.typeRet, args, statements); $functionDeclaration.setLine($name.identifierRet.getLine());}
    ;

functionVarDec returns [ArgDeclaration argDeclaration]:
    t = type arg_iden = identifier {$argDeclaration = new ArgDeclaration($arg_iden.identifierRet, $t.typeRet); $argDeclaration.setLine($arg_iden.identifierRet.getLine());}
    ;

mainBlock returns [MainDeclaration main]:
    {ArrayList<Statement> mainStmts = new ArrayList<>();}
    m = MAIN LBRACE (s = statement {mainStmts.add($s.statementRet);})+ RBRACE
    {$main = new MainDeclaration(mainStmts); $main.setLine($m.getLine());}
    ;

statement returns [Statement statementRet]:
    s1 = assignSmt {$statementRet = $s1.assignStmtRet;}| (s2 = predicate SEMICOLON {$statementRet = $s2.predicateRet;})
    | s3 = implication {$statementRet = $s3.implicationRet;} | s4 = returnSmt {$statementRet = $s4.returnRet;}
    | s5 = printSmt {$statementRet = $s5.PrintStmtRet;}| s6 = forLoop {$statementRet = $s6.forRet;}| s7 = localVarDeclaration {$statementRet = $s7.localvarDecRet;}
    ;

assignSmt returns [AssignStmt assignStmtRet]:
    lv = variable line = ASSIGN rv = expression SEMICOLON
    {$assignStmtRet = new AssignStmt($lv.v,  $rv.e); $assignStmtRet.setLine($line.getLine());}
    ;

variable returns [Variable v]:
    i = identifier {$v = $i.identifierRet;} | name = identifier LBRACKET exp = expression RBRACKET {$v = new ArrayAccess($name.identifierRet.getName(), $exp.e); $v.setLine($name.identifierRet.getLine()); }
    ;

localVarDeclaration returns [Statement localvarDecRet]:
    s1 = varDeclaration {$localvarDecRet = $s1.varDecRet;}
    | s2 = arrayDeclaration {$localvarDecRet = $s2.arrayDecRet;}
    ;

varDeclaration returns [VarDecStmt varDecRet]:
    t = type var_iden = identifier {$varDecRet = new VarDecStmt($var_iden.identifierRet, $t.typeRet); $varDecRet.setLine($var_iden.identifierRet.getLine());} (ASSIGN e = expression {$varDecRet.setInitialExpression($e.e);} )? SEMICOLON
    ;

arrayDeclaration returns [ArrayDecStmt arrayDecRet]:
    t = type LBRACKET INT_NUMBER RBRACKET var_iden = identifier {$arrayDecRet = new ArrayDecStmt($var_iden.identifierRet, $t.typeRet, $INT_NUMBER.int); $arrayDecRet.setLine($var_iden.identifierRet.getLine());}
    (initial = arrayInitialValue {$arrayDecRet.setInitialValues($initial.initialValues);})? SEMICOLON
    ;

arrayInitialValue returns [ArrayList<Expression> initialValues]:
    ASSIGN arrList = arrayList {$initialValues = $arrList.values;}
    ;

arrayList returns [ArrayList<Expression> values]:
    {$values = new ArrayList<Expression>();}
    LBRACKET (v = value {$values.add($v.valueRet);}| id = identifier {$values.add($id.identifierRet);}) (COMMA (v2 = value {$values.add($v2.valueRet);}| id2 = identifier {$values.add($id.identifierRet);}))* RBRACKET
    ;

printSmt returns [PrintStmt PrintStmtRet]:
    p = PRINT LPAR arg = printExpr RPAR SEMICOLON
    {$PrintStmtRet = new PrintStmt($arg.printExprRet); $PrintStmtRet.setLine($p.getLine());}
    ;

printExpr returns [Expression printExprRet]:
    var = variable {$printExprRet = $var.v;}
    | q = query {$printExprRet = $q.queryRet;}
    ;

query returns [QueryExpression queryRet]:
     q1 = queryType1 {$queryRet = $q1.query1Ret;}
     | q2 = queryType2 {$queryRet = $q2.query2Ret;}
    ;

queryType1 returns [QueryExpression query1Ret]:
    LBRACKET line = QUARYMARK id = predicateIdentifier LPAR var = variable RPAR RBRACKET
    {$query1Ret = new QueryExpression($id.predicateIdentifierRet); $query1Ret.setLine($line.getLine()); $query1Ret.setVar($var.v);}
    ;

queryType2 returns [QueryExpression query2Ret]:
    LBRACKET id = predicateIdentifier LPAR line = QUARYMARK RPAR RBRACKET
    {$query2Ret = new QueryExpression($id.predicateIdentifierRet); $query2Ret.setLine($line.getLine()); }
    ;

returnSmt returns [ReturnStmt returnRet]:
    RETURN (v = value {$returnRet = new ReturnStmt($v.valueRet);} | iden = identifier {$returnRet = new ReturnStmt($iden.identifierRet);})? SEMICOLON {if($returnRet == null){$returnRet = new ReturnStmt(null);}}
    {$returnRet.setLine($RETURN.getLine());}
    ;

forLoop returns [ForloopStmt forRet]:
    {ArrayList<Statement> bodyStmts = new ArrayList<>();}
    line = FOR LPAR iterator = identifier COLON arrayName = identifier RPAR
    LBRACE ((stmt =statement {bodyStmts.add($stmt.statementRet);})*) RBRACE
    {$forRet = new ForloopStmt($iterator.identifierRet, $arrayName.identifierRet, bodyStmts); $forRet.setLine($line.getLine());}
    ;

predicate returns [PredicateStmt predicateRet]:
    id = predicateIdentifier LPAR v = variable RPAR
    {$predicateRet = new PredicateStmt($id.predicateIdentifierRet, $v.v); $predicateRet.setLine($id.predicateIdentifierRet.getLine());}
    ;

implication returns [ImplicationStmt implicationRet]:
    {ArrayList<Statement> results = new ArrayList<Statement>();}
    LPAR e = expression RPAR a = ARROW LPAR ((s = statement {results.add($s.statementRet);})+) RPAR
    {$implicationRet = new ImplicationStmt($e.e, results); $implicationRet.setLine($a.getLine());}
    ;

expression returns [Expression e]:
    l = andExpr r = expression2
    {if($r.e != null) {$e = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $e.setLine($r.e.getLine());} else {$e = $l.e;}}
    ;

expression2 returns [BinaryExpression e] locals [BinaryExpression ee]:
    OR l = andExpr r = expression2
    {if($r.e != null) {$ee = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $ee.setLine($r.e.getLine()); $e = new BinaryExpression(null, $ee, BinaryOperator.or);} else{$e = new BinaryExpression(null, $l.e, BinaryOperator.or);}}
    {$e.setLine($OR.getLine());}
    |
    {$e = null;}
    ;

andExpr returns [Expression e]:
    l = eqExpr r = andExpr2
    {if($r.e != null) {$e = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $e.setLine($r.e.getLine());} else {$e = $l.e;}}
    ;

andExpr2 returns [BinaryExpression e] locals [BinaryExpression ee]:
    AND l = eqExpr r = andExpr2
    {if($r.e != null) {$ee = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $ee.setLine($r.e.getLine()); $e = new BinaryExpression(null, $ee, BinaryOperator.and);} else{$e = new BinaryExpression(null, $l.e, BinaryOperator.and);}}
    {$e.setLine($AND.getLine());}
    |
    {$e = null;}
    ;

eqExpr returns [Expression e]:
    l = compExpr r = eqExpr2
    {if($r.e != null) {$e = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $e.setLine($r.e.getLine());} else {$e = $l.e;}}
    ;

eqExpr2 returns [BinaryExpression e] locals [BinaryOperator opt, BinaryExpression ee]:
    (op = EQ {$opt = BinaryOperator.eq;}| op = NEQ {$opt = BinaryOperator.neq;}) l = compExpr r = eqExpr2
    {if($r.e != null) {$ee = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $ee.setLine($r.e.getLine()); $e = new BinaryExpression(null, $ee, $opt);} else{$e = new BinaryExpression(null, $l.e, $opt);}}
    {$e.setLine($op.getLine());}
    |
    {$e = null;}
    ;

compExpr returns [Expression e]:
    l = additive r = compExpr2
    {if($r.e != null) {$e = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $e.setLine($r.e.getLine());} else {$e = $l.e;}}
    ;

compExpr2 returns [BinaryExpression e] locals [BinaryOperator opt, BinaryExpression ee]:
    (op = LT {$opt = BinaryOperator.lt;}| op = LTE {$opt = BinaryOperator.lte;}| op = GT {$opt = BinaryOperator.gt;}| op = GTE{$opt = BinaryOperator.gte;}) l = additive r = compExpr2
    {if($r.e != null) {$ee = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $ee.setLine($r.e.getLine()); $e = new BinaryExpression(null, $ee, $opt);} else{$e = new BinaryExpression(null, $l.e, $opt);}}
    {$e.setLine($op.getLine());}
    |
    {$e = null;}
    ;

additive returns [Expression e]:
    l = multicative r = additive2
    {if($r.e != null) {$e = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $e.setLine($r.e.getLine());} else {$e = $l.e;}}
    ;

additive2 returns [BinaryExpression e] locals [BinaryOperator opt, BinaryExpression ee]:
    (op = PLUS {$opt = BinaryOperator.add;} | op = MINUS {$opt = BinaryOperator.sub;}) l = multicative r = additive2
    {if($r.e != null) {$ee = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $ee.setLine($r.e.getLine()); $e = new BinaryExpression(null, $ee, $opt);} else{$e = new BinaryExpression(null, $l.e, $opt);}}
    {$e.setLine($op.getLine());}
    |
    {$e = null;}
    ;

multicative returns [Expression e]:
    l =  unary r = multicative2
    {if($r.e != null) {$e = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $e.setLine($r.e.getLine());} else {$e = $l.e;}}
    ;

multicative2 returns [BinaryExpression e] locals [BinaryOperator opt, BinaryExpression ee]:
    (op = MULT {$opt = BinaryOperator.mult;} | op = MOD {$opt = BinaryOperator.mod;}| op = DIV {$opt = BinaryOperator.div;}) l = unary r = multicative2
    {if($r.e != null) {$ee = new BinaryExpression($l.e, $r.e.getRight(), $r.e.getBinaryOperator()); $ee.setLine($r.e.getLine()); $e = new BinaryExpression(null, $ee, $opt);} else{$e = new BinaryExpression(null, $l.e, $opt);}}
    {$e.setLine($op.getLine());}
    |
    {$e = null;}
    ;

unary returns [Expression e] locals [UnaryOperator opt]:
    otherRet = other {$e = $otherRet.e;}
    |
     (op = PLUS {$opt = UnaryOperator.plus;} | op = MINUS {$opt = UnaryOperator.minus;} | op = NOT {$opt = UnaryOperator.not;}) expr = other
     {$e = new UnaryExpression($opt, $expr.e); $e.setLine($op.getLine());}
    ;

other returns [Expression e]:
    LPAR expr = expression RPAR {$e = $expr.e;}| var = variable {$e = $var.v;} | val = value {$e = $val.valueRet;}
    | q1 = queryType1 {$e = $q1.query1Ret;} | funcCall = functionCall {$e = $funcCall.fCallRet;}
    ;

functionCall returns [FunctionCall fCallRet]:
    {ArrayList<Expression> args = new ArrayList<Expression>();}
    name = identifier LPAR (arg1 = expression {args.add($arg1.e);} (COMMA newArg = expression {args.add($newArg.e);})*)? RPAR
    {$fCallRet = new FunctionCall(args, $name.identifierRet); $fCallRet.setLine($name.identifierRet.getLine());}
    ;

value returns [Value valueRet]:
    v = numericValue {$valueRet = $v.v;}
    | t = TRUE {$valueRet = new BooleanValue(true);} {$valueRet.setLine($t.getLine());}
    | f = FALSE {$valueRet = new BooleanValue(false);} {$valueRet.setLine($f.getLine());}
    | MINUS v2 = numericValue {$v2.v.negateConstant(); $valueRet = $v2.v;}
    ;

numericValue returns [Value v]:
    i = INT_NUMBER {$v = new IntValue($i.int);}
               {$v.setLine($i.getLine());}
    | f = FLOAT_NUMBER {$v = new FloatValue(Float.parseFloat($f.text));}
                       {$v.setLine($f.getLine());}
    ;

identifier returns [Identifier identifierRet]:
    idnfr = IDENTIFIER {$identifierRet = new Identifier($idnfr.text); $identifierRet.setLine($idnfr.getLine());}
    ;

predicateIdentifier returns [Identifier predicateIdentifierRet]:
    predicate_idnfr = PREDICATE_IDENTIFIER {$predicateIdentifierRet = new Identifier($predicate_idnfr.text); $predicateIdentifierRet.setLine($predicate_idnfr.getLine());}
    ;

type returns [Type typeRet]:
    BOOLEAN {$typeRet = new BooleanType();}
    | INT {$typeRet = new IntType();}
    | FLOAT {$typeRet = new FloatType();}
    ;




FUNCTION : 'function';
BOOLEAN : 'boolean';
INT : 'int';
FLOAT: 'float';
MAIN: 'main';
PRINT: 'print';
RETURN: 'return';
FOR: 'for';
TRUE: 'true';
FALSE: 'false';

LPAR: '(';
RPAR: ')';
COLON: ':';
COMMA: ',';
LBRACE: '{';
RBRACE: '}';
SEMICOLON: ';';
ASSIGN: '=';
LBRACKET: '[';
RBRACKET: ']';
QUARYMARK: '?';
ARROW: '=>';
OR: '||';
AND: '&&';
EQ: '==';
GT: '>';
LT: '<';
GTE: '>=';
LTE: '<=';
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
MOD: '%';
NEQ: '!=';
NOT: '!';


WS : [ \t\r\n]+ -> skip ;
COMMENT : '#' ~[\r\n]* -> skip ;

IDENTIFIER : [a-z][a-zA-Z0-9_]* ;
PREDICATE_IDENTIFIER : [A-Z][a-zA-Z0-9]* ;
INT_NUMBER : [0-9]+;
FLOAT_NUMBER: ([0-9]*[.])?[0-9]+;