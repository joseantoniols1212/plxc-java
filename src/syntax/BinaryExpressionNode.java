package syntax;

import visitor.Visitor;

import java.util.Arrays;

public class BinaryExpressionNode extends ExpressionNode {
    private ExpressionNode left;
    private BinaryOperation operation;
    private ExpressionNode right;

    public ExpressionNode getLeft() {
        return this.left;
    }

    public ExpressionNode getRight() {
        return this.right;
    }

    public BinaryOperation getOperation() {
        return this.operation;
    }

    public BinaryExpressionNode(ExpressionNode left, ExpressionNode right, BinaryOperation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
}
