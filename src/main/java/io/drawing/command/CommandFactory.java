/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import static io.drawing.util.Constant.*;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public final class CommandFactory {

	public static Command getCommand(String input) {
		
		Command result = null;
		if (StringUtils.isNotBlank(input)) {
			String[] commandAndArguments = StringUtils.split(input);
			String command = commandAndArguments[0];
			if (COMMAND_QUIT.equals(command)) {
				result = new Quit();
			}else if (COMMAND_CREATE_CANVAS.equals(command) && commandAndArguments.length == 3) {
				int[] arguments = getCommandArguments(commandAndArguments[1], commandAndArguments[2]);
				if (arguments != null) {
					result = new CreateCanvas(arguments[0], arguments[1]);
				}
			}
		}
		return result;
	}
	
	private static int[] getCommandArguments(String... arguments) {
		int[] result = new int[arguments.length];
		try {
			for (int i=0; i<result.length; i++) {
				result[i] = Integer.parseInt(arguments[i]);
			}
		}catch (NumberFormatException ex) {
			result = null;
		}
		return result;
	}
}
