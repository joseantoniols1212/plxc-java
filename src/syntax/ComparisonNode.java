package syntax;

import visitor.Visitor;

import java.util.Arrays;

public class ComparisonNode extends ConditionNode {

    private ExpressionNode left;
    private ExpressionNode right;
    private Comparator comparator;

    public ComparisonNode(ExpressionNode left, ExpressionNode right, Comparator comparator) {
        this.left = left;
        this.right = right;
        this.comparator = comparator;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public ExpressionNode getRight() {
        return right;
    }

    public Comparator getComparator() {
        return comparator;
    }

    @Override
    public Iterable<Node> getChildren() {
        return Arrays.asList(left, right);
    }

    @Override
    public void not() {
        this.comparator = switch (this.comparator) {
            case LESS -> Comparator.GREATER_EQUAL;
            case EQUAL -> Comparator.DISTINCT;
            case GREATER -> Comparator.LESS_EQUAL;
            case DISTINCT -> Comparator.EQUAL;
            case LESS_EQUAL -> Comparator.GREATER;
            case GREATER_EQUAL -> Comparator.LESS;
        };
    }
}
