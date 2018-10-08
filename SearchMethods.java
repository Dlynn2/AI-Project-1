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
    public static List<Node> greedySearch(Node start,Node end){
        HashSet<Node> vangaurd = new HashSet<Node>();
        vangaurd.add(start);
        while()

    }
    public static List<Node> aStarSearch(Node start,Node end){

    }
}
