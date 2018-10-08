import java.util.ArrayList;
import java.util.List;

public class Node
{

    private int x;
    private int y;
    private int value;
    private boolean visited;
    private Node parent;
    private ArrayList<Node> neighbors;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        neighbors = new ArrayList<Node>();
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public void addNeighbor(Node neighbor)
    {
        neighbors.add(neighbor);
    }

    public void setNeighbor(int index,Node value){
        neighbors.set(index,value);
    }

    public int getValue() {
        return value;
    }

    public void setVisited(boolean b){
        visited = b;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setValue(int weight) {
        this.value = weight;
    }
}