/**
 * 
 */
package org.yooreeka.util;

import java.nio.charset.Charset;

/**
 * @author haris
 *
 */
public class P {

	public static void println(String s) {
		System.out.println(s);
	}
	
	/**
	 * Print a 54 character (-) horizontal line.
	 */
	public static void hline() {
		System.out.println("---------- ---------- ---------- ---------- ----------");
	}
	
	public static void main(String[] args) {
		println(Charset.defaultCharset().displayName());
	}
}
