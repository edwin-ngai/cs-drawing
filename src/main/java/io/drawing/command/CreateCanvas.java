/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import org.apache.commons.lang3.Validate;

import io.drawing.shape.Canvas;
import static io.drawing.util.Constant.*;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class CreateCanvas implements Command {

	private int width, height;

	public CreateCanvas(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		Validate.isTrue(width > 0, "width should be greater than 0.");
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		Validate.isTrue(height > 0, "height should be greater than 0.");
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see io.drawing.command.Command#execute(CommandContext)
	 */
	@Override
	public boolean execute(CommandContext context) {
		
		boolean result = true;
		try {
			int width = Integer.parseInt((String)context.getArgument(WIDTH));
			int height = Integer.parseInt((String)context.getArgument(HEIGHT));
			Canvas canvas = new Canvas(width, height);
			context.setCanvas(canvas);
		}catch (Exception ex) {
			result = false;
		}
		return result;
	}

}
