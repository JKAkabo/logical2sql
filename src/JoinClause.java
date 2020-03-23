public class JoinClause {
    private String leftTableName;

    private String rightTableName;

    private String leftColumnName;

    private String leftColumn;

    private String rightColumnName;

    private String rightColumn;

    private boolean isFirst;

    public JoinClause() {
    }

    public JoinClause(Variable left, Token operator, Variable right, boolean isFirst) {
        this.leftTableName = left.getTableName();
        this.rightTableName = right.getTableName();
        this.leftColumnName = left.getColumnName();
        this.rightColumnName = right.getColumnName();
        this.leftColumn = left.name;
        this.rightColumn = right.name;
        this.isFirst = isFirst;
    }

    public void setLeft(Variable left) {
        this.leftTableName = left.getTableName();
        this.leftColumnName = left.getColumnName();
        this.leftColumn = left.name;
    }

    public void setRight(Variable right) {
        this.rightTableName = right.getTableName();
        this.rightColumnName = right.getColumnName();
        this.rightColumn = right.name;
    }

    public String getLeftTableName() {
        return leftTableName;
    }

    public void setLeftTableName(String leftTableName) {
        this.leftTableName = leftTableName;
    }

    public String getRightTableName() {
        return rightTableName;
    }

    public void setRightTableName(String rightTableName) {
        this.rightTableName = rightTableName;
    }

    public String getLeftColumnName() {
        return leftColumnName;
    }

    public void setLeftColumnName(String leftColumnName) {
        this.leftColumnName = leftColumnName;
    }

    public String getRightColumnName() {
        return rightColumnName;
    }

    public void setRightColumnName(String rightColumnName) {
        this.rightColumnName = rightColumnName;
    }

    public String getLeftColumn() {
        return leftColumn;
    }

    public void setLeftColumn(String leftColumn) {
        this.leftColumn = leftColumn;
    }

    public String getRightColumn() {
        return rightColumn;
    }

    public void setRightColumn(String rightColumn) {
        this.rightColumn = rightColumn;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public String getRepresentation() {
        return this.getLeftTableName() + " --> " + this.getRightTableName();
    }

    @Override
    public String toString() {
        String string = "";
        if (isFirst)
            string = this.getLeftTableName();
        return string + " JOIN " + this.getRightTableName() + " ON " + this.getLeftColumn() + " = " + this.getRightColumn();
    }
}
