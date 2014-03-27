/*
 *   ________________________________________________________________________________________
 *   
 *   Y O O R E E K A
 *   A library for data mining, machine learning, soft computing, and mathematical analysis
 *   ________________________________________________________________________________________ 
 *    
 *   The Yooreeka project started with the code of the book "Algorithms of the Intelligent Web " 
 *   (Manning 2009). Although the term "Web" prevailed in the title, in essence, the algorithms 
 *   are valuable in any software application.
 *  
 *   Copyright (c) 2007-2009    Haralambos Marmanis & Dmitry Babenko
 *   Copyright (c) 2009-2014 Marmanis Group LLC and individual contributors as indicated by the @author tags.  
 * 
 *   Certain library functions depend on other Open Source software libraries, which are covered 
 *   by different license agreements. See the NOTICE file distributed with this work for additional 
 *   information regarding copyright ownership and licensing.
 * 
 *   Marmanis Group LLC licenses this file to You under the Apache License, Version 2.0 (the "License"); 
 *   you may not use this file except in compliance with the License.  
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software distributed under 
 *   the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 *   either express or implied. See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.yooreeka.math;

import org.yooreeka.util.P;

/**
 * A class that can create generalized Fibonacci sequences.
 * 
 * @author <a href="mailto:babis@marmanis.com">Babis Marmanis</a>
 *
 */
public class Fibonacci {

	private int order;
	private int size;
	private long[] memory;
	
	/**
	 * This constructor allows us to create an implementation that is more efficient
	 * than the recursive implementation.
	 * 
	 * @param k the order of the generalized Fibonacci
	 * @param n the number in the sequence to be evaluate
	 */
	public Fibonacci(int order, int size) {
		this.order = order;
		this.size  = size;
		memory = new long[size];
		init();
	}
	
	/**
	 * We i
	 */
	private void init() {
		for (int i=0; i<size; i++) {
			if (i<order)
				memory[i] = i;
			else
				memory[i] = -1;
		}
	}
	
	/**
	 * Diagnostic utility
	 */
	public void printMemory() {
		P.hline();
		for (int i=0; i<memory.length; i++) {
			P.println("memory["+i+"] --> "+memory[i]);
		}
		P.hline();		
	}

	/**
	 * This method stores previously computed values in "memory".
	 * 
	 * @param n is the index of the generalized Fibonacci number that we want to compute
	 * 
	 * @return the nth generalized Fibonacci number
	 */
	public long get(int n) {
		if (memory[n] < 0) {
			memory[n] = recursive(order,n);
		} 
		return memory[n];
	}
	
	/**
	 * This is a recursive implementation of a generalization of the Fibbonacci sequence.
	 * 
	 * @param k the order of generalization
	 * @param n the number
	 * @return the generalized Fibbonacci number
	 */
	public static long recursive(final int k, int n) {
		long val=0;

		if (n<k) {
			val = n;
		} else {
			
			for (int i=1; i<=k; i++) {
				val = val + recursive(k,n-i);				
			}
		}
		return val;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		Fibonacci f2 = new Fibonacci(2,11);
//		Fibonacci f3 = new Fibonacci(3,11);
//		Fibonacci f4 = new Fibonacci(4,11);
//		Fibonacci f5 = new Fibonacci(5,11);
//		StringBuilder msg = new StringBuilder();
//		
//		for (int i=2; i < 11; i++) {
//			msg.append(f2.get(i)).append(", ");
//			msg.append(f3.get(i)).append(", ");
//			msg.append(f4.get(i)).append(", ");
//			msg.append(f5.get(i)).append("\n");			
//		}
//		P.print(msg.toString());
	}
}
