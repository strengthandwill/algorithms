package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 *
 * <p>
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the Ts digit is at the
 * head of the list. Write a function that adds the two numbers and returns the sum
 * as a linked list.<br>
 * EXAMPLE<br>
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is, 617 + 295.<br>
 * Output: 2 -> 1 -> 9.That is, 912.<br>
 * FOLLOW UP<br>
 * Suppose the digits are stored in forward order. Repeat the above problem.<br>
 * EXAMPLE<br>
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.<br>
 * Output: 9 -> 1 -> 2.That is, 912.
 * </p>
 * 
 */
public class CrackingTheCodingInterviewC2Q5 {

	public static void main(String[] args) {
		
		Node<Integer> op1 = new Node<Integer>(7);
		op1.append(1);
		op1.append(6);
				
		Node<Integer> op2 = new Node<Integer>(5);
		op2.append(9);
		op2.append(2);
		//Node<Integer> resultf = sumForward(op1f, op2f);		
		Node<Integer> result = sumIterative(op1, op2);
		System.out.println(result);
		
		
/*		
		Node<Integer> op2f = new Node<Integer>(2);
		op2f.append(9);
		op2f.append(5);
		//Node<Integer> resultf = sumForward(op1f, op2f);
		//Node<Integer> resultf = sumForwardRecursive(op1f, op2f);		
		Node<Integer> resultf = sumIterative(op1f, op2f);
		System.out.println(resultf);
		
*/	
	}
	
	public static Node<Integer> sumIterative(Node<Integer> op1, Node<Integer> op2) {
		if (op1 == null && op2 == null) return null;
		Node<Integer> result = null;
		int carry = 0;
		while (op1 != null || op2 != null || carry != 0) {
			if (op1 == null) {
				op1 = new Node<Integer>(0);
			}			
			if (op2 == null) {
				op2 = new Node<Integer>(0);
			}
			
			int resultInt = op1.item + op2.item + carry;
			int valueInt = resultInt % 10;
			carry = resultInt / 10;
			
			if (result == null) {
				result = new Node<Integer>(valueInt);
			} else {
				result.append(valueInt);
			}			
			op1 = op1.next;
			op2 = op2.next;
		}
		return result;
	}
	
	/**
	 * Time:  O(n), where n is the greatest length among op1, op2 and result<br>
	 * Space: n * (1 int, 2 nodes) = O(n), where n is the greater length of op1, op2 or result
	 */
	public static Node<Integer> sumRecursive(Node<Integer> op1, Node<Integer> op2) {
		return sumRecursive(op1, op2, 0);
	}
	
	private static Node<Integer> sumRecursive(Node<Integer> op1, Node<Integer> op2, int carry) {
		if (op1 == null && op2 == null && carry == 0) return null;		
		if (op1 == null) op1 = new Node<Integer>(0); // pad with zero
		if (op2 == null) op2 = new Node<Integer>(0); // pad with zero
		int resultInt = op1.item + op2.item + carry; // summing up
		Node<Integer> result = sumRecursive(op1.next, op2.next, resultInt / 10);
		Node<Integer> sum = new Node<Integer>(resultInt % 10);
		if (result == null) result = sum;
		else {
			sum.next = result;
			result = sum;
		}
		return result;
	}
	
	private static int length(Node<Integer> node) {
		if (node == null) return 0;
		int i = 0;
		while (node != null) {
			i++;
			node = node.next;			
		}
		return i;
	}
	
	/**
	 * Time:   O(p) + O(q) + O(r) + O(n) = O(2n) = O(n) where p is the length of op1, q is the length of op2, 
	 * r is abs(p - q) and n is the greater length between op1 and op2<br>
	 * Space: 2 int + 1 IntWrapper + O(n) = O(n), where n is the greater length between op1 and op2.
	 */
	public static Node<Integer> sumForwardRecursive(Node<Integer> op1, Node<Integer> op2) {
		// find length of op1 and op2
		int op1Length = length(op1);
		int op2Length = length(op2);
		
		// pad op1 or op2 with zeros to make them same length
		if (op1Length > op2Length) op2 = padZeros(op2, op1Length - op2Length);
		else if (op2Length > op1Length) op1 = padZeros(op1, op2Length - op1Length);
		
		// summing up
		IntWrapper carry = new IntWrapper();
		Node<Integer> result = sumForwardRecursive(op1, op2, carry);
		if (carry.value > 0) result = insert(result, carry.value);
		return result;
	}
	
	private static Node<Integer> sumForwardRecursive(Node<Integer> op1, Node<Integer> op2, IntWrapper carry) {
		if (op1 == null && op2 == null) return null;
		Node<Integer> result = sumForwardRecursive(op1.next, op2.next, carry);
		int resultInt = op1.item + op2.item + carry.value; // summing up
		carry.value = resultInt / 10; // update carry
		
		// update result
		if (result == null)  result = new Node<Integer>(resultInt % 10);
		else result = insert(result, resultInt % 10);
		return result;
	}		
	
	private static Node<Integer> padZeros(Node<Integer> node, int n) {
		if (node == null || n < 0) return null;
		for (int i = 0; i < n; i++) {
			node = insert(node, 0);
		}
		return node;
	}
	
	private static Node<Integer> insert(Node<Integer> node, int i) {
		Node<Integer> insert = new Node<Integer>(i);
		insert.next = node;
		return insert;
	}
	
	static class IntWrapper {
		int value = 0;
	}
	
	/**
	 * Time:  O(n + m^2), where n is the greater length between op1 and op2 and m is the length of result<br>
	 * Space: 2 int, 1 node
	 */
	public static Node<Integer> sum(Node<Integer> op1, Node<Integer> op2) {
		if (op1 == null || op2 == null) return null;
		int op1Int = 0, op2Int = 0;
		int i = 1;
		while (op1 != null && op2 != null) { // operand linked lists to int
			if (op1 != null) {
				op1Int += op1.item * i;
				op1 = op1.next;
			}
			if (op2 != null) {
				op2Int += op2.item * i;
				op2 = op2.next;
			}
			i *= 10;
		}
		int resultInt = op1Int + op2Int; // summing up
		Node<Integer> result = null;
		while (resultInt > 0) { // result int to linked list
			if (result == null) result = new Node<Integer>(resultInt % 10);
			else result.append(resultInt % 10);
			resultInt /= 10;
		}
		return result;
	}	
	
	/**
	 * Time:  O(n + m), where n is the greater length between op1 or op2 and m is the length of result<br>
	 * Space: 2 int, 1 node = O(1) 
	 */
	public static Node<Integer> sumForward(Node<Integer> op1, Node<Integer> op2) {
		if (op1 == null || op2 == null) return null;
		int op1Int = 0, op2Int = 0;
		while (op1 != null && op2 != null) { // operand linked lists to int
			if (op1 != null) {
				op1Int = op1Int * 10 + op1.item;
				op1 = op1.next;
			}
			if (op2 != null) {
				op2Int = op2Int * 10 + op2.item;
				op2 = op2.next;
			}
		}
		int resultInt = op1Int + op2Int; // summing up
		Node<Integer> result = null;
		while (resultInt > 0) { // int to linkedlist
			if (result == null) result = new Node<Integer>(resultInt % 10);
			else insert(result, resultInt % 10);
			resultInt /= 10;
		}
		return result;
	}
}
