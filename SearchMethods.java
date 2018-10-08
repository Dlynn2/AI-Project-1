import java.util.*;

public class SearchMethods
{
    private SearchMethods(){}

    public static List<IAStarNode> BFS(Node start, Node end)
    {
        HashSet<IAStarNode> closedSet = new HashSet<>();
        Queue<IAStarNode> openSet = new LinkedList<>();
        openSet.add(start);

        while (openSet.isEmpty() == false)
        {
            IAStarNode currentNode = openSet.poll();

            if (closedSet.contains(currentNode))
                continue;

            if(currentNode == end)
                return backtrackPath(currentNode);

            closedSet.add(currentNode);
            for(IAStarNode neighbor : currentNode.getNeigbors())
            {
                if(closedSet.contains(neighbor) == false && currentNode.getG() == neighbor.getG())
                {
                    openSet.add(neighbor);
                    neighbor.setParent(currentNode);
                }
            }
        }


        return Collections.emptyList();
    }

    private static List<IAStarNode> backtrackPath(IAStarNode start) {

        List<IAStarNode> path = new ArrayList<>();
        IAStarNode current = start;

        while (current != null)
        {
            path.add(current);
            current = current.getParent();
        }

        return path;
    }


    public static List<IAStarNode> AStar(IAStarNode start, IAStarNode end, IAStarHueristic hueristic)
    {
        HashSet<IAStarNode> closedSet = new HashSet<>();
        List<IAStarNode> openSet = new ArrayList<>();
        openSet.add(start);
        //start.setG(0);

        while (openSet.size() != 0)
        {
            IAStarNode currentNode = openSet.get(0);

            for (IAStarNode node : openSet)
            {
                int h = hueristic.getHeuristic(node, end);
                node.setH(h);

                if (node.getF() < currentNode.getF() || node.getF() == currentNode.getF() && node.getH() < currentNode.getH())
                    currentNode = node;
            }

            if (currentNode == end)
                return backtrackPath(currentNode);

            openSet.remove(currentNode);
            closedSet.add(currentNode);

            for (IAStarNode neighbor : currentNode.getNeigbors())
            {
                if (closedSet.contains(neighbor))
                    continue;

                //Checking for walls
                if(currentNode.getG() != neighbor.getG())
                {
                    closedSet.add(neighbor);
                    continue;
                }

                int newGValue = currentNode.getG() + neighbor.getG();

                if (openSet.contains(neighbor) == false)
                    openSet.add(neighbor);
                else if (newGValue >= neighbor.getG())
                    continue;

                neighbor.setParent(currentNode);
            }
        }

        return null;
    }


}
