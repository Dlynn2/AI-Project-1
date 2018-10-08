using System;
        using System.Collections;
        using System.Collections.Generic;

public static class AStar
{
    public static List<IAStarNode> GetPath(IAStarNode start, IAStarNode end, IAStarHueristic hueristic)
    {
        HashSet<IAStarNode> closedSet = new HashSet<IAStarNode>();
        List<IAStarNode> openSet = new List<IAStarNode>();
        openSet.Add(start);
        start.G = 0;

        while (openSet.Count != 0)
        {
            IAStarNode currentNode = openSet[0];

            foreach (IAStarNode node in openSet)
            {
                node.H = hueristic.GetHeuristic(node, end);

                if (node.F < currentNode.F || node.F == currentNode.F && node.H < currentNode.H)
                    currentNode = node;
            }

            if (currentNode == end)
                return ConstructPath(currentNode, start);

            openSet.Remove(currentNode);
            closedSet.Add(currentNode);

            foreach (IAStarNode neighbor in currentNode.Neigbors)
            {
                if (closedSet.Contains(neighbor))
                    continue;

                int newHueristic = currentNode.G + hueristic.GetHeuristic(currentNode, neighbor);

                if (openSet.Contains(neighbor) == false)
                    openSet.Add(neighbor);
                else if (newHueristic >= neighbor.H)
                    continue;

                neighbor.Parent = currentNode;
            }
        }

        return null;
    }

    private static List<IAStarNode> ConstructPath(IAStarNode start, IAStarNode end)
    {
        List<IAStarNode> result = new List<IAStarNode>();
        IAStarNode currentNode = start;

        while (currentNode != end)
        {
            result.Add(currentNode);
            currentNode = currentNode.Parent;
        }

        result.Reverse();

        return result;
    }

}
