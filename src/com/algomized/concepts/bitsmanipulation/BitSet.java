package com.algomized.concepts.bitsmanipulation;

public class BitSet {
	private final int size;
	private byte[] bv;	
	
	public BitSet(int size) {
		this.size = size;
		bv = new byte[size / 8 + (size % 8 > 0 ? 1 : 0)];
	}
	
	public void set(int index) {
		if (!checkRange(index)) return;
		bv[index / 8] |= 1 << (index % 8);
	}
	
	public void set(int index, boolean value) {
		if (!checkRange(index)) return;
		bv[index / 8] &= ~(1 << index % 8);
		bv[index / 8] |= (value ? 1 : 0) << (index % 8);
	}	
	
	public boolean get(int index) {
		if (!checkRange(index)) return false;
		return (bv[index / 8] & 1 << (index % 8)) > 0; 
	}
	
	private boolean checkRange(int index) {
		return index >= 0 && index < size;
	}
}
