package syntax;

public enum Comparator {
    EQUAL, LESS_EQUAL, GREATER_EQUAL, GREATER, LESS, DISTINCT;

    @Override
    public String toString() {
        switch (this) {
            case GREATER -> {
                return ">";
            }
            case LESS -> {
                return "<";
            }
            case LESS_EQUAL -> {
                return "<=";
            }
            case DISTINCT -> {
                return "!=";
            }
            case EQUAL -> {
                return "==";
            }
            case GREATER_EQUAL -> {
                return ">=";
            }
            default -> {
                return null;
            }
        }
    }
}
