package com.algomized.datastructures.unionfind;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Low time complexity for find but high time complexity for union.<br>
 * <br>
 * Space: Worst = O(n)
 * </p>
 *
 */
public class QuickFindUF implements UnionFindAPI {
	private int[] id;
	private int count;

	public static void main(String[] args) {
		QuickFindUF uf = new QuickFindUF(10);		
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
	
	public QuickFindUF(int n) {
		id = new int[n];
		count = n;
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	/**
	 * Time:  Worst = O(1)<br>
	 * Space: Worst = O(1) 
	 */
	public int find(int v) {
		if (v < 0 || v >= id.length) {
			return -1;
		}		
		return id[v];
	}
	
	/**
	 * Time:  Worst = O(1)<br>
	 * Space: Worst = O(1) 
	 */
	public boolean connected(int v, int w) {
		if (v < 0 || v >= id.length || w < 0 || w >=id.length) {
			return false;
		}		
		return id[v] == id[w];
	}
	
	/**
	 * Time:  Worst = O(n)<br>
	 * Space: Worst = O(1) 
	 */
	public void union(int v, int w) {
		if (v < 0 || v >= id.length || w < 0 || w >=id.length || connected(v, w)) {
			return;
		}		
		int vId = id[v];
		int wId = id[w];
		for (int i = 0; i < id.length; i++) {			
			if (id[i] == vId) {
				id[i] = wId;
			}
		}
		count--;
	}
	
	public int count() {
		return count;
	}	
}
