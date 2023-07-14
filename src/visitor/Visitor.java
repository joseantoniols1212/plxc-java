package visitor;

import syntax.*;

public interface Visitor {
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
}

