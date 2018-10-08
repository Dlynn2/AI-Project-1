using System.Collections;
        using System.Collections.Generic;

public class Room : IAStarNode
        {
private int _G;
public int G { get { return _G; } set { _G = value; } }

private int _H;
public int H { get { return _H; } set { _H = value; } }

public int F { get { return H + G; } }

private IAStarNode _Parent;
public IAStarNode Parent { get { return _Parent; } set { _Parent = value; } }

private List<IAStarNode> _Neigbors;
public IList<IAStarNode> Neigbors { get { return _Neigbors.AsReadOnly(); } }

public readonly int X;
public readonly int Y;


public Room(int x, int y) :this(x, y, 0) {}
public Room(int x, int y, int g)
        {
        X = x;
        Y = y;
        G = g;
        _Neigbors = new List<IAStarNode>();
        }

public void AddNeighbor(IAStarNode neigbor)
        {
        _Neigbors.Add(neigbor);
        }
        }
