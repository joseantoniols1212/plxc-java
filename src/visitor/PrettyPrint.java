package visitor;

import syntax.*;

import java.io.PrintStream;
import java.util.List;

public class PrettyPrint implements Visitor {

    private String indent = "";
    private boolean isLast = true;

    private void print(String str){
        out.print(indent);
        out.print(isLast? "└───" : "├───");
        out.print(str+"\n");
        indent += isLast? "    " : "│   ";
        isLast = false;
    }

    private PrintStream out = System.out;

    public void changeOutput(PrintStream output) {
      this.out = output;
    }

    public void visit(BinaryExpressionNode node) {
        print(node.getOperation().toString());
        String saveIndent = indent;
        node.getLeft().accept(this);
        isLast = true;
        indent = saveIndent;
        node.getRight().accept(this);
    }

    public void visit(IntegerNode node) {
        print(node.getValue().toString());
    }

    @Override
    public void visit(AssignmentNode node) {
        print(":=");
        String saveIndent = indent;
        node.getIdentifier().accept(this);
        isLast = true;
        indent = saveIndent;
        node.getAssignment().accept(this);
    }

    @Override
    public void visit(FunctionNode node) {
        print(node.getName());
        isLast = true;
        String saveIndent = indent;
        node.getExpression().accept(this);
    }

    @Override
    public void visit(IdentifierNode node) {
        print(node.getIdentifierName());
    }

    @Override
    public void visit(BinaryConditionNode node) {
        print(node.getOperator().toString());
        String saveIndent = indent;
        node.getLeft().accept(this);
        isLast = true;
        indent = saveIndent;
        node.getRight().accept(this);
    }

    @Override
    public void visit(IfNode node) {
        print("If");
        String saveIndent = indent;
        node.getCondition().accept(this);
        indent = saveIndent;
        if (node.getElse_body() == null) {
            isLast = true;
            node.getBody().accept(this);
        } else {
            node.getBody().accept(this);
            indent = saveIndent;
            isLast = true;
            node.getElse_body().accept(this);
        }
    }

    @Override
    public void visit(UnaryExpressionNode node) {
        print(node.getOperation().toString());
        isLast = true;
        node.getNode().accept(this);
    }

    @Override
    public void visit(ComparisonNode node) {
        print(node.getComparator().toString());
        String saveIndent = indent;
        node.getLeft().accept(this);
        isLast = true;
        indent = saveIndent;
        node.getRight().accept(this);
    }

    @Override
    public void visit(BlockNode node) {
        print("Block");
        String saveIndent = indent;
        List<SentenceNode> sentences = node.getSentences();
        for (int i = 0; i< sentences.size(); i++){
            if (i == sentences.size()-1)
                isLast = true;
            sentences.get(i).accept(this);
            indent = saveIndent;
        }
    }

    @Override
    public void visit(WhileNode node) {
        print("While");
        String saveIndent = indent;
        node.getCondition().accept(this);
        indent = saveIndent;
        isLast = true;
        node.getBody().accept(this);
    }

    @Override
    public void visit(DoWhileNode node) {
        print("DoWhile");
        String saveIndent = indent;
        node.getCondition().accept(this);
        indent = saveIndent;
        isLast = true;
        node.getBody().accept(this);
    }

    @Override
    public void visit(ForNode node) {
        print("For");
        String saveIndent = indent;
        node.getInitialization().accept(this);
        indent = saveIndent;
        node.getCondition().accept(this);
        indent = saveIndent;
        node.getIncrement().accept(this);
        indent = saveIndent;
        isLast = true;
        node.getBody().accept(this);
    }

    @Override
    public void visit(DeclarationNode node) {
        print(node.getType());
        String saveIndent = indent;
        List<VariableNode> identifiers = node.getVariables();
        for (int i = 0; i< identifiers.size(); i++){
            if (i == identifiers.size()-1)
                isLast = true;
            identifiers.get(i).accept(this);
            indent = saveIndent;
        }
    }

    @Override
    public void visit(ForToNode node) {
        print("For To");
        String saveIndent = indent;
        node.getInitialization().accept(this);
        indent = saveIndent;
        node.getBound().accept(this);
        indent = saveIndent;
        node.getStep().accept(this);
        indent = saveIndent;
        isLast = true;
        node.getBody().accept(this);
    }

    @Override
    public void visit(ForDownToNode node) {
        print("For Down To");
        String saveIndent = indent;
        node.getInitialization().accept(this);
        indent = saveIndent;
        node.getBound().accept(this);
        indent = saveIndent;
        node.getStep().accept(this);
        indent = saveIndent;
        isLast = true;
        node.getBody().accept(this);
    }
}
