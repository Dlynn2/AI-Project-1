import java.util.List;

public interface IAStarNode
{
    int getValue();
    boolean isVisited();
    void setVisited(boolean b);
    int getX();
    int getY();
    int getG();
    void setG(int gValue);
    int getH();
    void setH(int hValue);
    int getF();
    IAStarNode getParent();
    void setParent(IAStarNode parent);
    List<IAStarNode> getNeighbors();
    void addNeighbor(IAStarNode neighbor);
}
