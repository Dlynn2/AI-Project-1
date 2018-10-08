import java.util.*;

public class Room implements IAStarNode {
    private int G;
    public int getG(){
        return G;
    }
    public void setG(int val){
        G = val;
    }

    private int h;
    public int getH(){
        return h;
    }
    public void setH(int val){
        h = val;
    }

    public int getF(){
        return h + G;
    }

    private IAStarNode parent;
    public IAStarNode getParent(){
        return parent;
    }
    public void setParent(IAStarNode val){
        parent = val;
    }

    private List<IAStarNode> neigbors;
    public List<IAStarNode> getNeigbors(){
        return neigbors;
    }

    public int X;
    public int Y;


    public Room(int x, int y){
        X = x;
        Y = y;
        G = 0;
    }
    public Room(int x, int y, int g)
    {
        X = x;
        Y = y;
        G = g;
        neigbors = new LinkedList<IAStarNode>();
    }

    public void AddNeighbor(IAStarNode neigbor)
    {
        neigbors.add(neigbor);
    }
}