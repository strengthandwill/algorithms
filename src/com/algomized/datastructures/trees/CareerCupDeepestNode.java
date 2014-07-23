package com.algomized.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Find the deepest node in a binary tree
 * </p>
 *
 */
public class CareerCupDeepestNode {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);
		root = TreeNode.insert(root, 7);
		root = TreeNode.insert(root, 8);
		root = TreeNode.insert(root, 9);
		
		System.out.println(deepestNodeDFS(root));
		System.out.println(deepestNodeBFS(root));
	}
	
	public static TreeNode<Integer> deepestNodeDFS(TreeNode<Integer> root) {
		if (root == null) return null;
		return deepestNodeDFS(root, 0).root;    
	}

	private static Result deepestNodeDFS(TreeNode<Integer> root, int depth) {	
		if (root.left == null && root.right == null) {
			return new Result(root, depth);
		}

		Result left = null;
		Result right = null;
		if (root.left != null) left = deepestNodeDFS(root.left, depth + 1);
		if (root.right != null) right = deepestNodeDFS(root.right, depth + 1);
		
		if (left == null) return right;		
		return left.depth > right.depth ? left : right;
	}

	static class Result {
		TreeNode<Integer> root;
		int depth;

		public Result(TreeNode<Integer> root, int depth) {
			this.root = root;
			this.depth = depth;
		}
	}

	public static TreeNode<Integer> deepestNodeBFS(TreeNode<Integer> root) {
		Queue<TreeNode<Integer>> q = new LinkedList<TreeNode<Integer>>();
		TreeNode<Integer> deepest = null;
		q.add(root);

		while(q.size() > 0) {
			deepest = q.remove();
			if(deepest.left != null) q.add(deepest.left);			
			if(deepest.right != null) q.add(deepest.right);			
		}
		return deepest;
	}
}