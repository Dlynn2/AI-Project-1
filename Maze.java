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
                setNeighbors(data[x][y]);
            }
        }
    }

    private void setNeighbors(Node node)
    {
        int x = node.getX();
        int y = node.getY();
        //inProgress - assuming that neighbors can be corners
        //adding in order: top-left -> top-right -> bottom-right -> bottom-left
        if(isWithinBounds(x-1, y-1)){
            node.addNeighbor(data[x-1][y-1]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x, y-1)){
            node.addNeighbor(data[x][y-1]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x+1, y-1)){
            node.addNeighbor(data[x+1][y-1]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x+1, y)){
            node.addNeighbor(data[x+1][y]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x+1, y+1)){
            node.addNeighbor(data[x+1][y+1]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x, y+1)){
            node.addNeighbor(data[x][y+1]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x-1, y+1)){
            node.addNeighbor(data[x-1][y+1]);
        }else{
            node.addNeighbor(null);
        }
        if(isWithinBounds(x-1, y)){
            node.addNeighbor(data[x-1][y]);
        }
    }
    public void resetVisited(){
        for(int x =0;x<data.length;x++){
            for(int y = 0;y<data[0].length;y++){
                data[x][y].setVisited(false);
            }
        }
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
            data[x][y].setG(value);
    }

    public int getValueAt(int x, int y)
    {
        if(isWithinBounds(x, y))
            return data[x][y].getG();
        else
            return Integer.MIN_VALUE;
    }

}
