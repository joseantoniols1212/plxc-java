package syntax;

import visitor.Visitor;

import java.util.List;

public class IntegerNode extends ExpressionNode {

    private Integer value;

    public Integer getValue(){
        return this.value;
    }

    public IntegerNode(Integer value) {
        this.value = value;
    }

    @Override
    public Iterable<Node> getChildren() {
        return List.of();
    }
}
