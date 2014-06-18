## Binary Search Tree + Red Black Tree

### APIs
- public void put(Key key, Value value);
- public Value get(Key key);
- public void delete(Key key);
- public boolean contains(Key key);
- public int size();
- public boolean isEmpty();
- public Iterable<Key> keys();

### Binary Search Tree

#### Time Complexity
- Insert: Average = O(log(n)), Worst = O(n)
- Search: Average = O(log(n)), Worst = O(n)
- Delete: Average = O(log(n)), Worst = O(n)

#### Space Complexity
- Worst = O(n)

### Red Black Tree

#### Time Complexity
- Insert: Average = Worst = O(log(n))
- Search: Average = Worst = O(log(n))
- Delete: Average = Worst = O(log(n))

#### Space Complexity
- Worst = O(n)