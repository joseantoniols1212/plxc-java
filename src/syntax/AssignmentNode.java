package syntax;

import java.util.Arrays;

public class AssignmentNode extends VariableNode {

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

    @Override
    public String getIdentifierName() {
        return identifier.getIdentifierName();
    }
}