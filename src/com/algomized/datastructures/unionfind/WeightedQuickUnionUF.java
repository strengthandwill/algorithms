package com.algomized.datastructures.unionfind;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * <b>Weighted Quick Union</b>
 * Uses size of the substree as weight to add smaller subtree to
 * the larger subtree to ensure that the resultant tree is balanced.
 * This allows low time complexity for both union and find as 
 * the trees are balanced<br>
 * <br>
 * <b>Weighted Quick Union With Path Compression</b>
 * Path compression set the id to the parent id along the way to
 * find the root id, which flatten the resultant tree without incurring
 * too much additional operations. This allows even lower complexity for
 * both union and find as the trees are balanced and flattened.<br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 *
 */
public class WeightedQuickUnionUF implements UnionFindAPI {
	private int[] id;
	private int[] size;
	private int count = 0;
	
	public static void main(String[] args) {						
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);		
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
	
	public WeightedQuickUnionUF(int n) {
		id = new int[n];
		size = new int[n];
		count = n;
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}
	
	/**
	 * <b>Weighted Union Find</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * <b>Weighted Union Find with Path Compression</b><br>
	 * Time:  Average = Worst = Near O(1)<br>
	 * Space: Worst = O(1)
	 */
	public void union(int v, int w) {
		if (v < 0 || v >= id.length || w < 0 || w >= id.length) {
			return;
		}
		int vRoot = find(v);
		int wRoot = find(w);
		if (vRoot == wRoot) { // v and w are in same components
			return;
		}
		if (size[vRoot] < size[wRoot]) { // subtree of w larger than subtree of v
			id[vRoot] = wRoot;
			size[wRoot] += size[vRoot];				
		} else { // subtree of v larger than subtree of w
			id[wRoot] = vRoot;
			size[vRoot] += size[wRoot];			
		}
		count--;		
	}
	
	/**
	 * <b>Weighted Union Find</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * <b>Weighted Union Find with Path Compression</b><br>
	 * Time:  Average = Worst = Near O(1)<br>
	 * Space: Worst = O(1)
	 */
	public int find(int v) {
		if (v < 0 || v >= id.length) {
			return -1;
		}
		while (v != id[v]) { // depth of vertices
			//id[v] = id[id[v]]; // path compression: set id to parent id
			v = id[v];			
		}
		return v;
	}
	
	/**
	 * <b>Weighted Union Find</b><br>
	 * Time:  Average = Worst = O(log(n))<br>
	 * <b>Weighted Union Find with Path Compression</b><br>
	 * Time:  Average = Worst = Near O(1)<br>
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