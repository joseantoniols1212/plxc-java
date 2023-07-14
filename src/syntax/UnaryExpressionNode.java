package syntax;

import visitor.Visitor;

import java.util.List;

public class UnaryExpressionNode extends ExpressionNode {
    private ExpressionNode node;
    private UnaryOperation operation;

    public UnaryOperation getOperation() {
        return operation;
    }

    public ExpressionNode getNode() {
        return node;
    }

    public UnaryExpressionNode(ExpressionNode node, UnaryOperation operation) {
        this.node = node;
        this.operation = operation;
    }
}
