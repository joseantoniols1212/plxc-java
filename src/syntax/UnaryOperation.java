package syntax;

public enum UnaryOperation {
    PLUS,
    MINUS;

    @Override
    public String toString() {
        switch (this) {
            case PLUS -> {
                return "+";
            }
            case MINUS -> {
                return "-";
            }
            default -> {
                return null;
            }
        }
    }
}
