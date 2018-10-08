import java.util.List;

public interface IAStarNode
{
    int getG();
    void setG(int gValue);
    int getH();
    void setH(int hValue);
    int getF();
    IAStarNode getParent();
    void setParent(IAStarNode parent);
    List<IAStarNode> getNeigbors();
    void addNeighbor(IAStarNode neighbor);
}
