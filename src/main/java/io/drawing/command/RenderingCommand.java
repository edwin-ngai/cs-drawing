/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import static io.drawing.util.Constant.CANVAS_IS_NULL;
import static io.drawing.util.Constant.CONTEXT_IS_NULL;

import java.util.Objects;

import io.drawing.renderer.Renderer;
import io.drawing.shape.Canvas;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public abstract class RenderingCommand implements Command{
	
	protected CommandContext context;
	//the displacement of canvas and command coordinates
	//current canvas is start from 0, and command is start from 1
	//so that the displacement is 1
	protected int displacement = 1;

	public RenderingCommand(CommandContext context) {
		
		Objects.requireNonNull(context, CONTEXT_IS_NULL);
		this.context = context;
	}
	
	@Override
	public void execute() throws CommandExecutionException{
		
		doExecution();
		Canvas canvas = context.getCanvas();
		if (canvas == null) {
			throw new CommandExecutionException(CANVAS_IS_NULL);
		}
		Renderer renderer = context.getRenderer();
		if (renderer != null) {
			renderer.render(canvas);
		}else {
			throw new CommandExecutionException("Renderer is null. The canvas cannot be rendered.");
		}
	}
	
	protected abstract void doExecution() throws CommandExecutionException;
	
}
