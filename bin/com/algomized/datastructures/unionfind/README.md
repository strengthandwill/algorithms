## Union Find

### APIs
- public void union(int v, int w)
- public int find(int v)
- public boolean connected(int v, int w)
- public int count()


### Time Complexity

#### Quick Find
- Union: Average = Worst = O(n)
- Find:  Average = Worst = O(1)

#### Quick Union
- Union: Average = O(log(n)), Worst = O(n)
- Find:  Average = O(log(n)), Worst = O(n)

#### Weighted Quick Find
- Union: Average = Worst = O(log(n))
- Find:  Average = Worst = O(log(n))

#### Weighted Quick Find with Path Compression
- Union: Average = Worst = ~ O(1)
- Find:  Average = Worst = ~ O(1)


### Space Complexity
- Worst = O(n)