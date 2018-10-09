import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        //This is in a per use setting and does not change dynamically to the other maze's
        String filename = "medium maze.txt";
        try{
            ArrayList<String> fileData = getFileData(filename);
            Maze currentMaze = getNewMaze(fileData);
            //This is also per use, if you want to change it you must change the SearchMethods.(searchMethod)
            List<Node> path = SearchMethods.DFS(currentMaze.getStart(),currentMaze.getEnd());
            for (Node c : path) { //setting path
                Node node = c;
                currentMaze.setValue(node.getX(), node.getY(), '.');
            }
            System.out.println("Path count: " + path.size() + "\tExpanded Count: " + SearchMethods.getExpandedCount());
            print(currentMaze);
        }catch(IOException ioe){
            System.out.println("Could not read file with name: " + filename);
            System.exit(1);
        }
    }
    /**
     * Constructs a new maze based on data passed from a textfile formatted like a maze, % is a wall, P is start, . is
     * the finish
     * @param data
     * @return
     */
    public static Maze getNewMaze(ArrayList<String> data)
    {
        int width = data.size();
        int height = data.get(0).length();
        Maze result = new Maze(width, height);
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                char currentCell = data.get(x).charAt(y);
                result.setValue(x, y, currentCell);
                switch (currentCell)
                {
                    case 'P':
                        result.setStart(x, y);
                        result.setValue(x, y, ' ');
                        break;
                    case '*':
                        result.setEnd(x, y);
                        result.setValue(x, y, ' ');
                        break;
                    default:
                        break;
                }
            }
        }

        return  result;
    }
    /**
     * Prints a maze object from the data in each node
     */
    public static void print(Maze maze)
    {
        for (int x = 0; x < maze.getWidth(); x++)
        {
            for (int y = 0; y < maze.getHeight(); y++)
            {
                System.out.print((char)maze.getValueAt(x, y));
            }
            System.out.println();
        }
    }
    /**
     *Gets the file data from the specified and puts it into an ArrayList of type string
     */
    public static ArrayList<String> getFileData(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<String> result = new ArrayList<>();
        while (scanner.hasNext())
        {
            String line = scanner.nextLine();
            result.add(line);
        }
        return result;
    }
}
