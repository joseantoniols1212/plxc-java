package visitor;

import syntax.*;

import java.io.PrintStream;

public interface Visitor {

    void changeOutput(PrintStream output);

    void visit(BinaryExpressionNode node);
    void visit(IntegerNode node);
    void visit(AssignmentNode node);
    void visit(FunctionNode node);
    void visit(IdentifierNode node);
    void visit(BinaryConditionNode node);
    void visit(IfNode node);
    void visit(UnaryExpressionNode node);
    void visit(ComparisonNode node);
    void visit(BlockNode node);
    void visit(WhileNode node);
    void visit(DoWhileNode node);
    void visit(ForNode node);
    void visit(DeclarationNode node);
    void visit(ForToNode node);
    void visit(ForDownToNode node);
}

