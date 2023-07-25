package symbolTable;

import java.util.Objects;

public class Register {

    private String identifier;
    private String type;

    public Register(String identifier, String type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Register register = (Register) o;
        return Objects.equals(identifier, register.identifier) && Objects.equals(type, register.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, type);
    }
}
