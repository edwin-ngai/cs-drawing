/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import static io.drawing.util.Constant.INVALID_COMMAND;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import io.drawing.renderer.Renderer;
import io.drawing.shape.Canvas;
/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public final class CommandContext {

	private static final String COMMAND_QUIT = "Q";
	private static final String COMMAND_CREATE_CANVAS = "C";
	private static final String COMMAND_DRAW_LINE = "L";
	private static final String COMMAND_DRAW_RECTANGLE = "R";
	private static final String COMMAND_BUCKET_FILL = "B";

	
	private Canvas canvas;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Renderer renderer;
	
	public CommandContext(InputStream inputStream, OutputStream outputStream) {
		Objects.requireNonNull(inputStream);
		Objects.requireNonNull(outputStream);
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public void setCanvas(Canvas canvas) {
		Objects.requireNonNull(canvas);
		this.canvas = canvas;
	}

	
	public OutputStream getOutputStream() {
		return outputStream;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public Renderer getRenderer() {
		return renderer;
	}
	public void setRenderer(Renderer renderer) {
		Objects.requireNonNull(renderer);
		this.renderer = renderer;
	}

	public Command createCommand(String input) throws InvalidCommandException{
		
		Command result = null;
		if (StringUtils.isNotBlank(input)) {
			String[] commandAndArguments = StringUtils.split(input);
			String command = commandAndArguments[0];
			String[] arguments = null;
			if (commandAndArguments.length > 1) {
				arguments = Arrays.copyOfRange(commandAndArguments, 1, commandAndArguments.length);
			}
			try {
				if (COMMAND_QUIT.equals(command)) {
					result = new Quit(this);
				}else if (COMMAND_CREATE_CANVAS.equals(command)) {
					result = new CreateCanvas(arguments, this);
				}else if (COMMAND_DRAW_LINE.equals(command)) {
					result = new DrawLine(arguments, this);
				}else if (COMMAND_DRAW_RECTANGLE.equals(command)) {
					result = new DrawRectangle(arguments, this);
				}else if (COMMAND_BUCKET_FILL.equals(command)) {
					result = new BucketFill(arguments, this);
				}
			}catch (RuntimeException ex) {
				throw new InvalidCommandException(ex.getMessage());
			}
 		}
		if (result == null) {
			throw new InvalidCommandException(INVALID_COMMAND);
		}
		return result;
	}

}
