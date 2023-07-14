package syntax;

import visitor.Visitor;

import java.util.List;

public class IfNode extends SentenceNode {
    private ConditionNode condition;
    private SentenceNode body;
    private SentenceNode else_body;

    public IfNode(ConditionNode condition, SentenceNode body, SentenceNode else_body) {
        this.condition = condition;
        this.body = body;
        this.else_body = else_body;
    }

    @Override
    public Iterable<Node> getChildren() {
        return List.of(condition, body, else_body);
    }

    public ConditionNode getCondition() {
        return condition;
    }

    public SentenceNode getBody() {
        return body;
    }

    public SentenceNode getElse_body() {
        return else_body;
    }
}
