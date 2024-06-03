//  H.M.Malshi Sathsarani
//  IIT - 20221118   UOW - 19995021


import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "";

        //while loop for user enter "0"
        while (!filePath.equals("0")) {
            System.out.println("............................................................");
            System.out.println("                                                            ");
            System.out.println("Please enter the file path:");
            //Read the file path
            filePath = scanner.nextLine();

            if (!filePath.equals("0")) {
                try {
                    //parsing map from the provided file
                    Map map = MapParser.parseMap("mazes/"+filePath);
                    System.out.println("The file: " + filePath);
                    //find the shortest path in the map
                    List<Node> path = ShortestPathFinder.findShortestPath(map);

                    if (path != null) {
                        System.out.println("Shortest path found:");
                        int steps = 1;
                        Node prevNode = null;
                        // Iterating through the nodes in the path
                        for (Node node : path) {
                            if(steps == 1) {
                                System.out.println(steps + " Start at (" + (node.col + 1) + "," + (node.row + 1) + ")");
                            } else {
                                // Getting the direction
                                String direction = getDirection(prevNode, node);
                                System.out.println(steps + " Move " + direction + " to (" + (node.col+1) + ", " + (node.row+1) + ")");
                            }
                            prevNode = node;
                            steps += 1;
                        }
                    } else {
                        System.out.println("No path found from start to finish.");
                    }
                } catch (IOException e) {
                    // Handling reading errors
                    System.err.println("Error reading the file: " + e.getMessage());
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Handling errors in parsing the maze file
                    System.err.println("Error parsing the maze file: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }

    // Method to get the direction between nodes
    private static String getDirection(Node prevNode, Node currentNode) {
        if (prevNode.col < currentNode.col) {
            return "right";
        } else if (prevNode.col > currentNode.col) {
            return "left";
        } else if (prevNode.row < currentNode.row) {
            return "down";
        } else {
            return "up";
        }
    }
}
