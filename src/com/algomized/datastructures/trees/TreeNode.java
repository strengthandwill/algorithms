package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * TreeNode with two links, left and right.
 * </p>
 */
public class TreeNode <Item extends Comparable<Item>> {
	Item item;
	TreeNode<Item> left;
	TreeNode<Item> right;
	
	public TreeNode(Item item) {
		this.item = item;
	}
	
	public String toString() {
		return "[" + item + "]";
	}
	
	/**
	 * Time:  Average = O(log(n)), Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree
	 */
	public static <Item extends Comparable<Item>> TreeNode<Item> insert(TreeNode<Item> node, Item item) {
		 if (node == null) {
			 return new TreeNode<Item>(item);
		 }
		 int cmp = item.compareTo(node.item);
		 if (cmp < 0) {
			 node.left = insert(node.left, item);
		 } else if (cmp > 0) {
			 node.right = insert(node.right, item);
		 } else {
			 node.item = item;
		 }
		 return node;
	}	
}
