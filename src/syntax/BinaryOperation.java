package syntax;

public enum BinaryOperation {
    SUM,
    MULTIPLICATION,
    DIVISION,
    DIFFERENCE,
    MOD;

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
            case MOD:
                return "%";
            default:
                return null;
        }
    }
}
