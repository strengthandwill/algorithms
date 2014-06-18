package com.algomized.datastructures.trees;

import com.algomized.datastructures.stacks.Stack;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * You are given a binary tree in which each node contains a value. Design an algorithm
 * to print all paths which sum to a given value. The path does not need to
 * start or end at the root or a leaf.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q9 {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);
		
		printPaths(root, 12);
	}
	
	/**
	 * Time:  Average = Worst = n [traverse all nodes] * O(log(n)) [print path] = O(nlog(n))<br>
	 * Space: Worst = O(h) [recursive calls] + O(h) [stack size] = O(h), h = height of tree 
	 */
	public static void printPaths(TreeNode<Integer> root, int sum) {
		if (root == null) {
			return;
		}
		printPaths(root, new Stack<Integer>(), sum);
	}
	
	private static void printPaths(TreeNode<Integer> node, Stack<Integer> path, int sum) {
		if (node == null) {
			return;
		}
		path.push(node.item);
		printPath(path, sum);
		printPaths(node.left, path, sum);
		printPaths(node.right, path, sum);
		path.pop();		
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(n)
	 * Note:  Length of path = height of tree
	 */
	private static void printPath(Stack<Integer> path, int sum) {
		int pathSum = 0;
		StringBuffer strBuf = new StringBuffer();
		for (Integer i : path) {
			strBuf.append("[" + i + "]");
			pathSum += i;
			if (pathSum == sum) { // sum found
				System.out.println(strBuf.toString());
			}
		}
	}
}
