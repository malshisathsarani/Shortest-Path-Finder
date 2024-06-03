public class Map {
    // represent start and finish nodes.
    Node[][] maze;
    Node start;
    Node finish;

    // Constructor for the Map class.
    public Map(int rows, int cols) {
        this.maze = new Node[rows][cols];
    }

    public void setNode(int row, int col, Node node) {
        this.maze[row][col] = node;
        // If the node is a start or finish node, it updates the corresponding field.
        if (node.isStart) {
            this.start = node;
        } else if (node.isFinish) {
            this.finish = node;
        }
    }
    // Returns the node in the maze
    public Node getNode(int row, int col) {
        return this.maze[row][col];
    }
}