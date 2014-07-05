package com.algomized.concepts.objectoriented.CrackingTheCodingInterviewC8Q2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author Poh Kah Kong
 * 
 * <p>
 * Imagine you have a call center with three levels of employees: respondent,
 * manager, and director. An incoming telephone call must be first allocated to a
 * respondent who is free. If the respondent can't handle the call, he or she must
 * escalate the call to a manager. If the manager is not free or not able to handle it,
 * then the call should be escalated to a director. Design the classes and data structures
 * for this problem. Implement a method dispatchCall() which assigns a
 * call to the first available employee.
 * </p>
 *
 */
public class CallCenter {
	private static CallCenter instance = null;
	private Queue<Call>[] calls;
	private ArrayList<Employee>[] employees;
	
	public static void main(String[] args) {
		CallCenter callCenter = CallCenter.getInstance();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Respondent("R1"));
		employees.add(new Manager("M1"));
		employees.add(new Director("D1"));		
		for (Employee employee : employees) {
			callCenter.addEmployee(employee);
		}
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			// input new call
			String input = null;
			System.out.print("New call (Msg/N): ");
			input = sc.nextLine();
			if (input.equals("Q")) {
				break;
			} else if (!input.equals("N")) {
				callCenter.receiveCall(new Call(input));
			}
			System.out.println(callCenter);
			callCenter.dispatchCall();
			
			// employee act on his call
			for (Employee employee : employees) {
				if (!employee.isFree()) {
					System.out.print(employee + " (C/E/N/Q): ");
					input = sc.nextLine();
					if (input.equals("Q")) {
						break;
					} else if (input.equals("C")) {
						employee.completeCall();
					} else if (input.equals("E")) {
						callCenter.receiveCall(employee.escalateCall());
					}
				}
			}
			System.out.println("***************************");
		}
		sc.close();
		System.out.println("Call Center is closed.");
	}
	
	@SuppressWarnings("unchecked")
	private CallCenter() {
		calls = (Queue<Call>[]) new LinkedList[Employee.MAX_LEVEL + 1];		
		for (int i = 0; i < calls.length; i++) {
			calls[i] = new LinkedList<Call>();
		}
		employees = (ArrayList<Employee>[]) new ArrayList[Employee.MAX_LEVEL + 1];
		for (int i = 0; i < employees.length; i++) {
			employees[i] = new ArrayList<Employee>();
		}
	}
	
	public static CallCenter getInstance() {
		if (instance == null) {
			instance = new CallCenter();
		}
		return instance;
	}
	
	public void addEmployee(Employee employee) {
		employees[employee.getLevel()].add(employee);		
	}

	public void receiveCall(Call call) {
		if (call == null) {
			return;
		}		
		calls[call.getLevel()].add(call);
	}
		
	public void dispatchCall() {
		for (int i = 0; i < calls.length; i++) {
			if (!calls[i].isEmpty()) {
				for (Employee employee : employees[i]) {
					if (employee.isFree() && (employee.getLevel() == calls[i].peek().getLevel())) {
						employee.receiveCall(calls[i].remove());
					}
				}				
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < calls.length; i++) {
			strBuf.append("{Level " + i + ": " + calls[i] + "}");
		}
		return strBuf.toString();
	}
}