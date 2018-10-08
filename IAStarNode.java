public interface IAStarNode
{
    int G { get; set; }
    int H { get; set; }
    int F { get; }
    IAStarNode Parent { get; set; }
    System.Collections.Generic.IList<IAStarNode> Neigbors { get; }
    void AddNeighbor(IAStarNode neigbor);
}
