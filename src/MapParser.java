import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapParser {
    //reads a file and creates a Map object
    public static Map parseMap(String filename) throws IOException {
        //store the lines of the file.
        List<String> lines = new ArrayList<>();
        // ensure the BufferedReader is closed.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        // Determining the number of rows and columns
        int rows = lines.size();
        int cols = lines.stream().mapToInt(String::length).max().orElse(0);

        // Creating a new Map object with the determined rows and columns.
        Map map = new Map(rows, cols);
        // Iterating over the lines to create nodes and add them to the map.
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                char ch = line.charAt(col);
                // Determining the type of the node based on the character in the file.
                boolean isStart = ch == 'S';
                boolean isFinish = ch == 'F';
                boolean isRock = ch == '0';
                // Adding the node to the map.
                map.setNode(row, col, new Node(row, col, isStart, isFinish, isRock));
            }
        }
        return map;
    }

}