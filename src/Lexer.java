public class Lexer {
    public Lexer(String expression) {
        this.chars = expression.toCharArray();
        this.position = 0;
        this.currentChar = this.chars[this.position];
    }

    int position;
    Character currentChar;
    char[] chars;

    public void pop() {
        this.position++;
        if (this.position > this.chars.length - 1) {
            this.currentChar = null;
        }
        else {
            this.currentChar = this.chars[this.position];
        }
    }

    public Character peek() {
        int nextPosition = this.position + 1;
        if (nextPosition > this.chars.length - 1) {
            return null;
        }
        else {
            return this.chars[nextPosition];
        }
    }

    public void skipWhitespace() {
        while (this.currentChar != null && this.currentChar == ' ') {
            this.pop();
        }
    }

    public double getNumberLiteral() {
        StringBuilder literalBuilder = new StringBuilder();
        while (this.currentChar != null && (isDigit(this.currentChar) || this.currentChar == '.')) {
            literalBuilder.append(this.currentChar);
            this.pop();
        }
        return Double.parseDouble(literalBuilder.toString());
    }

    public String getField() {
        StringBuilder variableBuilder = new StringBuilder();
        skipWhitespace();
        variableBuilder.append(this.currentChar);
        this.pop();
        while (this.currentChar != null && (isAlphaNum(this.currentChar) || this.currentChar == '.')) {
            variableBuilder.append(this.currentChar);
            this.pop();
        }
        return variableBuilder.toString();
    }

    public Token getNextToken() {
        while (this.currentChar != null) {
            // Skip Whitespaces
            if (this.currentChar == ' ') {
                this.skipWhitespace();
                continue;
            }

            if (this.currentChar == '{') {
                this.pop();
                return new Token(TokenType.VARIABLE, getField());
            }

            if (this.currentChar == '}') {
                this.pop();
                return new Token(TokenType.R_BRACES,'}');
            }

            if (this.currentChar == '&') {
                this.pop();
                return new Token(TokenType.AND, '&');
            }

            if (this.currentChar == '|') {
                this.pop();
                return new Token(TokenType.OR, '|');
            }

            if (this.currentChar == '!') {
                this.pop();
                return new Token(TokenType.NOT, '!');
            }

            if (this.currentChar == '=') {
                this.pop();
                return new Token(TokenType.EQ, '=');
            }

            if (this.currentChar == '<') {
                this.pop();
                if (this.currentChar == '=') {
                    this.pop();
                    return new Token(TokenType.LTE, "<=");
                }
                return new Token(TokenType.LT, '<');
            }

            if (this.currentChar == '>') {
                this.pop();
                if (this.currentChar == '=') {
                    this.pop();
                    return new Token(TokenType.GTE, ">=");
                }
                return new Token(TokenType.GT, '>');
            }

            if (this.currentChar == '+') {
                this.pop();
                return new Token(TokenType.ADD, '+');
            }

            if (this.currentChar == '-') {
                this.pop();
                return new Token(TokenType.SUB, '-');
            }

            if (this.currentChar == '*') {
                this.pop();
                return new Token(TokenType.MUL, '*');
            }

            if (this.currentChar == '/') {
                this.pop();
                return new Token(TokenType.DIV, '/');
            }

            if (isDigit(this.currentChar)) {
                return new Token(TokenType.INTEGER, this.getNumberLiteral());
            }

            // Throw exception;
        }
        return new Token(TokenType.EOF, TokenType.EOF.getValue());
    }

    private static boolean isDigit(char character) {
        return character >= '0' && character <= '9';
    }

    private static boolean isAlpha(char character) {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }

    private static boolean isAlphaNum(char character) {
        return isAlpha(character) || isDigit(character);
    }
}
