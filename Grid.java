import java.util.*;

public class Grid
{
    private class Hueristic implements IAStarHueristic
    {
        public int GetHeuristic(IAStarNode start, IAStarNode end)
        {
            Room s = start as Room;
            Room e = end as Room;

            return Math.abs(s.X - e.X) + Math.abs(s.Y - e.Y);
        }
    }

    private Random random;
    public int Width;
    public int Height;

    private Room[,] _Rooms;

    public Grid(int width, int height, int seed)
    {
        Width = width;
        Height = height;
        random = new Random(seed);

        InitializeRooms();
    }

    public Room GetRoomAt(int x, int y)
    {
        return _Rooms[x, y];
    }

    public List<IAStarNode> GetMap()
    {
        IAStarNode startNode;
        IAStarNode endNode;
        SetStartEndNodes(startNode, endNode);

        List<IAStarNode> map = AStar.GetPath(startNode, endNode, new Hueristic());
        Branching(map);

        return map;
    }

    private void InitializeRooms()
    {
        List<Room> rooms = new LinkedList<Room>();

        //Setup rooms
        for (int y = 0; y < Height; y++)
        {
            for (int x = 0; x < Width; x++)
            {
                rooms.add(new Room(x, y, random.nextInt()));
            }
        }

        //Initialize neigbors
        for (Room room : rooms)
        {
            int x = room.X;
            int y = room.Y;

            if (IsWithinBounds(x - 1, y))
                room.AddNeighbor(_Rooms[x - 1, y]);

            if (IsWithinBounds(x + 1, y))
                room.AddNeighbor(_Rooms[x + 1, y]);

            if (IsWithinBounds(x, y - 1))
                room.AddNeighbor(_Rooms[x, y - 1]);

            if (IsWithinBounds(x, y + 1))
                room.AddNeighbor(_Rooms[x, y + 1]);
        }
    }

    private List SetStartEndNodes(IAStarNode startNode, IAStarNode endNode)
    {
        int randomFactor = random.nextInt(100);

        if (randomFactor < 50)
        {
            startNode = _Rooms[0, 0];
            endNode = _Rooms[Width - 1, Height - 1];
        }
        else
        {
            startNode = _Rooms[0, Height - 1];
            endNode = _Rooms[Width - 1, 0];
        }
    }

    private void Branching(List<IAStarNode> path)
    {
        for (int i = 0; i < path.size(); i++)
        {
            for(IAStarNode neigbor : path.get(i).getNeigbors())
            {
                if (path.contains(neigbor))
                    continue;

                //TODO: Implement branching with better accuracy
                int randomFactor = random.nextInt(Width + Height);

                if(randomFactor < (Width + Height)/3)
                {
                    path.add(neigbor);
                    break;
                }
            }
        }
    }

    private boolean IsWithinBounds(int x, int y)
    {
        if (x < 0 || x >= Width)
            return false;

        if (y < 0 || y >= Height)
            return false;

        return true;
    }

}