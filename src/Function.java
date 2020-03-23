import java.util.List;

public class Function extends Node {
    public Function(Token identifier, List<Node> args) {
        this.name = String.valueOf(identifier.getValue());
        this.args = args;
    }

    public String name;

    public List<Node> args;

    public List<Double> actualArgs;
}
