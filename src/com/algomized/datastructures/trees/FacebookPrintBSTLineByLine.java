package com.algomized.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class FacebookPrintBSTLineByLine {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);
		
		printBST(root);
	}
	
	public static void printBST(TreeNode<Integer> root) {
		if (root == null) return;
		
		Queue<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();
		queue.add(root);
		queue.add(null);
		while (!queue.isEmpty()) {
			TreeNode<Integer> node = queue.remove();			
			if (node == null) {
				System.out.println();
				if (!queue.isEmpty()) queue.add(null);
			}
			else {
				System.out.print("[" + node.item + "]");
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}
		}
	}
}
