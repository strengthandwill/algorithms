package com.algomized.datastructures.trees;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a sorted (increasing order) array with unique integer elements, write an
 * algorithm to create a binary search tree with minimal height.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC4Q3 {

	public static void main(String[] args) {
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
		TreeNode<Integer> root = createBST(arr);
		System.out.println(toString(root));
	}
	
	/**
	 * Time:  Average = Worst = O(n)<br>
	 * Space: Worst = O(log(n))
	 */
	public static TreeNode<Integer> createBST(int[] arr) {
		if (arr == null) {
			return null;
		}
		return createMinimalBST(arr, 0, arr.length - 1);
	}
	
	private static TreeNode<Integer> createMinimalBST(int[] arr, int lo, int hi) {
		if (lo > hi) {
			return null;
		}
		int mid = (lo + hi) / 2;
		TreeNode<Integer> node = new TreeNode<Integer>(arr[mid]);
		node.left = createMinimalBST(arr, lo, mid - 1);
		node.right = createMinimalBST(arr, mid + 1, hi);
		return node;
	}
	
	public static String toString(TreeNode<Integer> root) {
		StringBuffer strBuf = new StringBuffer();
		toString(root, strBuf);
		return strBuf.toString();
	}
	
	private static void toString(TreeNode<Integer> node, StringBuffer strBuf) {
		if (node == null) {
			return;
		}
		toString(node.left, strBuf);
		strBuf.append("[" + node.item + "]");
		toString(node.right, strBuf);
	}
}
