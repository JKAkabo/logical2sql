import java.util.Arrays;

public class Variable extends Node {
    public Variable(Token variable) {
        this.name = (String) variable.getValue();
    }

    public String name;

    public String getTableName() {
        System.out.println("Ken here --> " + this.name);
        String[] parts = this.name.split("\\.");
        return String.join(".", Arrays.copyOfRange(parts, 0, parts.length - 1));
    }

    public String getColumnName() {
        String[] parts = this.name.split("\\.");
        return parts[parts.length - 1];
    }
}
