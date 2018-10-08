import java.util.*;

public class AStar
{
    public static List<IAStarNode> GetPath(IAStarNode start, IAStarNode end, IAStarHueristic hueristic)
    {
        HashSet<IAStarNode> closedSet = new HashSet<IAStarNode>();
        List<IAStarNode> openSet = new LinkedList<IAStarNode>();
        openSet.add(start);
        start.setG(0);

        while (openSet.size() != 0)
        {
            IAStarNode currentNode = openSet.get(0);

            for(IAStarNode node : openSet)
            {
                node.setH(hueristic.GetHeuristic(node, end));

                if (node.getF() < currentNode.getF() || node.getF() == currentNode.getF() && node.getH() < currentNode.getH())
                    currentNode = node;
            }

            if (currentNode == end)
                return ConstructPath(currentNode, start);

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            for (IAStarNode neighbor : currentNode.getNeigbors())
            {
                if (closedSet.contains(neighbor))
                    continue;

                int newHueristic = currentNode.getG() + hueristic.GetHeuristic(currentNode, neighbor);

                if (!openSet.contains(neighbor))
                    openSet.add(neighbor);
                else if (newHueristic >= neighbor.getH())
                    continue;

                neighbor.setParent(currentNode);
            }
        }

        return null;
    }

    private static List<IAStarNode> ConstructPath(IAStarNode start, IAStarNode end)
    {
        Stack<IAStarNode> resultTemp = new Stack<IAStarNode>();
        List<IAStarNode> result = new LinkedList<IAStarNode>();
        IAStarNode currentNode = start;

        while (currentNode != end)
        {
            resultTemp.push(currentNode);
            currentNode = currentNode.getParent();
        }

        while (!resultTemp.isEmpty()) {
            result.add(resultTemp.pop());
        }

        return result;
    }

}