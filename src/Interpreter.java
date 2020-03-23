import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Interpreter extends NodeVisitor {

    private String baseTable;

    private Set<JoinClause> joinClauses = new HashSet<>();

    private JoinClause joinClause = new JoinClause();
//    private WhereClause whereClause = new WhereClause();

    private boolean isBuildingWhere = false;
    private boolean isBuildingJoin = false;

    Parser parser;


    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    public String visitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {
        return this.visit(binaryArithmeticOperator.getLeft()) +
                " " +
                binaryArithmeticOperator.getOperator().getValue() +
                " " +
                this.visit(binaryArithmeticOperator.getRight());
    }

    public String visitNumber(Number number) {
        return number.value.toString();
    }

    public String visitUnaryArithmeticOperator(UnaryArithmeticOperator unaryArithmeticOperator) {
        StringBuilder stringBuilder = new StringBuilder();
        if (unaryArithmeticOperator.getOperator().getType() == TokenType.SUB) {
            stringBuilder.append("-");
            stringBuilder.append(this.visit(unaryArithmeticOperator.getRight()));
            return stringBuilder.toString();
        }
        return this.visit(unaryArithmeticOperator.getRight());
    }

    public String visitConstantNode(ConstantNode constantNode) {
        return "Ken here --> Constant here";
    }

    public String visitFunction(Function function) {
        return "Ken here --> Function here";
    }

    public String visitVariable(Variable variable) {
        if (this.baseTable == null) {
            this.baseTable = this.getTableName(variable.name);
            this.joinClause.setLeft(variable);
            isBuildingJoin = true;
        } else {
            if (!isBuildingJoin)
                joinClause.setLeft(variable);
            else {
                joinClause.setRight(variable);
                if (!getJoinClauseRepresentations().contains(joinClause.getRepresentation()) && !this.joinClause.getLeftTableName().equals(this.joinClause.getRightTableName())) {
                    joinClauses.add(joinClause);
                }
                joinClause = new JoinClause();
            }
            isBuildingJoin = !isBuildingJoin;
        }
        return variable.name;
    }

    public String visitComparisonOperator(ComparisonOperator comparisonOperator) {
        return " " +
                this.visit(comparisonOperator.left) +
                " " +
                comparisonOperator.operator.getValue() +
                " " +
                this.visit(comparisonOperator.right) +
                " ";
    }

    public String visitUnaryLogicalOperator(UnaryLogicalOperator unaryLogicalOperator) {
        return "Ken here --> Unary Logical Operator here";
    }

    public String visitBinaryLogicalOperator(BinaryLogicalOperator binaryLogicalOperator) {
        return " " +
                this.visit(binaryLogicalOperator.getLeft()) +
                " " +
                binaryLogicalOperator.getOperator().getType().toString() +
                " " +
                this.visit(binaryLogicalOperator.getRight()) +
                " ";
    }

    public String interpret() {
        Node tree = this.parser.parse();
        String where = this.visit(tree);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM " + this.baseTable);
        for (JoinClause joinClause : this.joinClauses) {
            stringBuilder.append(joinClause.toString());
        }
        stringBuilder.append(" WHERE " + where);
        return stringBuilder.toString();
    }


    //
    private String getTableName(String columnName) {
        String[] parts = columnName.split("\\.");
        return String.join(".", Arrays.copyOfRange(parts, 0, parts.length - 1));
    }

    private Set<String> getJoinClauseRepresentations() {
        return joinClauses.stream().map(JoinClause::getRepresentation).collect(Collectors.toSet());
    }
}
