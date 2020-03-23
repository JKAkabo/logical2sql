public class Parser {
    Lexer lexer;
    Token currentToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = this.lexer.getNextToken();
    }

    public void eat(TokenType tokenType) {
        if (this.currentToken.getType() == tokenType) {
            this.currentToken = this.lexer.getNextToken();
        } else {
            // throw exception;
        }
    }

    public Node factor() {
        Token token = this.currentToken;
        if (token.getType() == TokenType.ADD) {
            this.eat(TokenType.ADD);
            return new UnaryArithmeticOperator(token, this.factor());
        } else if (token.getType() == TokenType.SUB) {
            this.eat(TokenType.SUB);
            return new UnaryArithmeticOperator(token, this.factor());
        } else if (token.getType() == TokenType.INTEGER) {
            this.eat(TokenType.INTEGER);
            return new Number(token);
        } else if (token.getType() == TokenType.L_PARENTHESIS) {
            this.eat(TokenType.L_PARENTHESIS);
            Node node = this.parse();
            this.eat(TokenType.R_PARENTHESIS);
            return node;
//        } else if (token.getType() == TokenType.FUNCTION) {
////            this.eat(TokenType.FUNCTION);
//            return this.function();
        } else if (token.getType() == TokenType.CONSTANT) {
            this.eat(TokenType.CONSTANT);
            return new ConstantNode(token);
        } else if (token.getType() == TokenType.VARIABLE) {
            this.eat(TokenType.VARIABLE);
            this.eat(TokenType.R_BRACES);
            return new Variable(token);
        }
        // throw exception
        return null;
    }

    public Node term() {
        Node node = this.factor();

        while (this.currentToken.getType() == TokenType.MUL || this.currentToken.getType() == TokenType.DIV) {
            Token token = this.currentToken;
            if (token.getType() == TokenType.MUL) {
                this.eat(TokenType.MUL);
            }
            node = new BinaryArithmeticOperator(node, token, this.factor());
        }
        return node;
    }

    public Node compare() {
        Node node = this.term();
        while (this.currentToken.getType() == TokenType.ADD || this.currentToken.getType() == TokenType.SUB) {
            Token token = this.currentToken;
            if (this.currentToken.getType() == TokenType.ADD) {
                this.eat(TokenType.ADD);
            } else {
                this.eat(TokenType.SUB);
            }
            node = new BinaryArithmeticOperator(node, token, this.factor());
        }
        return node;
    }

    public Node logic() {
        Node node = this.compare();
        while (this.currentToken.getType() == TokenType.LT || this.currentToken.getType() == TokenType.GT ||
                this.currentToken.getType() == TokenType.LTE || this.currentToken.getType() == TokenType.GTE ||
                this.currentToken.getType() == TokenType.EQ || this.currentToken.getType() == TokenType.NEQ) {
            Token token = this.currentToken;
            this.eat(token.getType());
            return new ComparisonOperator(node, token, this.compare());
        }
        return node;
    }

    public Node unaryParse() {
//        Node node = this.logic();
        while (this.currentToken.getType() == TokenType.NOT) {
            Token token = this.currentToken;
            this.eat(token.getType());
            return new UnaryLogicalOperator(token, this.logic());
        }
        return this.logic();
    }

    public Node parse() {
        Node node = this.logic();
        while (this.currentToken.getType() == TokenType.AND || this.currentToken.getType() == TokenType.OR) {
            Token token = this.currentToken;
            this.eat(token.getType());
            return new BinaryLogicalOperator(node, token, this.parse());
        }
        return node;
    }
}
