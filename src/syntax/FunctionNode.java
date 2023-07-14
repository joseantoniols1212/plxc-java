package syntax;

import visitor.Visitor;

import java.util.Arrays;
import java.util.List;

public class FunctionNode extends SentenceNode {

    private String name;
    private ExpressionNode expression;

    public String getName() {
        return name;
    }

    public ExpressionNode getExpression() {
        return expression;
    }

    public FunctionNode(String name, ExpressionNode expression) {
        this.name = name;
        this.expression = expression;
    }
}
