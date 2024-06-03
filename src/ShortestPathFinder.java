import java.util.*;

class ShortestPathFinder {
    //breadth-first search algorithm to find the shortest path.
    public static List<Node> findShortestPath(Map map) {
        // Initializing the queue, parent map, and visited set.
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Node> parent = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        // Adding the start node
        queue.add(map.start);
        visited.add(map.start);
        //remove the first one and check if it's the finish node.
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.isFinish) {
                return constructPath(parent, curr);
            }
            // For each unvisited neighbor of the current node, add it to the queue and visited set,
            // set its parent to the current node.
            getNeighbors(map, curr).stream()
                    .filter(neighbor -> !visited.contains(neighbor))
                    .forEach(neighbor -> {
                        queue.add(neighbor);
                        parent.put(neighbor, curr);
                        visited.add(neighbor);
                    });
        }
        // If no path was found
        return null;
    }

    // The constructPath method constructs the shortest path by following the parent nodes
    // from the finish node to the start node.
    private static List<Node> constructPath(HashMap<Node, Node> parent, Node finish) {
        List<Node> path = new ArrayList<>();
        Node curr = finish;
        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }
        // The path is reversed because
        // it was constructed from the finish node to the start node.
        Collections.reverse(path);
        return path;
    }

    // returns a list of all valid neighbors of a given node.
    private static List<Node> getNeighbors(Map map, Node node) {
        List<Node> neighbors = new ArrayList<>();
        // Checking all four directions (down, up, right, left).
        checkDirection(map, node, 1, 0, neighbors); // Down
        checkDirection(map, node, -1, 0, neighbors); // Up
        checkDirection(map, node, 0, 1, neighbors); // Right
        checkDirection(map, node, 0, -1, neighbors); // Left
        return neighbors;
    }
    // The checkDirection method checks a given direction from a node
    // adds the farthest valid node in that direction to the neighbors list.
    private static void checkDirection(Map map, Node node, int rowChange, int colChange, List<Node> neighbors) {
        Node curr = null;
        for (int i = 0; i < map.maze.length; i++) {
            int newRow = node.row + i * rowChange;
            int newCol = node.col + i * colChange;
            if (!isValid(map, newRow, newCol)) {
                break;
            }
            curr = map.getNode(newRow, newCol);
            if (curr.isFinish) {
                break;
            }
        }
        if (curr != null) {
            neighbors.add(curr);
        }
    }

    //if a position is within the map bounds and not a rock.
    private static boolean isValid(Map map, int row, int col) {
        if (row >= 0 && row < map.maze.length && col >= 0 && col < map.maze[0].length) {
            Node node = map.getNode(row, col);
            return node != null && !node.isRock;
        }
        return false;
    }

}