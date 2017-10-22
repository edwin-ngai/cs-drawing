/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import static io.drawing.util.Constant.ARGUMENT_NOT_FOUND;
import static io.drawing.util.Constant.ARGUMENT_NOT_NUMERIC;
import static io.drawing.util.Constant.ARGUMENT_OUT_OF_RANGE;
import static io.drawing.util.Constant.CANVAS_IS_NULL;
import static io.drawing.util.Constant.INSUFFICIENT_ARGUMENT;
import static io.drawing.util.Constant.INVALID_ARGUMENT;
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
public class BucketFill extends RenderingCommand {

	private Canvas canvas;
	private int x;
	private int y;
	private char color;
	
	public BucketFill(String[] arguments, CommandContext context) {
		
		super(context);
		Validate.notNull(arguments, ARGUMENT_NOT_FOUND);
		Validate.isTrue(arguments.length>=3, INSUFFICIENT_ARGUMENT, 3);
		
		Validate.isTrue(StringUtils.isNumeric(arguments[0]), ARGUMENT_NOT_NUMERIC, arguments[0]);
		Validate.isTrue(arguments[0].length()<=3, ARGUMENT_OUT_OF_RANGE, arguments[0], MIN_CANVAS_SIZE, MAX_CANVAS_SIZE);
		
		Validate.isTrue(StringUtils.isNumeric(arguments[1]), ARGUMENT_NOT_NUMERIC, arguments[1]);
		Validate.isTrue(arguments[1].length()<=3, ARGUMENT_OUT_OF_RANGE, arguments[1], MIN_CANVAS_SIZE, MAX_CANVAS_SIZE);
		
		Validate.isTrue(arguments[2].length()==1, INVALID_ARGUMENT, arguments[2]);
		
		int localX = Integer.parseInt(arguments[0]);
		int localY = Integer.parseInt(arguments[1]);
		char localColor = arguments[2].charAt(0);

		init(localX, localY, localColor, context);
	}
	
	private void init(int x, int y, char color, CommandContext context) {
		
		Canvas localCanvas = context.getCanvas();
		Validate.notNull(localCanvas, CANVAS_IS_NULL);
		canvas = localCanvas;

		int width = canvas.getWidth();
		int height = canvas.getHeight();

		int minX = displacement;
		int maxX = width-1 + displacement;
		int minY = displacement;
		int maxY = height-1 + displacement;
		Utils.validateCoordinate(x, minX, maxX, "x");
		Utils.validateCoordinate(y, minY, maxY, "y");
		//there might be displacement between canvas and command coordinates
		this.x = x-displacement;
		this.y = y-displacement;
		this.color = color;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see io.drawing.command.Command#execute(CommandContext)
	 */
	@Override
	protected void doExecution() throws CommandExecutionException {
		
		canvas.bucketFill(x, y, color);
	}

}
