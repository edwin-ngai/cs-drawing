/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class InvalidCommandException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2161676226637828404L;

	public InvalidCommandException(String message) {
		super(message);
	}
}
