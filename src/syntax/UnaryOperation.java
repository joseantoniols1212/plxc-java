package syntax;

public enum UnaryOperation {
    PLUS,
    MINUS,
    PREINC,
    POSTINC,
    PREDEC,
    POSTDEC;

    @Override
    public String toString() {
        switch (this) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case PREDEC:
                return "PREDEC";
            case POSTDEC:
                return "POSTDEC";
            case PREINC:
                return "PREINC";
            case POSTINC:
                return "POSTINC";
            default:
                return null;
        }
    }
}
