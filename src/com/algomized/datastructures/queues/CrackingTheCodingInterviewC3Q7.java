package com.algomized.datastructures.queues;


/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * An animal shelter holds only dogs and cats, and operates on a strictly "first in, 
 * first out" basis. People must adopt either the "oldest" (based on arrival time) of 
 * all animals at the shelter, or they can select whether they would prefer a dog or 
 * a cat (and will receive the oldest animal of that type). They cannot select which 
 * specific animal they would like. Create the data structures to maintain this system 
 * and implement operations such as enqueue, dequeueAny, dequeueDog and 
 * dequeueCat.You may use the built-in LinkedList data structure.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC3Q7 {

	public static void main(String[] args) {
		AnimalQueueWithSeparateQueues queue = new AnimalQueueWithSeparateQueues();
		//AnimalQueueWithSingleQueue queue = new AnimalQueueWithSingleQueue();
		queue.enqueue(new Dog("Dog A"));
		queue.enqueue(new Cat("Cat A"));
		queue.enqueue(new Cat("Cat B"));
		queue.enqueue(new Dog("Dog B"));
		queue.enqueue(new Dog("Dog C"));
		
		System.out.println(queue.dequeueAny());
		System.out.println(queue.dequeueDog());
		System.out.println(queue.dequeueCat());
		System.out.println(queue.dequeueDog());
		System.out.println(queue.dequeueAny());
	}
	
	static class Animal {		
		String name;
		int order;
		
		public Animal(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "[" + name + "]"; 
		}
	}
	
	static class Dog extends Animal {
		public Dog(String name) {
			super(name);
		}
		
		public String toString() {
			return "[Dog: " + name + "]"; 
		}
	}
	
	static class Cat extends Animal {
		public Cat(String name) {
			super(name);
		}
		
		public String toString() {
			return "[Cat: " + name + "]"; 
		}
	}
	
	/**
	 * Uses separate queues to store dogs and cats. Each item has a timestamp called order 
	 * to determine which item to remove first in dequeueAny().
	 */	
	static class AnimalQueueWithSeparateQueues {
		private Queue<Animal> dogs, cats;
		private int size = 0;
		
		public AnimalQueueWithSeparateQueues() {
			dogs = new Queue<Animal>();
			cats = new Queue<Animal>();
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: O(1) 
		 */
		public void enqueue(Animal animal) {
			animal.order = size;
			if (Dog.class.isInstance(animal)) {
				dogs.enqueue(animal);
			} else if (Cat.class.isInstance(animal)) {
				cats.enqueue(animal);
			}
			size++;
		}

		/**
		 * Time:  O(1)<br>
		 * Space: O(1)
		 */
		public Animal dequeueAny() {
			if (dogs.isEmpty()) {
				return cats.dequeue();
			}
			if (cats.isEmpty()) {
				return dogs.dequeue();
			}
			if (dogs.peek().order < cats.peek().order) {
				return dogs.dequeue();
			} else {
				return cats.dequeue();
			}
		}

		/**
		 * Time:  O(1)<br>
		 * Space: 1 animal = O(1)
		 */
		public Animal dequeueDog() {
			if (dogs.isEmpty()) {
				return null;
			}
			Animal animal = dogs.dequeue();
			size--;
			return animal;
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: 1 animal = O(1)
		 */
		public Animal dequeueCat() {
			if (cats.isEmpty()) {
				return null;
			}
			Animal animal = cats.dequeue();
			size--;
			return animal;
		}		
	}
	
	/**
	 * Uses a single queue to store both Dog and Cat. When dequeuing Dog or Cat, 
	 * transverses down the nodes to find the required type.
	 */
	static class AnimalQueueWithSingleQueue {
		private Node first, end;
		private int size = 0;
		
		class Node {
			Animal animal;
			Node next;
			
			public Node(Animal animal, Node next) {
				this.animal = animal;
				this.next = next;
			}
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: 1 node = O(1)
		 */
		public void enqueue(Animal animal) {
			Node node = new Node(animal, null);
			if (first == null) {
				first = node;
				end = node;
			} else {
				end.next = node;
				end = end.next;
			}
			size++;
		}
		
		/**
		 * Time:  O(1)<br>
		 * Space: 1 animal = O(1)
		 */
		public Animal dequeueAny() {
			if (first == null) {
				return null;
			}
			Animal animal = first.animal;
			first = first.next;
			size--;
			return animal;
		}
		
		public Animal dequeueDog() {
			return dequeueType(Dog.class);
		}
		
		public Animal dequeueCat() {
			return dequeueType(Cat.class);
		}		
		
		/**
		 * Time:  O(1) for best case, O(n) for average case<br>
		 * Space: 1 animal = O(1)
		 */
		private Animal dequeueType(Class<?> classz) {
			if (first == null) {
				return null;
			}
			// first item is the required type so return as normal
			if (classz.isInstance(first.animal)) { 
				return dequeueAny();
			}
			
			// search for required type as it is not in the first item
			Node current = first;
			while (current.next != null) {
				Animal animal = current.next.animal;
				if (classz.isInstance(animal)) { // required type found
					current.next = current.next.next;
					size--;
					return animal;
				}
				current = current.next;
			}
			return null; // required type not found
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public int size() {
			return size;			
		}
		
		public String toString() {
			StringBuffer strBuf = new StringBuffer();
			Node current = first;
			while (current != null) {
				strBuf.append("[" + current.animal + "]");
				current = current.next;
			}
			return strBuf.toString();
		}
	}

}
