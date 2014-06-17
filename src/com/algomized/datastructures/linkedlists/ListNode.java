package com.algomized.datastructures.linkedlists;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * ListNode with one link, next.
 * </p>
 *
 */
public class ListNode<Item> {
	public static void main(String[] args) {
		ListNode<Integer> node = new ListNode<Integer>(1);
		node.append(2);
		node.append(3);
		node.append(4);
		node.append(5);
		
		System.out.println(node);
		node = insert(node, 20);
		System.out.println(node);
		node = delete(node, 1);
		System.out.println(node);		
		node = delete(node, 3);
		System.out.println(node);							
	}
	
	ListNode<Item> next = null;
	Item item;
	
	public ListNode(Item item) {
		this.item = item;
	}
	
	/**
	 * <b>Append</b><br>
	 * Time:  O(n)<br>
	 * Space: 1 node = O(1)
	 */
	public ListNode<Item> append(Item item) {
		ListNode<Item> node = this;	
		ListNode<Item> end = new ListNode<Item>(item);
		while (node.next != null) {
			node = node.next;
		}
		node.next = end;
		return end;
	}
		
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		ListNode<Item> node = this;
		while (node != null) {
			strBuf.append("[" + node.item + "]");			
			node = node.next;
		}
		return strBuf.toString();
	}
	
	/**
	 * <b>Insert</b><br>
	 * Time:  O(1)<br>
	 * Space: 1 node = O(1) 
	 */
	public static <Item> ListNode<Item> insert(ListNode<Item> head, Item item) {
		if (head == null) return null;		
		ListNode<Item> node = new ListNode<Item>(item);
		node.next = head;
		return node;
	}
	
	/**
	 * <b>Delete<b><br>
	 * Time:  O(n)<br>
	 * Space: O(1)
	 */
	public static <Item> ListNode<Item> delete(ListNode<Item> head, Item item) {
		if (head == null) return null;
		if (head.item.equals(item)) {
			head = head.next;
			return head;
		}
		ListNode<Item> current = head;		
		while (current.next != null) {
			if (current.next.item.equals(item)) {
				current.next = current.next.next;
			}
			current = current.next;
		}
		return head;
	}	
}
