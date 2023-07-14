package syntax;

import visitor.Visitor;

import java.util.Arrays;

import static syntax.BinaryLogicalOperator.AND;
import static syntax.BinaryLogicalOperator.OR;

public class BinaryConditionNode extends  ConditionNode {

    private ConditionNode left;
    private ConditionNode right;
    private BinaryLogicalOperator operator;

    public BinaryConditionNode(ConditionNode left, ConditionNode right, BinaryLogicalOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ConditionNode getLeft() {
        return left;
    }

    public ConditionNode getRight() {
        return right;
    }

    public BinaryLogicalOperator getOperator() {
        return operator;
    }

    @Override
    public Iterable<Node> getChildren() {
        return Arrays.asList(left, right);
    }

    public void not() {
        this.operator = switch (this.getOperator()) {
            case OR -> AND;
            case AND -> OR;
        };
        this.left.not();
        this.right.not();
    }
}
