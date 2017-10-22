/**
 * This is a simple console version of a drawing program.
 */
package io.drawing;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.drawing.command.Command;
import io.drawing.command.CommandContext;
import io.drawing.command.CommandExecutionException;
import io.drawing.command.InvalidCommandException;
import io.drawing.renderer.StreamRenderer;


/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private static final byte[] PROMOPT_MSG = "enter command: ".getBytes();
	
	public static void main(String[] args) {
	
		CommandContext context = new CommandContext(System.in, System.out);
		Scanner scanner = new Scanner(context.getInputStream());
		
		OutputStream outputStream = context.getOutputStream();
		context.setRenderer(new StreamRenderer(outputStream));
		
		try {
			while (true) {
				outputStream.write(PROMOPT_MSG);
				String input = scanner.nextLine();
				try {
					Command command = context.createCommand(input);
					command.execute();
				} catch (InvalidCommandException | CommandExecutionException ex) {
					String message = ex.getMessage();
					if (StringUtils.isNotBlank(message)) {
						outputStream.write(message.getBytes());
					}
					outputStream.write('\n');
				}
			}
		}catch (IOException ex) {
			logger.debug(ex.getMessage(), ex);
		}finally{
			scanner.close();
			try {
				outputStream.close();
			} catch (IOException ex) {
				logger.debug(ex.getMessage(), ex);
			}
			System.exit(-1);
		}
	}
	
}
