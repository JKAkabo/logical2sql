public class BinaryLogicalOperator extends Node {
    public BinaryLogicalOperator(Node left, Token operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    private Node left;
    private Token operator;
    private Node right;

    public Node getLeft() {
        return left;
    }

    public Token getOperator() {
        return operator;
    }

    public Node getRight() {
        return right;
    }
}
