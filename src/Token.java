public class Token {
    private TokenType type;

    private Object value;

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }
}
