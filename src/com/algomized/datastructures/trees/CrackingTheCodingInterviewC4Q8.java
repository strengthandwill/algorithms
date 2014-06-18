package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * You have two very large binary trees: Tl, with millions of nodes, and T2, with
 * hundreds of nodes. Create an algorithm to decide ifT2 is a subtree of Tl.<br>
 * A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of
 * n is identical to T2. That is, if you cut off the tree at node n, the two trees would
 * be identical.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q8 {

	public static void main(String[] args) {
		TreeNode<Integer> root = null;
		root = TreeNode.insert(root, 3);
		root = TreeNode.insert(root, 1);
		root = TreeNode.insert(root, 0);
		root = TreeNode.insert(root, 2);
		root = TreeNode.insert(root, 5);
		root = TreeNode.insert(root, 4);
		root = TreeNode.insert(root, 6);
		
		TreeNode<Integer> root2 = null;
		root2 = TreeNode.insert(root2, 2);
		root2 = TreeNode.insert(root2, 1);
		root2 = TreeNode.insert(root2, 3); 
		
		System.out.println(isSubtree(root, root.left));
		System.out.println(isSubtree(root, root2));
	}
	
	/**
	 * Time:  Average = O(n + km), Worst = O(nm), n = size of larger tree a, 
	 * m = size of smaller tree b, k is the occurrences of b in a<br>
	 * Space: Worst = O(h + i), h = height of a, i = height of b 
	 */	
	public static boolean isSubtree(TreeNode<Integer> a, TreeNode<Integer> b) {
		if (b == null) {
			return false;
		}
		return isSubtreeHelper(a, b);
	}
	
	private static boolean isSubtreeHelper(TreeNode<Integer> a, TreeNode<Integer> b) {
		if (a == null) {
			return false;
		}
		if (a.item.equals(b.item)) {
			return treeMatch(a, b);
		}
		return isSubtree(a.left, b) || isSubtree(a.right, b);
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(h), h = height of tree 
	 */
	private static boolean treeMatch(TreeNode<Integer> a, TreeNode<Integer> b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		
		if (!a.item.equals(b.item)) { // a and b do not match
			return false;
		}		
		return treeMatch(a.left, b.left) && treeMatch(a.right, b.right);
	}
}