package syntax;

public enum BinaryLogicalOperator {
    AND, OR;

    @Override
    public String toString() {
        switch (this) {
            case OR -> {
                return "||";
            }
            case AND -> {
                return "&&";
            }
            default -> {
                return null;
            }
        }
    }
}
