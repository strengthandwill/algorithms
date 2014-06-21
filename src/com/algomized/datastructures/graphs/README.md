## Graph

## Undirected Graph 

### APIs for Undirected Graph
- public int vertices()
- public int edges()
- public void addEdge(int v, int w)
- public void deleteEdge(int v, int w)
- public Iterable<Integer> adj(int v)

### APIs for Undirected Symbol Graph
- public int vertices()
- public int edges()
- public void addEdge(Item v, Item w)
- public void deleteEdge(Item v, Item w)
- public Iterable<Item> adj(Item v)
- public boolean contains(Item key)
- public int index(Item key)
- public Item name(int index)

### Time Complexity (Adjacent Lists)
- Add Edge:    Worst = O(1)
- Remove Edge: Worst = O(|E|)
- Storage:     Worst = O(|V| + |E|)


## Search

### Depth First Search

## Complexity
- Time:  Worst = (|V| + |E|)
- Space: Worst = (|V|) (Due to recursive calls)

### Breath First Search

## Complexity
- Time: Worst = (|V| + |E|)
- Space: Worst - (|V|) (Due to the use of queue)