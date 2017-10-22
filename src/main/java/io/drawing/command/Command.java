/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public interface Command {

	public void execute() throws CommandExecutionException;
}
