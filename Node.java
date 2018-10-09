import java.util.ArrayList;
import java.util.List;

/**
 * Node class is used to hold each x,y coordinate along with its neighbors.
 */
public class Node
{
    private static Heuristic heuristic = new Heuristic();
    private int x;
    private int y;
    private Node IAparent;
    private int gValue;
    private int hValue;
    private boolean visited;
    private Node parent;
    private ArrayList<Node> neighbors;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        neighbors = new ArrayList<>();
    }

    public static Heuristic getHeuristic()
    {
        return heuristic;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getG()
    {
        return this.gValue;
    }

    public void setG(int gValue)
    {
        this.gValue = gValue;
    }

    public int getH()
    {
        return this.hValue;
    }

    public void setH(int hValue)
    {
        this.hValue = hValue;
    }

    public int getF()
    {
        return hValue + gValue;
    }

    public Node getParent() {
        return this.IAparent;
    }

    public void setVisited(boolean b){
        visited = b;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setParent(Node parent)
    {
        this.IAparent = parent;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor)
    {
        neighbors.add(neighbor);
    }


}