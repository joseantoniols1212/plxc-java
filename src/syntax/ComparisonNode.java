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
    public void not() {
        switch (this.comparator) {
            case LESS:
                this.comparator = Comparator.GREATER_EQUAL;
                break;
            case EQUAL:
                this.comparator = Comparator.DISTINCT;
                break;
            case GREATER:
                this.comparator = Comparator.LESS_EQUAL;
                break;
            case DISTINCT:
                this.comparator = Comparator.EQUAL;
                break;
            case LESS_EQUAL:
                this.comparator = Comparator.GREATER;
                break;
            case GREATER_EQUAL:
                this.comparator = Comparator.LESS;
                break;
        };
    }
}
