/*import java.io.File;
  import java.io.IOException;
  import java.*;
  import java.util.ArrayList;

  public class Main {
      public static void main(String args[]) throws IOException {
          File maze1 = new File("C:\\Users\\Mitch\\Documents\\Computer Science\\Artifical Intelligence\\Programs\\Project1\\test.txt");
          Maze maze=new Maze(maze1);
          System.out.println(maze.getHeight());
          System.out.println(maze.getWidth());
          maze.printMaze();
          System.out.println(maze.isWall(19,8));
          System.out.println(maze.isValidLocation(0,0));
          System.out.println(maze.getEntry().getX() +" "+maze.getEntry().getY());
          System.out.println(maze.getExit().getX()+" "+maze.getExit().getY());
          BFS solve=new BFS();
          ArrayList A= new ArrayList(solve.solve(maze));
          System.out.println(A.get(0));
      }
  }*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        String filename = "open maze.txt";
        try{
            ArrayList<String> fileData = getFileData(filename);
            Maze maze = getNewMaze(fileData);
            for(String k :fileData){
                System.out.println(k);
            }
            List<Node> path = SearchMethods.greedySearch(maze.getStart(),
                    maze.getEnd());

             for (Node c : path)
            {
                maze.setValue(c.getX(), c.getY(), 'X');
            }


            print(maze);
        }catch(IOException ioe){
            System.out.println("Could not read file with name: " + filename);
            System.exit(1);
        }

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
                System.out.print((char)maze.getValueAt(x, y));
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
