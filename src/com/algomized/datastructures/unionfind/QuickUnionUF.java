package com.algomized.datastructures.unionfind;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Low time complexity for both union and find on balanced tree, but 
 * high time complexity for both union and find on unbalanced tree.<br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 *
 */
public class QuickUnionUF implements UnionFindAPI {
	private int[] id;
	private int count = 0;
	
	public static void main(String[] args) {
		QuickUnionUF uf = new QuickUnionUF(10);		
		uf.union(4, 3);
		uf.union(3, 8);
		uf.union(6, 5);
		uf.union(9, 4);
		uf.union(2, 1);
		uf.union(8, 9); // same component
		uf.union(5, 0);
		uf.union(7, 2);
		uf.union(6, 1);
		uf.union(1, 0); // same component
		uf.union(6, 7); // same component
		System.out.println(uf.count());
		System.out.println(uf.connected(4, 3));
	}
	
	public QuickUnionUF(int n) {
		id = new int[n];
		count = n;
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(1)
	 */
	public void union(int v, int w) {
		if (v < 0 || v >= id.length || w < 0 || w >= id.length) {
			return;
		}
		int vRoot = find(v);
		int wRoot = find(w);
		if (vRoot != wRoot) {
			id[vRoot] = wRoot;
			count--;
		}
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n) [Unbalanced tree]<br>
	 * Space: Worst = O(1)
	 */
	public int find(int v) {
		if (v < 0 || v >= id.length) {
			return -1;
		}
		while (v != id[v]) { // depth of vertices
			v = id[v];
		}
		return v;
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(1)
	 */
	public boolean connected(int v, int w) {
		if (v < 0 || v >= id.length || w < 0 || w >= id.length) {
			return false;
		}
		return find(v) == find(w);	
	}
	
	public int count() {
		return count;
	}
}
