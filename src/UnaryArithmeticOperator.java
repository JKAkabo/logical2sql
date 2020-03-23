public class UnaryArithmeticOperator extends Node {
    public UnaryArithmeticOperator(Token operator, Node right) {
        this.operator = operator;
        this.right = right;
    }

    private Token operator;
    private Node right;

    public Token getOperator() {
        return operator;
    }

    public Node getRight() {
        return right;
    }
}
