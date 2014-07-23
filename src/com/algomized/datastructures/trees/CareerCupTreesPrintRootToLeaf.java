package com.algomized.datastructures.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Print the paths of the root to leaf of a binary tree WITHOUT using recursion.
 * </p>
 *
 */
public class CareerCupTreesPrintRootToLeaf {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);

		printRootToLeaf(root);
		System.out.println();
		printRootToLeafDFSRecursive(root);
		System.out.println();
		printRootToLeafDFSIterative(root);
	}

	public static void printRootToLeafDFSIterative(TreeNode<Integer> root) {
		if (root == null) return;
		ArrayList<TreeNode<Integer>> visited = new ArrayList<TreeNode<Integer>>();
		Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
		Stack<Integer> path = new Stack<Integer>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode<Integer> node = stack.peek();
			if (!visited.contains(node)) {
				visited.add(node);
				path.add(node.item);
				if (node.left == null && node.right == null) {
					System.out.println(path);
				} else {
					if (node.left != null) stack.push(node.left);					
					if (node.right != null) stack.push(node.right);					
				}
			} else {
				path.pop();
				stack.pop();
			}
		}
	}

	public static void printRootToLeafDFSRecursive(TreeNode<Integer> root) {
		if (root == null) return;
		printRootToLeafDFSRecursive(root, new Stack<Integer>());
	}

	private static void printRootToLeafDFSRecursive(TreeNode<Integer> node, Stack<Integer> path) {
		if (node.left == null && node.right == null) {
			path.push(node.item);
			System.out.println(path);
			path.pop();
			return;
		}
		path.push(node.item);
		printRootToLeafDFSRecursive(node.left, path);
		printRootToLeafDFSRecursive(node.right, path);
		path.pop();
	}

	public static void printRootToLeaf(TreeNode<Integer> root) {
		if (root == null) return;
		Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
		HashMap<TreeNode<Integer>, TreeNode<Integer>> edgeTo = 
				new HashMap<TreeNode<Integer>, TreeNode<Integer>>();
		stack.add(root);
		while (!stack.isEmpty()) {
			TreeNode<Integer> node = stack.pop();						
			if (node.left != null) {
				edgeTo.put(node.left, node);
				stack.push(node.left);
			}
			if (node.right != null) {
				edgeTo.put(node.right, node);
				stack.push(node.right);
			}
			if (node.left == null && node.right == null) { // leaf node
				printPath(root, node, edgeTo);
			}
		}
	}

	private static void printPath(TreeNode<Integer> source, TreeNode<Integer> dest, 
			HashMap<TreeNode<Integer>, TreeNode<Integer>> edgeTo) {		
		Deque<Integer> stack = new ArrayDeque<Integer>();
		while (!dest.equals(source)) {
			stack.push(dest.item);
			dest = edgeTo.get(dest);			
		}
		stack.push(dest.item);
		System.out.println(stack);		
	}
}
