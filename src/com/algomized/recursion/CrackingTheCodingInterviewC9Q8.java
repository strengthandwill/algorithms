package com.algomized.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5
 * cents) and pennies (1 cent), write code to calculate the number of ways of representing
 * n cents.
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q8 {	
	public static void main(String[] args) {
		int[] coins = {25, 10, 5, 1};
		System.out.println(getCoinsCombinations(coins, 26));
		System.out.println(getCoinsCombinationsNumber(coins, 26));
	}
	
	public static int getCoinsCombinationsNumber(int[] coins, int cents) {
		if (cents < 0) return 0;
		if (cents == 0) return 1;	
		int sum = 0;
		for (int coin : coins) {
			sum += getCoinsCombinationsNumber(coins, cents - coin);
		}
		return sum;
	}	
	
	public static List<List<Integer>> getCoinsCombinations(int[] coins, int cents) {
		if (coins == null || cents < 0) return null;
		List<List<Integer>> cc = new ArrayList<List<Integer>>();
		getCoinsCombinations(coins, cents, new Stack<Integer>(), cc);
		return cc;
	}
	
	@SuppressWarnings("unchecked")
	private static void getCoinsCombinations(int[] coins, int cents, 
			Stack<Integer> c, List<List<Integer>> cc) {
		if (cents < 0) return;
		if (cents == 0) {
			cc.add((List<Integer>) c.clone());
			return;
		}
		for (int coin : coins) {
			c.push(coin);
			getCoinsCombinations(coins, cents - coin, c, cc);
			c.pop();
		}
	}
}
