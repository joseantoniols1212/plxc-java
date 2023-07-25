package visitor;

import symbolTable.Register;
import symbolTable.SymbolTable;
import syntax.*;

import java.io.PrintStream;

public class CtdGenerator implements Visitor {

    private int label = 0;
    private int var = 0;

    private String lastVariable = null;
    private String trueLabel = null;
    private String falseLabel = null;

    private PrintStream out = System.out;

    private SymbolTable symbolTable = new SymbolTable();

    public void changeOutput(PrintStream output) {
        this.out = output;
    }

    private String getLabel(){
        return "L"+label++;
    }

    private String getVar(){
        return "t"+var++;
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        String var = getVar();
        node.getLeft().accept(this);
        String left = lastVariable;
        node.getRight().accept(this);
        String right = lastVariable;
        BinaryOperation operation = node.getOperation();
        if (operation.equals(BinaryOperation.MOD)) {
            String divVariable = getVar();
            String prodVariable = getVar();
            out.println(divVariable + " = " + left + "/" + right + ";");
            out.println(prodVariable + " = " + divVariable + "*" + right + ";");
            out.println(var + " = " + left + "-" + prodVariable + ";");
        } else {
            out.println(var + " = " + left + " " + node.getOperation().toString() + " " + right + ";");
        }
        lastVariable = var;
    }

    @Override
    public void visit(IntegerNode node) {
//        lastVariable = getVar();
//        out.println(lastVariable+" = "+node.getValue() + ";");
        lastVariable = node.getValue().toString();
    }

    @Override
    public void visit(AssignmentNode node) {
        node.getAssignment().accept(this);
        String assignmentVariable = lastVariable;
        node.getIdentifier().accept(this);
        out.println(lastVariable+" = "+assignmentVariable + ";");
    }

    @Override
    public void visit(FunctionNode node) {
        node.getExpression().accept(this);
        out.println(node.getName() + " " + lastVariable + ";");
    }

    @Override
    public void visit(IdentifierNode node) {
        int identifierBlock = symbolTable.search(new Register(node.getIdentifierName(), "INT"));
        if ( identifierBlock == -1) {
            out.println("error;");
            out.println("# Variable no declarada; ");
        }
        lastVariable = node.getIdentifierName() + "_" + identifierBlock;
    }

    @Override
    public void visit(ComparisonNode node) {
        trueLabel = getLabel();
        falseLabel = getLabel();
        node.getLeft().accept(this);
        String left = lastVariable;
        node.getRight().accept(this);
        String right = lastVariable;
        switch (node.getComparator()) {
            case EQUAL:
                out.println("if ( " + left + " == " + right + " ) goto " + trueLabel + ";");
                out.println("goto " + falseLabel + ";");
                break;
            case GREATER_EQUAL:
                out.println("if ( " + left + " < " + right + " ) goto " + falseLabel + ";");
                out.println("goto " + trueLabel + ";");
                break;
            case LESS:
                out.println("if ( " + left + " < " + right + " ) goto " + trueLabel + ";");
                out.println("goto " + falseLabel + ";");
                break;
            case DISTINCT:
                out.println("if ( " + left + " == " + right + " ) goto " + falseLabel + ";");
                out.println("goto " + trueLabel + ";");
                break;
            case LESS_EQUAL:
                out.println("if ( " + right + " < " + left + " ) goto " + falseLabel + ";");
                out.println("goto " + trueLabel + ";");
                break;
            case GREATER:
                out.println("if ( " + right + " < " + left + " ) goto " + trueLabel + ";");
                out.println("goto " + falseLabel + ";");
                break;
        }
    }

    @Override
    public void visit(BlockNode node) {
        symbolTable.openBlock();
        for(Node child : node.getSentences()) child.accept(this);
        symbolTable.closeBlock();
    }

    @Override
    public void visit(WhileNode node) {
        String loopLabel = getLabel();
        out.println(loopLabel+":");
        node.getCondition().accept(this);
        String saveFalseLabel = falseLabel;
        out.println(trueLabel+":");
        node.getBody().accept(this);
        out.println("goto "+loopLabel+";");
        out.println(saveFalseLabel+":");
    }

    @Override
    public void visit(DoWhileNode node) {
        String loopLabel = getLabel();
        out.println(loopLabel+":");
        node.getBody().accept(this);
        node.getCondition().accept(this);
        out.println(trueLabel+":");
        out.println("goto "+loopLabel+";");
        out.println(falseLabel+":");
    }

    @Override
    public void visit(ForNode node) {
        node.getInitialization().accept(this);
        String loopLabel = getLabel();
        out.println(loopLabel+":");
        node.getCondition().accept(this);
        String saveFalseLabel = falseLabel;
        out.println(trueLabel+":");
        node.getBody().accept(this);
        node.getIncrement().accept(this);
        out.println("goto "+loopLabel+";");
        out.println(saveFalseLabel+":");
    }

    @Override
    public void visit(DeclarationNode node) {
        for (VariableNode variable : node.getVariables()) {
            Register register = new Register(variable.getIdentifierName(), node.getType());
            if ( symbolTable.insert(register) == -1 ) {
                out.println("error;");
                out.println("# Variable ya declarada ");
            }
            variable.accept(this);
        }
    }

    @Override
    public void visit(ForToNode node) {
        node.getInitialization().accept(this);
        String var = lastVariable;
        node.getBound().accept(this);
        String bound = lastVariable;
        String loopLabel = getLabel();
        String goBody = getLabel();
        String exitLoop = getLabel();
        out.println(loopLabel+":");
        out.println("if ( " + bound + " < " + var + " ) goto " + exitLoop + ";");
        out.println("goto " + goBody + ";");
        out.println(goBody+":");
        node.getBody().accept(this);
        node.getStep().accept(this);
        String step = lastVariable;
        out.println(var+ " = " + var + " + " + step + ";");
        out.println("goto "+loopLabel+";");
        out.println(exitLoop+":");
    }

    @Override
    public void visit(ForDownToNode node) {
        node.getInitialization().accept(this);
        String var = lastVariable;
        node.getBound().accept(this);
        String bound = lastVariable;
        String loopLabel = getLabel();
        String goBody = getLabel();
        String exitLoop = getLabel();
        out.println(loopLabel+":");
        out.println("if ( " + var + " < " + bound + " ) goto " + exitLoop + ";");
        out.println("goto " + goBody + ";");
        out.println(goBody+":");
        node.getBody().accept(this);
        node.getStep().accept(this);
        String step = lastVariable;
        out.println(var+ " = " + var + " - " + step + ";");
        out.println("goto "+loopLabel+";");
        out.println(exitLoop+":");
    }

    @Override
    public void visit(IfNode node) {
        node.getCondition().accept(this);
        out.println(trueLabel+":");
        String saveFalseLabel = falseLabel;
        node.getBody().accept(this);
        falseLabel = saveFalseLabel;
        if (node.getElse_body() == null) {
            out.println(falseLabel+":");
        } else {
            String end = getLabel();
            out.println("goto "+end+";");
            out.println(falseLabel+":");
            node.getElse_body().accept(this);
            out.println(end+":");
        }
    }

    @Override
    public void visit(BinaryConditionNode node) {
        switch (node.getOperator()) {
            case AND:
                node.getLeft().accept(this);
                String leftFalse = falseLabel;
                out.println(trueLabel+":");
                node.getRight().accept(this);
                out.println(leftFalse+":");
                out.println("goto "+falseLabel+";");
                break;
            case OR:
                node.getLeft().accept(this);
                String leftTrue = trueLabel;
                out.println(falseLabel+":");
                node.getRight().accept(this);
                out.println(leftTrue+":");
                out.println("goto "+trueLabel+";");
                break;
        }
    }

    @Override
    public void visit(UnaryExpressionNode node) {
        String var = getVar();
        node.getNode().accept(this);
        switch (node.getOperation()) {
            case MINUS:
                out.println(var+" = "+ node.getOperation().toString() +lastVariable + ";");
                lastVariable = var;
                break;
            case PREDEC:
                errorNotIdentifier(node.getNode());
                out.println(lastVariable+" = "+lastVariable + "- 1 ;");
                break;
            case PREINC:
                errorNotIdentifier(node.getNode());
                out.println(lastVariable+" = "+lastVariable + "+ 1 ;");
                break;
            case POSTDEC:
                errorNotIdentifier(node.getNode());
                out.println(var+" = "+lastVariable + ";");
                out.println(lastVariable+" = "+lastVariable + "- 1 ;");
                lastVariable = var;
                break;
            case POSTINC:
                errorNotIdentifier(node.getNode());
                out.println(var+" = "+lastVariable + ";");
                out.println(lastVariable+" = "+lastVariable + "+ 1 ;");
                lastVariable = var;
                break;
        }
    }

    public void errorNotIdentifier(Node node) {
        if (!node.getClass().getSimpleName().equals("IdentifierNode")) {
            out.println("error;");
        }
    }
}
