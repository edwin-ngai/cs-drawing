/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.util;

import org.apache.commons.lang3.Validate;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Utils {

	public static void validateCoordinate(int coordinate, int min, String name) {
		Validate.isTrue(coordinate>=min, 
				"Invalid %s [%d]! Expected: %s >=%d", name, coordinate, name, min);
	}

	public static void validateCoordinate(int coordinate, int min, int max, String name) {
		Validate.isTrue(coordinate>=min && coordinate<=max, 
				"Invalid %s [%d] ! Expected: %s = [%d, %d]", name, coordinate, name, min, max);
	}
	
	public static void validateTwoCoordinates(int c1, int c2, String name1, String name2) {
		
		Validate.isTrue(c1 <= c2, "Invalid %s [%d] or %s [%d]! Expected: %s > %s.", name1, c1, name2, c2, name1, name2);
	}
	
	private Utils() {
		
	}
	
}