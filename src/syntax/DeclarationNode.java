package syntax;

import java.util.ArrayList;
import java.util.List;

public class DeclarationNode extends SentenceNode {

    private List<VariableNode> variables;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<VariableNode> getVariables() {
        return variables;
    }

    public DeclarationNode(List<VariableNode> variables) {
        this.variables = variables;
    }

    public DeclarationNode() {
        this.variables = new ArrayList<>();
    }

    public void add(VariableNode node) {
        this.variables.add(node);
    }
}
