import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {

        ArrayList<String> fileData = getFileData("test.txt");

        Maze maze = getNewMaze(fileData);

        List<IAStarNode> path = SearchMethods.BFS(maze.getStart(),maze.getEnd());

        path = SearchMethods.AStar(maze.getStart(),
                maze.getEnd(), Node.getHueristic());

        for (IAStarNode c : path)
        {
            Node node = (Node)c;
            maze.setValue(node.getX(), node.getY(), 'X');
        }


        print(maze);
    }

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

    public static void print(Maze maze)
    {
        for (int x = 0; x < maze.getWidth(); x++)
        {
            for (int y = 0; y < maze.getHeight(); y++)
            {
                System.out.print((char)maze.getValueAt(x, y) + " ");
            }
            System.out.println();
        }
    }

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
