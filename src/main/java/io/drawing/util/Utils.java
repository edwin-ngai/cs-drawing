/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.util;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Utils {

	public static void print(final String message, final Object... values) {
		System.out.printf(message, values);
	}

	public static void print(char c) {
		System.out.print(c);
	}
}
