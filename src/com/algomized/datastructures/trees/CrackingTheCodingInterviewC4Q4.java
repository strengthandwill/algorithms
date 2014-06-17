package com.algomized.datastructures.trees;

import com.algomized.datastructures.arrays.ArrayList;
import com.algomized.datastructures.linkedlists.LinkedList;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Given a binary tree, design an algorithm which creates a linked list of all the
 * nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked
 * lists).
 * </p>
 *
 */
public class CrackingTheCodingInterviewC4Q4 {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);
		
		ArrayList<LinkedList<TreeNode<Integer>>> lists = createDepthLists(root);
		for (LinkedList<TreeNode<Integer>> list : lists) {
			System.out.println(list);
		}		
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static ArrayList<LinkedList<TreeNode<Integer>>> createDepthLists(TreeNode<Integer> root) {
		ArrayList<LinkedList<TreeNode<Integer>>> lists = new ArrayList<LinkedList<TreeNode<Integer>>>();
		createDepthLists(root, lists, 0);
		return lists;
	}
	
	private static void createDepthLists(TreeNode<Integer> node, ArrayList<LinkedList<TreeNode<Integer>>> lists, int depth) {
		if (node == null) {
			return;
		}
		LinkedList<TreeNode<Integer>> list = lists.get(depth);
		if (list == null) {
			list = new LinkedList<TreeNode<Integer>>();
			lists.add(list);
		}
		list.add(node);
		createDepthLists(node.left, lists, depth + 1);
		createDepthLists(node.right, lists, depth + 1);
	}
}