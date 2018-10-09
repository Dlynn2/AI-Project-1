import sun.awt.image.ImageWatched;

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
            for(IAStarNode neighbor : currentNode.getNeighbors())
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

            for (IAStarNode neighbor : currentNode.getNeighbors())
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
        //if start is null return empty list
        return Collections.emptyList();



    }
    /**
     * An implementation of a greedy search algorithm where the algorithm
     * picks the "Most weighted" direction where the best move is one that
     * iterates the x and y one closer to the final node, and the worst move
     * is one that is x and y one away from the final node
     */
    public static List<IAStarNode> greedySearch(Node start,Node end){
        class MakeQueue{ //Only needed for this search so specific function needed
            /**
             * Builds priority Queue based on the current direction of x and y;
             */
            public Queue<IAStarNode> getMostPreferred(int xDirection,int yDirection,Node current){
                Queue<IAStarNode> preferredBuilder = new LinkedList<>();
                switch(xDirection){//Going through all the options and associating weight to neighbor nodes based on that
                    case 1:
                        switch(yDirection){
                            case 1:
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                return preferredBuilder;
                            case 0:
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                return preferredBuilder;
                            case -1:
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                return preferredBuilder;
                        }
                        break;
                    case 0:
                        switch(yDirection){
                            case 1:
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                return preferredBuilder;
                            case -1:
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                return preferredBuilder;
                        }
                        break;
                    case -1:
                        switch(yDirection){
                            case 1:
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                return preferredBuilder;
                            case 0:
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                return preferredBuilder;
                            case -1:
                                preferredBuilder.add(current.getNeighbors().get(0));
                                preferredBuilder.add(current.getNeighbors().get(1));
                                preferredBuilder.add(current.getNeighbors().get(7));
                                preferredBuilder.add(current.getNeighbors().get(2));
                                preferredBuilder.add(current.getNeighbors().get(6));
                                preferredBuilder.add(current.getNeighbors().get(3));
                                preferredBuilder.add(current.getNeighbors().get(5));
                                preferredBuilder.add(current.getNeighbors().get(4));
                                return preferredBuilder;
                        }
                        break;
                }
                return preferredBuilder;
            }
        }
        Queue<IAStarNode> preferred; //list of preferred nodes
        MakeQueue makePriorityQueue = new MakeQueue();//priority Queue class for making priority queues
        IAStarNode currentNode = start;
        int finalX = end.getX();
        int finalY = end.getY();
        while(currentNode != end){//When we are not at the end
            int currentX = currentNode.getX();
            int currentY = currentNode.getY();
            //Getting the direction we need to go to associate greed
            int offsetX = finalX - currentX;
            int offsetY = finalY - currentY;
            if(offsetX < 0){
                offsetX = -1;
            }else if(offsetX > 0){
                offsetX = 1;
            }
            if(offsetY < 0){
                offsetY = -1;
            }else if(offsetY > 0){
                offsetY = 1;
            }
            preferred = makePriorityQueue.getMostPreferred(offsetX,offsetY,(Node)currentNode);
            Node preferredNode = (Node)preferred.poll();
            while(preferredNode != null){
                if(preferredNode.getValue() == (int)' ' && !preferredNode.isVisited()){
                    preferredNode.setVisited(true);
                    preferredNode.setParent(currentNode);
                    currentNode = preferredNode;
                    break;
                }else {
                    preferredNode = (Node)preferred.poll();
                    if(preferred.size() == 0){
                        currentNode = (Node)currentNode.getParent();
                        break;
                    }
                }
            }
        }
        return backtrackPath(currentNode);//change for final
    }
    public static List<Node> aStarSearch(Node start,Node end){
        return null;//change for final
    }


}
