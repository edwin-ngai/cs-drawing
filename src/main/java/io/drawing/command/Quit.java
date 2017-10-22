/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import static io.drawing.util.Constant.CONTEXT_IS_NULL;

import java.io.OutputStream;
import java.util.Objects;
/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Quit implements Command {

	private static final String QUIT_MSG = "The program will exit.";
	
	private CommandContext context = null;
	
	public Quit(CommandContext context) {
		Objects.requireNonNull(context, CONTEXT_IS_NULL);
		this.context = context;
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see io.drawing.command.Command#execute()
	 */
	@Override
	public void execute() throws CommandExecutionException{
		
		try {
			OutputStream outputStream = context.getOutputStream();
			outputStream.write(QUIT_MSG.getBytes());
			Thread.sleep(500);
		}catch (Exception ex) {
			//nothing to do here since this command will quit the program.
		}finally {
			System.exit(0);
		}
	}

}
