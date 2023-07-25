package syntax;

public class ForToNode extends SentenceNode {

    private AssignmentNode initialization;
    private ExpressionNode bound;
    private ExpressionNode step;
    private SentenceNode body;

    public SentenceNode getBody() {
        return body;
    }

    public AssignmentNode getInitialization() {
        return initialization;
    }

    public ExpressionNode getBound() {
        return bound;
    }

    public ExpressionNode getStep() {
        return step;
    }

    public ForToNode(AssignmentNode initialization, ExpressionNode bound, ExpressionNode step, SentenceNode body) {
        this.initialization = initialization;
        this.bound = bound;
        this.step = step;
        this.body = body;
    }

    public ForToNode(AssignmentNode initialization, ExpressionNode bound, SentenceNode body) {
        this.initialization = initialization;
        this.bound = bound;
        this.step = new IntegerNode(1);
        this.body = body;
    }
}
