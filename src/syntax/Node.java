package syntax;

import visitor.Visitor;

public abstract class Node {
    public void accept(Visitor visitor) {
        if (this instanceof BinaryConditionNode) visitor.visit((BinaryConditionNode) this);
        else if (this instanceof ComparisonNode) visitor.visit((ComparisonNode) this);
        else if (this instanceof BinaryExpressionNode) visitor.visit((BinaryExpressionNode) this);
        else if (this instanceof AssignmentNode) visitor.visit((AssignmentNode) this);
        else if (this instanceof IntegerNode) visitor.visit((IntegerNode) this);
        else if (this instanceof IdentifierNode) visitor.visit((IdentifierNode) this);
        else if (this instanceof UnaryExpressionNode) visitor.visit((UnaryExpressionNode) this);
        else if (this instanceof IfNode) visitor.visit((IfNode) this);
        else if (this instanceof FunctionNode) visitor.visit((FunctionNode) this);
        else if (this instanceof BlockNode) visitor.visit((BlockNode) this);
        else if (this instanceof WhileNode) visitor.visit((WhileNode) this);
        else if (this instanceof DoWhileNode) visitor.visit((DoWhileNode) this);
        else if (this instanceof ForNode) visitor.visit((ForNode) this);
        else if (this instanceof DeclarationNode) visitor.visit((DeclarationNode) this);
        else if (this instanceof ForToNode) visitor.visit((ForToNode) this);
        else if (this instanceof ForDownToNode) visitor.visit((ForDownToNode) this);
        else throw new RuntimeException("Falta castear el nodo al aceptar visitor: "+this.getClass().getSimpleName());
    }
}