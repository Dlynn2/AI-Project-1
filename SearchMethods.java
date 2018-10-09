import java.util.*;

/**
 * Search methods, configured by knowing the start and end node of the maze, and in the Astar instance, by knowing the
 * Heuristic
 */
public class SearchMethods
{
    public static int expandedCount = 0;
    public static int getExpandedCount(){
        int temp = expandedCount;
        expandedCount = 0;
        return temp;
    }
    private SearchMethods(){}
    public static List<Node> BFS(Node start, Node end)
    {
        HashSet<Node> closedSet = new HashSet<>();
        Queue<Node> openSet = new LinkedList<>();
        openSet.add(start);
        while (!openSet.isEmpty())
        {
            Node currentNode = openSet.poll();
            if (closedSet.contains(currentNode))
                continue;
            if(currentNode == end)
                return backtrackPath(currentNode);

            closedSet.add(currentNode);
            expandedCount++;
            for(Node neighbor : currentNode.getNeighbors())
            {
                if(!closedSet.contains(neighbor) && currentNode.getG() == neighbor.getG())
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
    public static List<Node> AStar(Node start, Node end, Heuristic hueristic)
    {
        HashSet<Node> closedSet = new HashSet<>();
        List<Node> openSet = new ArrayList<>();
        openSet.add(start);
        while (openSet.size() != 0)
        {
            Node currentNode = openSet.get(0);
            for (Node node : openSet)
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
            expandedCount++;
            for (Node neighbor : currentNode.getNeighbors()) {
                if (closedSet.contains(neighbor))
                    continue;
                //Checking for walls
                if (currentNode.getG() != neighbor.getG()) {
                    closedSet.add(neighbor);
                    continue;
                }
                int newGValue = currentNode.getG() + neighbor.getG();

                if (openSet.contains(neighbor) == false){
                    openSet.add(neighbor);
                    expandedCount++;
                }else if (newGValue >= neighbor.getG())
                    continue;
                neighbor.setParent(currentNode);
            }
        }
        //if start is null return empty list
        return Collections.emptyList();
    }
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
                expandedCount++; //counting how many times we expand
                //if the neighbor has not been visited and is not a wall
                if(!closedSet.contains(neighbor) && currentNode.getG() == neighbor.getG())
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
            while(preferredNode != null){//While we still have preferred nodes
                expandedCount++;//counting how many times we expand
                if(preferredNode.getG() == (int)' ' && !preferredNode.isVisited()){//If preferred node is available
                    preferredNode.setVisited(true);//visit it
                    preferredNode.setParent(currentNode);//set its parent to current node
                    currentNode = preferredNode;//mode current node to preferred node
                    break;
                }else {
                    preferredNode = preferred.poll();//get the next most greedy move
                    if(preferred.size() == 0){//if the list is empty
                        currentNode = currentNode.getParent();
                        break;
                    }
                }
            }
        }
        return backtrackPath(currentNode);
    }
}
