package syntax;

import java.util.List;

public class ForNode extends SentenceNode {

    private ExpressionNode initialization;
    private ConditionNode condition;
    private ExpressionNode increment;
    private SentenceNode body;

    public ForNode(ExpressionNode initialization, ConditionNode condition, ExpressionNode increment, SentenceNode body) {
        this.initialization = initialization;
        this.condition = condition;
        this.increment = increment;
        this.body = body;
    }

    public ExpressionNode getInitialization() {
        return initialization;
    }

    public ConditionNode getCondition() {
        return condition;
    }

    public ExpressionNode getIncrement() {
        return increment;
    }

    public SentenceNode getBody() {
        return body;
    }
}
