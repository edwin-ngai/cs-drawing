/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import static io.drawing.util.Constant.ARGUMENT_NOT_FOUND;
import static io.drawing.util.Constant.ARGUMENT_NOT_NUMERIC;
import static io.drawing.util.Constant.ARGUMENT_OUT_OF_RANGE;
import static io.drawing.util.Constant.CANVAS_IS_NULL;
import static io.drawing.util.Constant.INSUFFICIENT_ARGUMENT;
import static io.drawing.util.Constant.MAX_CANVAS_SIZE;
import static io.drawing.util.Constant.MIN_CANVAS_SIZE;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import io.drawing.shape.Canvas;
import io.drawing.shape.Point;
import io.drawing.shape.Rectangle;
import io.drawing.util.Utils;
/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class DrawRectangle extends RenderingCommand {

	private Canvas canvas;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public DrawRectangle(String[] arguments, CommandContext context) {
		
		super(context);
		Validate.notNull(arguments, ARGUMENT_NOT_FOUND);
		Validate.isTrue(arguments.length>=4, INSUFFICIENT_ARGUMENT, 4);
		for (String arg : arguments) {
			Validate.isTrue(StringUtils.isNumeric(arg), ARGUMENT_NOT_NUMERIC, arg);
			//maximum canvas size is 999
			Validate.isTrue(arg.length()<=3, ARGUMENT_OUT_OF_RANGE, arg, MIN_CANVAS_SIZE, MAX_CANVAS_SIZE);
		}
		int localX1 = Integer.parseInt(arguments[0]);
		int localY1 = Integer.parseInt(arguments[1]);
		int localX2 = Integer.parseInt(arguments[2]);
		int localY2 = Integer.parseInt(arguments[3]);

		init(localX1, localY1, localX2, localY2, context);
	}
	
	private void init(int x1, int y1, int x2, int y2, CommandContext context) {
		
		Canvas localCanvas = context.getCanvas();
		Validate.notNull(localCanvas, CANVAS_IS_NULL);
		this.canvas = localCanvas;
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		int minX = displacement;
		int maxX = width-1 + displacement;
		int minY = displacement;
		int maxY = height-1 + displacement;
		Utils.validateCoordinate(x1, minX, maxX, "x1");
		Utils.validateCoordinate(y1, minY, maxY, "y1");
		Utils.validateCoordinate(x2, minX, maxX, "x2");
		Utils.validateCoordinate(y2, minY, maxY, "y2");
		Utils.validateTwoCoordinates(x1, x2, "x1", "x2");
		Utils.validateTwoCoordinates(y1, y2, "y1", "y2");
		//there might be displacement between canvas and command coordinates
		this.x1 = x1-displacement;
		this.y1 = y1-displacement;
		this.x2 = x2-displacement;
		this.y2 = y2-displacement;

	}
	/* (non-Javadoc)
	 * @see io.drawing.command.RenderingCommand#doExecution()
	 */
	@Override
	protected void doExecution() throws CommandExecutionException {

		Rectangle rectangle = new Rectangle(new Point(x1, y1), new Point(x2, y2));
		if (!canvas.drawShape(rectangle)) {
			throw new CommandExecutionException(
					String.format("Unknown error encountered during drawing rectangle [(%d,%d), (%d,%d)]", x1, y1, x2, y2));
		}
		
	}

}
