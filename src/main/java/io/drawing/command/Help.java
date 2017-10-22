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
public class Help implements Command {

	private static final String[] HELP_MSG = new String[]{
		"Command\t\tDescription\n\n",
		"C w h\t\tCreate a new canvas of width w and height h.\n\n",
		"L x1 y1 x2 y2\tCreate a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.\n\n",
		"R x1 y1 x2 y2\tCreate a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.\n\n",
		"B x y c\t\tFill the entire area connected to (x,y) with \"colour\" c. The behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.\n\n",
		"Q\t\tQuit the program.\n\n",
		"H\t\tShow this help.\n"
	};
	
	private CommandContext context = null;
	
	public Help(CommandContext context) {
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
			for (String line : HELP_MSG) {
				outputStream.write(line.getBytes());
			}
		}catch (Exception ex) {
			//nothing to do here since this command will quit the program.
		}
	}

}
