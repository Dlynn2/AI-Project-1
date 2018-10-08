import java.util.*;

public class AStar
{
    public static List<IAStarNode> GetPath(IAStarNode start, IAStarNode end, IAStarHueristic hueristic)
    {
        HashSet<IAStarNode> closedSet = new HashSet<IAStarNode>();
        List<IAStarNode> openSet = new LinkedList<IAStarNode>();
        openSet.add(start);
        start.setG(0);

        //while theres more to open
        while (openSet.size() != 0)
        {
            //current node = a node in the set
            IAStarNode currentNode = openSet.get(0);

            //for each node in the set
            for(IAStarNode node : openSet)
            {
                //set the heuristic value
                node.setH(hueristic.GetHeuristic(node, end));

                //if the heuristic or the evaluation function of any given node is less than the current node
                if (node.getF() < currentNode.getF() || node.getF() == currentNode.getF() && node.getH() < currentNode.getH())
                    //change the current to the lowest eval function
                    currentNode = node;
            }

            //if end found return the path to get to end
            if (currentNode == end)
                return ConstructPath(currentNode, start);

            //remove the node checked from the 'frontier'
            openSet.remove(currentNode);
            //add to checked nodes
            closedSet.add(currentNode);

            //for each neighbor of curr node
            for (IAStarNode neighbor : currentNode.getNeigbors())
            {
                //if has been checked just move on to next node
                if (closedSet.contains(neighbor))
                    continue;

                //find the heuristic of getting to the neighbor node
                int newHueristic = currentNode.getG() + hueristic.GetHeuristic(currentNode, neighbor);

                //if neighbor is not already on frontier, then add
                if (!openSet.contains(neighbor))
                    openSet.add(neighbor);
                //if heuristic is to large move to next neighbor
                else if (newHueristic >= neighbor.getH())
                    continue;

                neighbor.setParent(currentNode);
            }
        }

        //if no path found return null
        return null;
    }

    //method to return A* path
    private static List<IAStarNode> ConstructPath(IAStarNode start, IAStarNode end)
    {
        //first in first out
        Queue<IAStarNode> resultTemp = new LinkedList<IAStarNode>();
        List<IAStarNode> result = new LinkedList<IAStarNode>();
        IAStarNode currentNode = end;

        //while we have not completed and entire path
        while (currentNode != start)
        {
            //add this node to the path
            resultTemp.add(currentNode);
            //get the next node on the path
            currentNode = currentNode.getParent();
        }

        //for each node on the path add to result
        for(IAStarNode node : resultTemp){
            result.add(node);
        }

        return result;
    }

}