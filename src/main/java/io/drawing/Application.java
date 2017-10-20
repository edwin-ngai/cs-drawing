/**
 * This is a simple console version of a drawing program.
 */
package io.drawing;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.drawing.command.Command;
import io.drawing.command.CommandContext;
import io.drawing.command.CommandFactory;
import io.drawing.command.Quit;
import io.drawing.util.Utils;


/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		Command command = null;
		CommandContext context = new CommandContext();
		while (!(command instanceof Quit)) {
			Utils.print("enter command:");
			String input = scanner.nextLine();
			command = CommandFactory.getCommand(input);
			if (command == null) {
				Utils.print("Invalid command. Please try again!\n");
				Utils.print("enter command:");
			}else {
				command.execute(context);
				Utils.print("enter command:");
			}
		}
		scanner.close();
		System.exit(0);
	}
}
