## Graph

1. Undirected Graph: 		Graph
2. Symbol Graph:			Symbol Graph
3. Directed Graph: 			Digraph
4. Symbol Directed Graph:	Symbol Digraph
5. Weighted Directed Graph: Weighted Digraph


## APIs

### Graph/Digraph 
- public int vertices()
- public int edges()
- public void addEdge(int v, int w)
- public void deleteEdge(int v, int w)
- public Iterable<Integer> adj(int v)

### Symbol Graph/ Symbol Digraph
- public int vertices()
- public int edges()
- public void addEdge(Item v, Item w)
- public void deleteEdge(Item v, Item w)
- public Iterable<Item> adj(Item v)
- public boolean contains(Item key)
- public int index(Item key)
- public Item name(int index)


## Time Complexity (Adjacent Lists)
- Add Edge:    Worst = O(1)
- Remove Edge: Worst = O(|E|)
- Storage:     Worst = O(|V| + |E|)


## Search

### Depth First Search Complexity
- Time:  Worst = (|V| + |E|)
- Space: Worst = (|V|) (Due to recursive calls)

### Breath First Search Complexity
- Time: Worst = (|V| + |E|)
- Space: Worst - (|V|) (Due to the use of queue)


## Problems and Solutions

### Graph
- Single-source connectivity: 	DepthFirstSearch
- Single-source paths: 		  	DepthFirstPaths
- Single-source shortest paths: BreadthFirstPaths
- Connectivity: 				ConnectedComponents
- Cycle detection:				Cycle
- Two-colorability: 			Bipartite

### Digraph
- Single- and multiple-source reachability: DirectedDFS
- Single-source directed paths: 			DepthFirstDirectedPaths
- Single-source shortest directed paths: 	BreadthFirstDirectedPaths
- Directed cycle detection: 				DirectedCycle
- Depth-first vertex orders: 				DepthFirstOrder
- Precedence-constrained scheduling: 		Topological
- Topological sort: 						Topological
- Strong connectivity: 						KosarajuSCC
- All-pairs reachability: 					TransitiveClosure