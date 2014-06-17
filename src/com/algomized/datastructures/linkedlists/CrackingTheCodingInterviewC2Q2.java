package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC2Q2 {
	public static void main(String[] args) {
		ListNode<Integer> node = new ListNode<Integer>(1);
		node.append(2);
		node.append(3);
		node.append(4);
		node.append(5);
		System.out.println(node);
//		System.out.println(findKLastNodeUsingSingleReference(node, 1));
//		System.out.println(findKLastNodeRecursiveUsingNodeWrapper( node, 1));
//		System.out.println(findKLastNodeRecursiveUsingIntWrapper(node, 1));
		System.out.println(findKLastNodeUsingRunnerTechnique(node, 1));
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: 2 nodes = O(1)
	 */
	public static ListNode<Integer> findKLastNodeUsingRunnerTechnique(ListNode<Integer> node, int k) {
		if (k <= 0 || node == null) return null;
		ListNode<Integer> first = node;
		ListNode<Integer> second = node;
		for (int i = 0; i < k; i++) { // move first forward to kth node
			if (first == null) return null;
			first = first.next;
		}
		while (first != null) { // move first to last node
			first = first.next;
			second = second.next; // second will reach (n - k)th node
		}
		return second;
	}
	
	/**
	 * Time:  2n = O(n)<br>
	 * Space: 1 node, 2 int = O(1)
	 */
	public static ListNode<Integer> findKLastNodeUsingSingleReference(ListNode<Integer> node, int k) {
		if (k <= 0 || node == null) return null;
		ListNode<Integer> current = node;
		int n = 0;
		while (current != null) { // find length of linked list
			current = current.next;
			n++;
		}
		if (k > n) return null; // no such node 
		n = n - k;
		current = node;
		int i = 0;
		while (current != null) { // find node
			if (i == n) return current; // found
			current = current.next;
			i++;
		}
		return null; // not found
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: O(n)
	 */
	public static ListNode<Integer> findKLastNodeRecursiveUsingNodeWrapper(ListNode<Integer> node, int k) {
		NodeWrapper<Integer> nw = new NodeWrapper<Integer>();
		findKLastNodeRecursiveUsingNodeWrapper(node, k, nw);
		return nw.node;
	}
	
	private static int findKLastNodeRecursiveUsingNodeWrapper(ListNode<Integer> node, int k, NodeWrapper<Integer> nw) {
		if (node == null) return 0;
		int j = findKLastNodeRecursiveUsingNodeWrapper(node.next, k, nw) + 1;
		if (j == k) nw.node = node; // found
		return j;
	}
	
	static class NodeWrapper<Item> {
		ListNode<Item> node = null;
	}
	
	/**
	 * Time:  O(n)<br>
	 * Space: O(n) 
	 */
	public static ListNode<Integer> findKLastNodeRecursiveUsingIntWrapper(ListNode<Integer> node, int k) {
		IntWrapper i = new IntWrapper();
		return findKLastNodeRecursiveUsingIntWrapper(node, k, i);
	}
	
	private static ListNode<Integer> findKLastNodeRecursiveUsingIntWrapper(ListNode<Integer> node, int k, IntWrapper i) {
		if (node == null) return null;
		ListNode<Integer> result = findKLastNodeRecursiveUsingIntWrapper(node.next, k, i);
		i.value = i.value + 1;
		if (i.value == k) result = node;
		return result;		
	}
	
	static class IntWrapper {
		int value = 0;
	}
	
	/**
	 * Time: O(n)<br>
	 * Space: 1 int = O(1)
	 */
	public static ListNode<Integer> findKNode( ListNode<Integer> node, int k) {
		if (k <= 0 || node == null) return null;
		int i = 1;
		while (node != null) {
			if (i == k) return node; // kth node found
			node = node.next;
			i++;
		}
		return null; // kth node not found
	}
}
