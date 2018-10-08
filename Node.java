import java.util.ArrayList;
import java.util.List;

public class Node
{

    private int x;
    private int y;
    private int value;
    private Node parent;
    private List<Node> neighbors;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        neighbors = new ArrayList<>(8);
        int totalPossibleNeighbors = 8;
        for(int i = 0;i<totalPossibleNeighbors;i++){
            neighbors.add(null);
        }
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

    public void setValue(int weight) {
        this.value = weight;
    }
}