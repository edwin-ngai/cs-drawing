/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.util;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public final class Constant {
	
	public static final int MIN_CANVAS_SIZE = 1;
	public static final int MAX_CANVAS_SIZE = 999;
	
	public static final String CONTEXT_IS_NULL = "Context is null.";
	public static final String CANVAS_IS_NULL = "Canvas is null. It might not be created yet.";
	public static final String INSUFFICIENT_ARGUMENT = "Insufficient arguments. Expected %d arguments";
	public static final String INVALID_COMMAND = "Invalid command. Please try again!\n";

	public static final String INVALID_ARGUMENT = "Invalid argument [%s].";
	public static final String ARGUMENT_NOT_FOUND = "Argument is not found.";
	public static final String ARGUMENT_NOT_NUMERIC = "Argument [%s] is not numeric.";
	public static final String ARGUMENT_OUT_OF_RANGE = "Argument [%s] is out of range. Expected: argument = [%d, %d].";

	
	private Constant() {
		
	}
	
}
