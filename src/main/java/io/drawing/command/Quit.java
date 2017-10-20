/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Quit implements Command {

	/* (non-Javadoc)
	 * @see io.drawing.command.Command#execute()
	 */
	@Override
	public boolean execute(CommandContext context) {
		System.out.println("The program will exit.");
		return true;
	}

}
