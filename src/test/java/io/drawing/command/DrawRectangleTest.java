/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import org.junit.Assert;
import org.junit.Test;

import io.drawing.renderer.StreamRenderer;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class DrawRectangleTest {

	@Test(expected = NullPointerException.class)
	public void whenArgumentIsNullThenExceptionIsThrown() {
		new DrawRectangle(null, new CommandContext(System.in, System.out));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentLengthLessThan4ThenExceptionIsThrown() {
		new DrawRectangle(new String[]{"XXX", "YYY", "ZZZ"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentAreNotNumericThenExceptionIsThrown() {
		new DrawRectangle(new String[]{"111", "XXX", "YYY", "ZZZ"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentOutOfRangeThenExceptionIsThrown() {
		new DrawRectangle(new String[]{"9999", "8765", "231", "456"}, new CommandContext(System.in, System.out));
	}
	
	
	@Test
	public void whenArgumentCorrectThenInstanceIsCreatedSuccessfully() throws CommandExecutionException{
		CommandContext context = new CommandContext(System.in, System.out);
		context.setRenderer(new StreamRenderer(System.out));
		CreateCanvas createCanvas = new CreateCanvas(new String[]{"100", "100"}, context);
		createCanvas.execute();
		DrawRectangle command = new DrawRectangle(new String[]{"12", "34", "56", "78"}, context);
		Assert.assertNotNull(command);
	}

}
