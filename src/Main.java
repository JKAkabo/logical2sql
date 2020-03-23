public class Main {

    public static void main(String[] args) {
        String expression = "{a.b.c} = {d.e.f} & {a.b.d} = {d.e.g} & {a.b.d} = 2";
        Lexer lexer = new Lexer(expression);
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);

        System.out.println("Interpreter result --> " + interpreter.interpret());
    }
}
