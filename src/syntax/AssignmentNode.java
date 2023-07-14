package syntax;

import java.util.Arrays;

public class AssignmentNode extends ExpressionNode {

    private IdentifierNode identifier;
    private ExpressionNode assignment;

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public ExpressionNode getAssignment() {
        return assignment;
    }

    public AssignmentNode(IdentifierNode identifier, ExpressionNode assignment) {
        this.identifier = identifier;
        this.assignment = assignment;
    }
}