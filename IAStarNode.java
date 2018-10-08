import java.util.List;
public interface IAStarNode
{
    int getG();
    void setG(int g);
    int getH();
    void setH(int h);
    int getF();
    IAStarNode getParent();
    void setParent(IAStarNode Parent);
    List<IAStarNode> getNeigbors();
    //void AddNeighbor(IAStarNode neigbor);
}