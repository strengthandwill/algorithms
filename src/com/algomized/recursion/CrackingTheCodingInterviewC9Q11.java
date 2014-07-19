package com.algomized.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Given a boolean expression consisting of the symbols 0,1, &, |, and A, and a
 * desired boolean result value result, implement a function to count the number
 * of ways of parenthesizing the expression such that it evaluates to result.<br>
 * EXAMPLE<br>
 * Expression: 1^0|0|1<br>
 * Desired result: false (0)<br>
 * Output: 2 ways: 1^((0|0)|1) and 1^(0|(0|1)).<br>
 * </p>
 *
 */
public class CrackingTheCodingInterviewC9Q11 {
	public static void main(String[] args) {
		String exp = "1^0|0|1";	
		System.out.println(count(exp, false));
		System.out.println(countRecursive(exp, false));
		System.out.println(countDP(exp, false));
		System.out.println(countDPCatalan(exp, false));
	}
	
	public static int count(String exp, boolean result) {
		if (exp == null) return -1;
		return count(exp, 0, new Stack<Character>(), new Stack<Character>(), result);
	}
	
	@SuppressWarnings("unchecked")
	private static int count(String exp, int i, Stack<Character> availableOprs, 
			Stack<Character> pre, boolean result) {
		if (i == 0) {
			pre.add(exp.charAt(i));
			return count(exp, i + 1, availableOprs, pre, result);
		}
		
		availableOprs.push(exp.charAt(i++));
		pre.push(exp.charAt(i));							
		if (i == exp.length() - 1) {
			while (!availableOprs.isEmpty()) pre.push(availableOprs.pop());
			return calculate(pre) == result ? 1 : 0;
		}		
		int sum = count(exp, i + 1, (Stack<Character>) availableOprs.clone(), 
				(Stack<Character>) pre.clone(), result);
		while (!availableOprs.isEmpty()) {
			pre.push(availableOprs.pop());
			sum += count(exp, i + 1, (Stack<Character>) availableOprs.clone(), 
				(Stack<Character>) pre.clone(), result);			
		}
		return sum;
	}
	
	private static boolean calculate(Stack<Character> pre) {	
		// reverse stack
		Stack<Character> pe = new Stack<Character>();
		while (!pre.isEmpty()) pe.push(pre.pop());
		
		// compute result using postfix
		Stack<Character> opds = new Stack<Character>();
		while (!pe.isEmpty()) {
			char c = pe.pop();
			if (c == '&' || c == '|' || c == '^') {
				char r = 0; 
				switch (c) {
					case '&': r = (char) (opds.pop() & opds.pop()); break;
					case '|': r = (char) (opds.pop() | opds.pop()); break;
					case '^': r = (char) (opds.pop() ^ opds.pop()); break;
				}
				opds.push(r);
			} else {
				opds.push(c);
			}
		}
		return opds.pop() == '1' ? true : false;
	}	
	
	public static int countRecursive(String exp, boolean result) {
		if (exp == null) return -1;
		return countRecursive(exp, 0, exp.length() - 1, result);
	}
	
	private static int countRecursive(String exp, int start, int end, boolean result) {
		if (start == end) return (exp.charAt(start) == '1') == result ? 1 : 0;	
		
		int sum = 0;
		for (int i = start + 1; i <= end; i += 2) {
			char opr = exp.charAt(i);
			if (result) {
				if (opr == '&') {
					sum += countRecursive(exp, start, i - 1, true) * 
							countRecursive(exp, i + 1, end, true); 
				} else if (opr == '|') {
					sum += countRecursive(exp, start, i - 1, true) * 
							countRecursive(exp, i + 1, end, true);
					sum += countRecursive(exp, start, i - 1, true) * 
							countRecursive(exp, i + 1, end, false);
					sum += countRecursive(exp, start, i - 1, false) * 
							countRecursive(exp, i + 1, end, true); 					
				} else if (opr == '^') {
					sum += countRecursive(exp, start, i - 1, true) * 
							countRecursive(exp, i + 1, end, false);
					sum += countRecursive(exp, start, i - 1, false) * 
							countRecursive(exp, i + 1, end, true); 					
				}				
			} else {
				if (opr == '&') {
					sum += countRecursive(exp, start, i - 1, false) * 
							countRecursive(exp, i + 1, end, false); 
					sum += countRecursive(exp, start, i - 1, true) * 
							countRecursive(exp, i + 1, end, false);
					sum += countRecursive(exp, start, i - 1, false) * 
							countRecursive(exp, i + 1, end, true); 										
				} else if (opr == '|') {
					sum += countRecursive(exp, start, i - 1, false) * 
							countRecursive(exp, i + 1, end, false); 
				} else if (opr == '^') {
					sum += countRecursive(exp, start, i - 1, true) * 
							countRecursive(exp, i + 1, end, true);
					sum += countRecursive(exp, start, i - 1, false) * 
							countRecursive(exp, i + 1, end, false); 					
				}				
			}			
		}
		return sum;
	}
	
	public static int countDP(String exp, boolean result) {
		if (exp == null) return -1;
		return countDP(exp, 0, exp.length() - 1, result, new HashMap<String, Integer>());
	}
	
	private static int countDP(String exp, int start, int end, boolean result, Map<String, Integer> cache) {
		String key = "" + start + end + result;
		if (cache.containsKey(key)) {
			return cache.get(key);
		}		
		
		if (start == end) return (exp.charAt(start) == '1') == result ? 1 : 0;	
		
		int sum = 0;
		for (int i = start + 1; i <= end; i += 2) {
			char opr = exp.charAt(i);
			
			if (result) {
				if (opr == '&') {
					sum += countDP(exp, start, i - 1, true, cache) * 
							countDP(exp, i + 1, end, true, cache); 
				} else if (opr == '|') {
					sum += countDP(exp, start, i - 1, true, cache) * 
							countDP(exp, i + 1, end, true, cache);
					sum += countDP(exp, start, i - 1, true, cache) * 
							countDP(exp, i + 1, end, false, cache);
					sum += countDP(exp, start, i - 1, false, cache) * 
							countDP(exp, i + 1, end, true, cache); 					
				} else if (opr == '^') {
					sum += countDP(exp, start, i - 1, true, cache) * 
							countDP(exp, i + 1, end, false, cache);
					sum += countDP(exp, start, i - 1, false, cache) * 
							countDP(exp, i + 1, end, true, cache); 					
				}				
			} else {
				if (opr == '&') {
					sum += countDP(exp, start, i - 1, false, cache) * 
							countDP(exp, i + 1, end, false, cache); 
					sum += countDP(exp, start, i - 1, true, cache) * 
							countDP(exp, i + 1, end, false, cache);
					sum += countDP(exp, start, i - 1, false, cache) * 
							countDP(exp, i + 1, end, true, cache); 										
				} else if (opr == '|') {
					sum += countDP(exp, start, i - 1, false, cache) * 
							countDP(exp, i + 1, end, false, cache); 
				} else if (opr == '^') {
					sum += countDP(exp, start, i - 1, true, cache) * 
							countDP(exp, i + 1, end, true, cache);
					sum += countDP(exp, start, i - 1, false, cache) * 
							countDP(exp, i + 1, end, false, cache); 					
				}				
			}			
		}
		cache.put(key, sum);
		return sum;
	}	
	
	private static int total(int n) {
		return factorial(2 * n) / (factorial(n + 1) * factorial(n));
	}
	
	private static int factorial(int n) {
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
	
	public static int countDPCatalan(String exp, boolean result) {
		if (exp == null) return -1;
		return countDPCatalan(exp, 0, exp.length() - 1, result, new HashMap<String, Integer>());
	}
	
	private static int countDPCatalan(String exp, int start, int end, 
			boolean result, Map<String, Integer> cache) {
		String key = "" + start + end + result;
		if (cache.containsKey(key)) {
			return cache.get(key);
		}		
		
		if (start == end) return (exp.charAt(start) == '1') == result ? 1 : 0;			
		int sum = 0;
		for (int i = start + 1; i <= end; i += 2) {
			char opr = exp.charAt(i);
			if (opr == '&') {
				sum += countDP(exp, start, i - 1, true, cache) * 
						countDP(exp, i + 1, end, true, cache); 
			} else if (opr == '|') {				
				int total = total((i - 1 - start) / 2) * total((end - i - 1) / 2);				
				sum += total - (countDP(exp, start, i - 1, false, cache) * 
						countDP(exp, i + 1, end, false, cache)); 
			} else if (opr == '^') {
				sum += countDP(exp, start, i - 1, true, cache) * 
						countDP(exp, i + 1, end, false, cache);
				sum += countDP(exp, start, i - 1, false, cache) * 
						countDP(exp, i + 1, end, true, cache); 					
			}					
		}
		cache.put(key, sum);
		return result ? sum : total((end - start) / 2) - sum;
	}		
}