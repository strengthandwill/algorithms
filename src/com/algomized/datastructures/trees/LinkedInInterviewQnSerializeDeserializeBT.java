package com.algomized.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Serialize binary tree into pre-order, in-order and post-order.<br>
 * Deserialize pre-order and in-order to binary tree.<br>
 * Deserialize post-order and in-order to binary tree.
 * </p>
 *
 */
public class LinkedInInterviewQnSerializeDeserializeBT {	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);	
		
		List<Integer> inOrder = new ArrayList<Integer>();
		List<Integer> preOrder = new ArrayList<Integer>();
		List<Integer> postOrder = new ArrayList<Integer>();
		
		// Serialize binary tree
		inPreSerialize(root, preOrder);
		inOrderSerialize(root, inOrder);		
		postOrderSerialize(root, postOrder);
		
		System.out.println("Pre-order:  " + preOrder);
		System.out.println("In-order:   " + inOrder);
		System.out.println("Post-order: " + postOrder);
		
		// Deserialize binary tree
		List<Integer> inOrder2 = new ArrayList<Integer>();
		TreeNode root2 = deserializePreInOrder(toArray(preOrder), toArray(inOrder));
		inOrderSerialize(root2, inOrder2);
		System.out.println("In-order:   " + inOrder2);
		
		List<Integer> inOrder3 = new ArrayList<Integer>();
		TreeNode root3 = deserializePreInOrder(toArray(preOrder), toArray(inOrder));
		inOrderSerialize(root3, inOrder3);
		System.out.println("In-order:   " + inOrder3);
		
	}
	
	public static int[] toArray(List<Integer> list) {
		int[] array = new int[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	public static void inPreSerialize(TreeNode node, List<Integer> list) {
		if (node == null) return;
		list.add(node.value);
		inPreSerialize(node.left, list);
		inPreSerialize(node.right, list);
	}
	
	public static void inOrderSerialize(TreeNode node, List<Integer> list) {
		if (node == null) return;
		inOrderSerialize(node.left, list);
		list.add(node.value);
		inOrderSerialize(node.right, list);
	}	

	public static void postOrderSerialize(TreeNode node, List<Integer> list) {
		if (node == null) return;
		postOrderSerialize(node.left, list);
		postOrderSerialize(node.right, list);
		list.add(node.value);
	}
	
	public static TreeNode deserializePreInOrder(int[] preOrderList, int[] inOrderList) {
		return deserializePreInOrder(preOrderList, inOrderList, 
				0, preOrderList.length - 1, 0, inOrderList.length - 1);
	}
	
	public static TreeNode deserializePreInOrder(int[] preOrderList, int[] inOrderList, 
			int preOrderListLo, int preOrderListHi, int inOrderListLo, int inOrderListHi) {
		if (preOrderListLo > preOrderListHi || inOrderListLo > inOrderListHi) {
			return null;
		}
				
		int val = preOrderList[preOrderListLo];
		int index = indexOf(inOrderList, inOrderListLo, inOrderListHi, val);
		TreeNode root = new TreeNode(val);
		
		int inOrderListLeftLo = inOrderListLo;
		int inOrderListLeftHi = index - 1;
		int inOrderListRightLo = index + 1;
		int inOrderListRightHi = inOrderListHi;
		
		int preOrderListLeftLo = preOrderListLo + 1;
		int preOrderListLeftHi = preOrderListLeftLo + inOrderListLeftHi - inOrderListLeftLo;
		int preOrderListRightLo = preOrderListLeftHi + 1;
		int preOrderListRightHi = preOrderListHi;		
		
		root.left = deserializePreInOrder(preOrderList, inOrderList, preOrderListLeftLo, 
				preOrderListLeftHi, inOrderListLeftLo, inOrderListLeftHi);
		root.right = deserializePreInOrder(preOrderList, inOrderList, preOrderListRightLo, 
				preOrderListRightHi, inOrderListRightLo, inOrderListRightHi);		
		return root;		
	}
	
	public static TreeNode deserializeWithPostInOrder(int[] postOrderList, int[] inOrderList) {
		return deserializeWithPostInOrder(postOrderList, inOrderList, 
				0, postOrderList.length - 1, 0, inOrderList.length - 1);
	}	
	
	public static TreeNode deserializeWithPostInOrder(int[] postOrderList, int[] inOrderList, 
			int postOrderListLo, int postOrderListHi, int inOrderListLo, int inOrderListHi) {
		if (postOrderListLo > postOrderListHi || inOrderListLo > inOrderListHi) {
			return null;
		}
				
		int val = postOrderList[postOrderListHi];
		int index = indexOf(inOrderList, inOrderListLo, inOrderListHi, val);
		TreeNode root = new TreeNode(val);
		
		int inOrderListLeftLo = inOrderListLo;
		int inOrderListLeftHi = index - 1;
		int inOrderListRightLo = index + 1;
		int inOrderListRightHi = inOrderListHi;
		
		int postOrderListLeftLo = postOrderListLo;
		int postOrderListLeftHi = postOrderListLeftLo + inOrderListLeftHi - inOrderListLeftLo;
		int postOrderListRightLo = postOrderListLeftHi + 1;
		int postOrderListRightHi = postOrderListHi - 1;		
		
		root.left = deserializeWithPostInOrder(postOrderList, inOrderList, postOrderListLeftLo, 
				postOrderListLeftHi, inOrderListLeftLo, inOrderListLeftHi);
		root.right = deserializeWithPostInOrder(postOrderList, inOrderList, postOrderListRightLo, 
				postOrderListRightHi, inOrderListRightLo, inOrderListRightHi);		
		return root;		
	}	
	
	private static int indexOf(int[] n, int lo, int hi, int val) {
		for (int i = lo; i <= hi; i++) {
			if (n[i] == val) {
				return i;
			}
		}
		return -1;
	}
	
	static class TreeNode {
		TreeNode left;
		TreeNode right;
		int value;
		
		public TreeNode(int value) {
			this.value = value;
		}
	}	
}