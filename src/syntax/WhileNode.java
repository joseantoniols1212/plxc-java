package syntax;

import java.util.Arrays;

public class WhileNode extends SentenceNode {

    private ConditionNode condition;
    private SentenceNode body;

    public ConditionNode getCondition() {
        return condition;
    }

    public SentenceNode getBody() {
        return body;
    }

    @Override
    public Iterable<Node> getChildren() {
        return Arrays.asList(condition, body);
    }

    public WhileNode(ConditionNode condition, SentenceNode body) {
        this.condition = condition;
        this.body = body;
    }
}
