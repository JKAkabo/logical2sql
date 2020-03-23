public class ConstantNode extends Node {
    public ConstantNode(Token constant) {
        this.name = String.valueOf(constant.getValue());
    }

    public String name;
}
