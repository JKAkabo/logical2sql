public class Number extends Node {
    public Number(Token token) {
        this.value = (Double) token.getValue();
    }

    public Double value;
}
