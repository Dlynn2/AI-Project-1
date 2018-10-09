import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements IAStarNode
{
    private static class Hueristic implements IAStarHueristic
    {
        @Override
        public int getHeuristic(IAStarNode start, IAStarNode end)
        {
            Node s = (Node) start;
            Node e = (Node) end;

            return Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
        }
    }


    private static Hueristic hueristic = new Hueristic();

    private int x;
    private int y;

    private IAStarNode IAparent;
    private int gValue;
    private int hValue;
    private List<IAStarNode> _neighbors;
    private int value;
    private boolean visited;
    private IAStarNode parent;
    private ArrayList<IAStarNode> neighbors;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        neighbors = new ArrayList<>();
        _neighbors = Collections.unmodifiableList(neighbors);
    }

    public static Hueristic getHueristic()
    {
        return hueristic;
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

    public IAStarNode getParent() {
        return this.IAparent;
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

    public void setParent(IAStarNode parent)
    {
        this.IAparent = parent;
    }

    public List<IAStarNode> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(IAStarNode neighbor)
    {
        neighbors.add(neighbor);
    }


}