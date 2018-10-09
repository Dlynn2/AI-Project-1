public class Heuristic
{
    public int getHeuristic(Node start, Node end)
    {
        Node s = start;
        Node e = end;
        return Math.abs(s.getX() - e.getX()) + Math.abs(s.getY() - e.getY());
    }
}
