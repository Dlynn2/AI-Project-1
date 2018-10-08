/*
  import java.util.List;
  import java.io.File;
  import java.io.IOException;
  import java.util.Arrays;
  import java.util.Scanner;
  public class Maze {
      private int currentRow;
      private int currentCol;
      char [][] maze;
      int length;
      int height;
      private static final char pacman='P';
      private static final char goal='*';
      private static final char wall='%';
      private static final char marked='.';
      private static final char unknown=' ';
      private Coordinate start;
      private Coordinate end;
      boolean [][] visited;

      public Maze(File maze) throws IOException {
          GenerateMaze(maze);
      }
      public void GenerateMaze(File fileName) throws IOException{
            System.out.println("What file would you like to send in?");
            String fileName = new Scanner(System.in).next();
            File file=new File(fileName);
          Scanner scan = new Scanner(fileName);
          int count=0;
          String row=scan.nextLine();
          maze=new char[row.length()][];
          maze[count] = row.toCharArray();
          count++;
          visited = new boolean[maze.length][maze[0].length];
          while(scan.hasNext()) {
              row = scan.nextLine();
              maze[count] = row.toCharArray();
              count++;
              height=count;
          }
          }
      public void printMaze() {
          for (int i = 0; i < height; i++) {
              for (int j = 0; j < maze[i].length; j++) {
                  if (i == currentRow && j == currentCol) {
                      maze[i][j] = marked;
                      System.out.print(pacman + " ");

                  } else
                  if (maze[i][j]==pacman)
                      start=new Coordinate(i,j);
                  else if (maze[i][j]==goal)
                      end=new Coordinate(i,j);
                      System.out.print(maze[i][j] + " ");
              }
              System.out.println();
          }
      }

      public int getHeight() {
          return height;
      }

      public int getWidth() {
          return maze[0].length;
      }

      public Coordinate getEntry() {
          return start;
      }

      public Coordinate getExit() {
          return end;
      }

      public boolean isExit(int x, int y) {
          return x == end.getX() && y == end.getY();
      }

      public boolean isStart(int x, int y) {
          return x == start.getX() && y == start.getY();
      }

      public boolean visited(int row, int col) {
          return visited[row][col];
      }
      public boolean isExplored(int row, int col) {
          return visited[row][col];
      }
      public void setVisited(int row, int col,boolean val) {
          visited[row][col]=val;
      }

      public boolean isWall(int row, int col) {
          return maze[row][col] == wall;
      }
      public boolean isValidLocation(int row, int col) {
          if (row < 1 || row >= getHeight() || col < 1 || col >= getWidth()) {
              return false;
          }
          return true;
      }
      public void printcord(List<Coordinate> L,int i){
          System.out.println(L.get(i).getX()+","+L.get(i).getY());
      }

}*/
public class Maze
{
    private int width;
    private int height;
    private Node data[][];

    private Node start;
    private Node end;

    private Maze(){}
    public Maze(int width, int height)
    {
        this.width = width;
        this.height = height;
        inititializeData();
    }

    public void setStart(int x, int y)
    {
        this.start = data[x][y];
    }

    public void setEnd(int x, int y)
    {
        this.end = data[x][y];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    private void inititializeData()
    {
        data = new Node[width][];

        for (int x = 0; x < width; x++)
        {
            data[x] = new Node[height];
            for (int y = 0; y < height; y++)
            {
                data[x][y] = new Node(x, y);

            }
        }
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                setNeighbors(data[x][y]);//able to combine
            }
        }
    }

    private void setNeighbors(Node node)
    {
        int x = node.getX();
        int y = node.getY();

        //inProgress - assuming that neighbors can be corners
        //adding in order: top-left -> top-right -> bottom-right -> bottom-left
        if(isWithinBounds(x-1, y-1)) node.setNeighbor(0,data[x-1][y-1]);
        if(isWithinBounds(x, y-1)) node.setNeighbor(1,data[x][y-1]);
        if(isWithinBounds(x+1, y-1)) node.setNeighbor(2,data[x+1][y-1]);
        if(isWithinBounds(x+1, y)) node.setNeighbor(3,data[x+1][y]);
        if(isWithinBounds(x+1, y+1)) node.setNeighbor(4,data[x+1][y+1]);
        if(isWithinBounds(x, y+1)) node.setNeighbor(5,data[x][y+1]);
        if(isWithinBounds(x-1, y+1)) node.setNeighbor(6,data[x-1][y+1]);
        if(isWithinBounds(x-1, y)) node.setNeighbor(7,data[x-1][y]);
    }

    private boolean isWithinBounds(int x, int y)
    {
        if(x < 0 || x >= width)
            return false;

        if(y < 0 || y >= height)
            return false;

        return true;
    }

    public Node getEnd() {
        return end;
    }

    public Node getStart() {
        return start;
    }

    public void setValue(int x, int y, int value)
    {
        if(isWithinBounds(x, y))
            data[x][y].setValue(value);
    }
    public int getValueAt(int x, int y)
    {
        if(isWithinBounds(x, y))
            return data[x][y].getValue();
        else
            return Integer.MIN_VALUE;
    }

}
