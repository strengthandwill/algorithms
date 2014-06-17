package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Write an algorithm to find the'next'node (i.e., in-order successor) of a given node
 * in a binary search tree. You may assume that each node has a link to its parent.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q6 {
	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = insert(root, 3);
		root = insert(root, 1);
		root = insert(root, 0);
		root = insert(root, 2);
		root = insert(root, 5);
		root = insert(root, 4);
		root = insert(root, 6);
		
		System.out.println(next(root.left.left));
		System.out.println(nextUsingLoop(root.left.left));
	}
	
	/**
	 * Time:  Average = O(log(n)) [min()] + O(1) [parentNext()] = O(log(n))<br>
	 * Worst = O(n) [min()] + O(1) [parentNext()] = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static TreeNode<Integer> next(TreeNode<Integer> node) {
		if (node == null) {
			return null;
		}
		if (node.right != null) { // find next from right subtree
			return min(node.right);
		}
		return nextParent(node.parent, node);	// find next from parent	
	}

	/**
	 * Time:  Average = O(log(n)) [min()] + O(1) [loop] = O(log(n))<br>
	 * Worst = O(n) [min()] + O(1) [loop] = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static TreeNode<Integer> nextUsingLoop(TreeNode<Integer> node) {
		if (node == null) {
			return null;
		}
		if (node.right != null) { // find next from right subtree
			return min(node.right);
		}
		TreeNode<Integer> current = node; // find next from parent
		TreeNode<Integer> parent = node.parent;
		while (parent != null && parent.left != current) {
			current = parent;
			parent = parent.parent;
		}
		return parent;
	}	
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree  
	 */
	private static TreeNode<Integer> min(TreeNode<Integer> node) {
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}
	
	/**
	 * Time:  Average = Worst = 1 or 2 = O(1)<br>
	 * Space: Worst = O(h), h = height of tree 
	 */
	private static TreeNode<Integer> nextParent(TreeNode<Integer> node, TreeNode<Integer> child) {
		if (node == null) {
			return null;			
		}
		if (node.left == child) {
			return node;
		}
		return nextParent(node.parent, node);
	}
	
	public static <Item extends Comparable<Item>> TreeNode<Item> insert(TreeNode<Item> root, Item item) {
		return insert(root, item, null);
	}
	
	private static <Item extends Comparable<Item>> TreeNode<Item> insert(TreeNode<Item> node, Item item, TreeNode<Item> parent) {
		if (node == null) {
			return new TreeNode<Item>(item, parent);
		}
		int cmp = item.compareTo(node.item);
		if (cmp < 0) {
			node.left = insert(node.left, item, node);
		} else if (cmp > 0) {
			node.right = insert(node.right, item, node);
		} else {
			node.item = item;
		}
		return node;
	}
	
	static class TreeNode<Item extends Comparable<Item>> {
		Item item;
		TreeNode<Item> left;
		TreeNode<Item> right;
		TreeNode<Item> parent;
		
		public TreeNode(Item item, TreeNode<Item> parent) {
			this.item = item;
			this.parent = parent;
		}
		
		public String toString() {
			return "[" + item + "]";
		}
	}

}
