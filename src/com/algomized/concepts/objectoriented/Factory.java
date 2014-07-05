package com.algomized.concepts.objectoriented;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Create an instance of a class, with its subclasses deciding which class to instantiate.
 * </p>
 *
 */
public class Factory {
	public static enum ProductType {A, B}

	public static void main(String[] args) {
		Product a = Factory.createProduct(ProductType.A);
		Product b = Factory.createProduct(ProductType.B);
		System.out.println(a);
		System.out.println(b);
	}
	
	public static Product createProduct(ProductType type) {
		Product product = null;
		switch (type) {
		case A:
			product = new ProductA("A", 10);
			break;
		case B:
			product = new ProductB("B", 10.5);
			break;
		}
		return product;
	}
	
	abstract static class Product {
		protected String name;	
		
		public Product(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "[" + name + "]";
		}
	}
	
	static class ProductA extends Product {
		private int height;
		
		public ProductA(String name, int height) {
			super(name);
			this.height = height;
		}	
		
		public String toString() {
			return "[" + name + "][" +  height + "]";
		}		
	}

	static class ProductB extends Product {
		private double weight;
		
		public ProductB(String name, double weight) {
			super(name);
			this.weight = weight;
		}
		
		public String toString() {
			return "[" + name + "][" + weight + "]";
		}		
	}
}
