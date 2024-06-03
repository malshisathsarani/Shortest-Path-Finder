public class Node {
    //represent the row, column, is start, is finish and is rock
    int row;
    int col;
    boolean isStart;
    boolean isFinish;
    boolean isRock;

    public Node(int row, int col, boolean isStart, boolean isFinish, boolean isRock) {
        this.row = row;
        this.col = col;
        this.isStart = isStart;
        this.isFinish = isFinish;
        this.isRock = isRock;
    }
    //provide a string representation of the node.
    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", isStart=" + isStart +
                ", isFinish=" + isFinish +
                ", isRock=" + isRock +
                '}';
    }
}