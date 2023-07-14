package visitor;

import syntax.*;

import java.io.PrintStream;

public class CtdGenerator implements Visitor {

    private int label = 0;
    private int var = 0;

    private String lastVariable = null;
    private String trueLabel = null;
    private String falseLabel = null;

    private PrintStream out = System.out;

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
        out.println(var + " = " + left + " " + node.getOperation().toString() + " " + right + ";");
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
        out.println(node.getIdentifier().getName()+" = "+lastVariable + ";");
    }

    @Override
    public void visit(FunctionNode node) {
        node.getExpression().accept(this);
        out.println(node.getName() + " " + lastVariable + ";");
    }

    @Override
    public void visit(IdentifierNode node) {
        lastVariable = node.getName();
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
        for(Node child : node.getSentences()) child.accept(this);
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
        out.println(var+" = "+ node.getOperation().toString() +lastVariable + ";");
        lastVariable = var;
    }
}
