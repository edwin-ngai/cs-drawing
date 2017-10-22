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
public class BucketFillTest {

	@Test(expected = NullPointerException.class)
	public void whenArgumentIsNullThenExceptionIsThrown() {
		new BucketFill(null, new CommandContext(System.in, System.out));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentLengthLessThan3ThenExceptionIsThrown() {
		new BucketFill(new String[]{"XXX", "YYY"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentAreNotNumericThenExceptionIsThrown() {
		new BucketFill(new String[]{"111", "XXX", "YYY"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentOutOfRangeThenExceptionIsThrown() {
		new BucketFill(new String[]{"9999", "8765", "O"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenColorIsNotCharThenExceptionIsThrown() {
		new BucketFill(new String[]{"123", "345", "XXX"}, new CommandContext(System.in, System.out));
	}
	
	@Test
	public void whenArgumentCorrectThenInstanceIsCreatedSuccessfully() throws CommandExecutionException{
		CommandContext context = new CommandContext(System.in, System.out);
		context.setRenderer(new StreamRenderer(System.out));
		CreateCanvas createCanvas = new CreateCanvas(new String[]{"100", "100"}, context);
		createCanvas.execute();
		BucketFill command = new BucketFill(new String[]{"12", "34", "o"}, context);
		Assert.assertNotNull(command);
	}

}
