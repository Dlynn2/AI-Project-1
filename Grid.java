using System;
        using System.Collections;
        using System.Collections.Generic;
public class Grid
{
    private class Hueristic : IAStarHueristic
    {
        public int GetHeuristic(IAStarNode start, IAStarNode end)
        {
            Room s = start as Room;
            Room e = end as Room;

            return Math.Abs(s.X - e.X) + Math.Abs(s.Y - e.Y);
        }
    }

    private System.Random _Random;
    public readonly int Width;
    public readonly int Height;

    private Room[,] _Rooms;

    public Grid(int width, int height, int seed)
    {
        Width = width;
        Height = height;
        _Random = new System.Random(seed);

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
        SetStartEndNodes(out startNode, out endNode);

        List<IAStarNode> map = AStar.GetPath(startNode, endNode, new Hueristic());
        Branching(map);

        return map;
    }

    private void InitializeRooms()
    {
        _Rooms = new Room[Width, Height];

        //Setup rooms
        for (int y = 0; y < Height; y++)
        {
            for (int x = 0; x < Width; x++)
            {
                _Rooms[x, y] = new Room(x, y, _Random.Next());
            }
        }

        //Initialize neigbors
        foreach (Room room in _Rooms)
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

    private void SetStartEndNodes(out IAStarNode startNode, out IAStarNode endNode)
    {
        int randomFactor = _Random.Next(0, 100);

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
        for (int i = 0; i < path.Count; i++)
        {
            foreach (IAStarNode neigbor in path[i].Neigbors)
            {
                if (path.Contains(neigbor))
                    continue;

                //TODO: Implement branching with better accuracy
                int randomFactor = _Random.Next(0, Width + Height);

                if(randomFactor < (Width + Height)/3)
                {
                    path.Add(neigbor);
                    break;
                }
            }
        }
    }

    private bool IsWithinBounds(int x, int y)
    {
        if (x < 0 || x >= Width)
            return false;

        if (y < 0 || y >= Height)
            return false;

        return true;
    }

}
