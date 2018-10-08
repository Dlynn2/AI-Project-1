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
    private IAStarNode parent;
    private List<IAStarNode> neighbors;
    private int gValue;
    private int hValue;
    private List<IAStarNode> _neighbors;

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

    @Override
    public int getG()
    {
        return this.gValue;
    }

    @Override
    public void setG(int gValue)
    {
        this.gValue = gValue;
    }

    @Override
    public int getH()
    {
        return this.hValue;
    }

    @Override
    public void setH(int hValue)
    {
        this.hValue = hValue;
    }

    @Override
    public int getF()
    {
        return hValue + gValue;
    }

    @Override
    public IAStarNode getParent() {
        return this.parent;
    }

    @Override
    public void setParent(IAStarNode parent)
    {
        this.parent = parent;
    }

    @Override
    public List<IAStarNode> getNeigbors() {
        return _neighbors;
    }

    @Override
    public void addNeighbor(IAStarNode neighbor)
    {
        neighbors.add(neighbor);
    }


}