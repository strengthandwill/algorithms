package com.algomized.datastructures.unionfind;

public interface UnionFindAPI {
	public void union(int v, int w);
	public int find(int v);
	public boolean connected(int v, int w);
	public int count();
}
