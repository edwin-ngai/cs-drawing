/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import static io.drawing.util.Constant.ARGUMENT_NOT_FOUND;
import static io.drawing.util.Constant.ARGUMENT_NOT_NUMERIC;
import static io.drawing.util.Constant.ARGUMENT_OUT_OF_RANGE;
import static io.drawing.util.Constant.INSUFFICIENT_ARGUMENT;
import static io.drawing.util.Constant.MAX_CANVAS_SIZE;
import static io.drawing.util.Constant.MIN_CANVAS_SIZE;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import io.drawing.shape.Canvas;
import io.drawing.util.Utils;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class CreateCanvas extends RenderingCommand {

	private int width;
	private int height;

	public CreateCanvas(String[] arguments, CommandContext context) {
		
		super(context);
		Validate.notNull(arguments, ARGUMENT_NOT_FOUND);
		Validate.isTrue(arguments.length>=2, INSUFFICIENT_ARGUMENT, 2);
		for (String arg : arguments) {
			Validate.isTrue(StringUtils.isNumeric(arg), ARGUMENT_NOT_NUMERIC, arg);
			//maximum canvas size is 999
			Validate.isTrue(arg.length()<=3, ARGUMENT_OUT_OF_RANGE, arg, MIN_CANVAS_SIZE, MAX_CANVAS_SIZE);
		}
		int localWidth = Integer.parseInt(arguments[0]);
		int localHeight = Integer.parseInt(arguments[1]);
		init(localWidth, localHeight);

	}
	
	private void init(int width, int height) {
		
		Utils.validateCoordinate(width, MIN_CANVAS_SIZE, MAX_CANVAS_SIZE, "width");
		Utils.validateCoordinate(height, MIN_CANVAS_SIZE, MAX_CANVAS_SIZE, "height");

		this.width = width;
		this.height = height;
	}
	/* (non-Javadoc)
	 * @see io.drawing.command.RenderingCommand#doExecution(io.drawing.shape.Canvas)
	 */
	@Override
	protected void doExecution() throws CommandExecutionException {
		Canvas canvas = new Canvas(width, height);
		context.setCanvas(canvas);
	}

}
