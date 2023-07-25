package syntax;

public class IdentifierNode extends VariableNode {

    private String identifierName;

    public String getIdentifierName() {
        return this.identifierName;
    }

    public IdentifierNode(String name) {
        this.identifierName = name;
    }
}
