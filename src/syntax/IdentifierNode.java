package syntax;

import visitor.Visitor;

import java.util.Arrays;
import java.util.List;

public class IdentifierNode extends ExpressionNode {

    private String name;

    public String getName() {
        return this.name;
    }

    public IdentifierNode(String name) {
        this.name = name;
    }

    @Override
    public Iterable<Node> getChildren() {
        return List.of();
    }
}
