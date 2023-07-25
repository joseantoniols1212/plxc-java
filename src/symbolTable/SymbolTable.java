package symbolTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SymbolTable {

    private List<HashSet<Register>> table;

    public SymbolTable() {
        this.table = new ArrayList<>();
    }

    // return index of the block where the register was inserted or -1 if
    // insertion was not successful
    public int insert(Register register) {
        if (search(register) == table.size() - 1) {
            return -1;
        }
        table.get(table.size()-1).add(register);
        return table.size() - 1;
    }

    // returns -1 if register is not found
    public int search(Register register) {
        int index = table.size() - 1;
        boolean found = false;
        while (index >= 0 && !found) {
            if(table.get(index).contains(register)) {
                found = true;
            } else {
                index--;
            }
        }
        return index;
    }

    public void openBlock() {
        this.table.add(new HashSet<>());
    }

    public void closeBlock() {
        this.table.remove(this.table.size() - 1);
    }
}
