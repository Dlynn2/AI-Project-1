import sun.awt.image.ImageWatched;

import java.util.*;

public class SearchMethods
{
    private SearchMethods(){}

    public static List<Node> BFS(Node start, Node end)
    {
        HashSet<Node> closedSet = new HashSet<>();
        Queue<Node> openSet = new LinkedList<>();
        openSet.add(start);

        while (openSet.isEmpty() == false)
        {
            Node currentNode = openSet.poll();

            if (closedSet.contains(currentNode))
                continue;

            if(currentNode == end)
                return backtrackPath(currentNode);

            closedSet.add(currentNode);
            for(Node neighbor : currentNode.getNeighbors())
            {
                System.out.println("in");
                if(closedSet.contains(neighbor) == false && currentNode.getValue() == neighbor.getValue())
                {
                    openSet.add(neighbor);
                    neighbor.setParent(currentNode);
                }
            }
        }


        return Collections.emptyList();
    }

    private static List<Node> backtrackPath(Node start) {

        List<Node> path = new ArrayList<>();
        Node current = start;

        while (current != null)
        {
            path.add(current);
            current = current.getParent();
        }

        return path;
    }

    //depth first search
    public static List<Node> DFS(Node start, Node end)
    {
        //stack of next nodes to follow
        Stack<Node> next = new Stack<>();
        //hash for fast searching of past nodes
        HashSet<Node> closedSet = new HashSet<>();
        //current node
        Node currentNode;

        next.push(start);

        //while there are more to expand
        while (!next.isEmpty())
        {
            //get the next deepest node
            currentNode = next.pop();

            //set node to visited
            closedSet.add(currentNode);

            if(currentNode == end)
                return backtrackPath(currentNode);

            //for each neighbor
            for(Node neighbor : currentNode.getNeighbors())
            {

                //if the neighbor has not been visited and is not a wall
                if(!closedSet.contains(neighbor) && currentNode.getValue() == neighbor.getValue())
                {
                    //push to be visited next
                    next.push(neighbor);
                    //set current as parent
                    neighbor.setParent(currentNode);
                }
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
    public static List<Node> greedySearch(Node start,Node end){
        class MakeQueue{ //Only needed for this search so specific function needed
            /**
             * Builds priority Queue based on the current direction of x and y;
             */
            public Queue<Node> getMostPreferred(int xDirection,int yDirection,Node current){
                Queue<Node> preferredBuilder = new LinkedList<>();
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
        Queue<Node> preferred; //list of preferred nodes
        MakeQueue makePriorityQueue = new MakeQueue();//priority Queue class for making priority queues
        Node currentNode = start;
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
            preferred = makePriorityQueue.getMostPreferred(offsetX,offsetY,currentNode);
            Node preferredNode = preferred.poll();
            while(preferredNode != null){
                if(preferredNode.getValue() == (int)' '){
                    preferredNode.setValue('X');
                    preferredNode.setParent(currentNode);
                    currentNode = preferredNode;
                    break;
                }else {
                    preferredNode = preferred.poll();
                    if(preferred.size() == 0){
                        currentNode = currentNode.getParent();
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
