package syntax;

public enum BinaryOperation {
    SUM,
    MULTIPLICATION,
    DIVISION,
    DIFFERENCE;

    @Override
    public String toString() {
        switch (this) {
            case SUM:
                return "+";
            case DIVISION:
                return "/";
            case DIFFERENCE:
                return "-";
            case MULTIPLICATION:
                return "*";
            default:
                return null;
        }
    }
}
