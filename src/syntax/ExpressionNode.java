package syntax;

import visitor.Visitor;

public abstract class ExpressionNode extends SentenceNode {

    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}