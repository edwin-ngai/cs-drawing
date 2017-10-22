/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class CommandExecutionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3822852571499590339L;

	public CommandExecutionException(String message) {
		super(message);
	}
	
	public CommandExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
