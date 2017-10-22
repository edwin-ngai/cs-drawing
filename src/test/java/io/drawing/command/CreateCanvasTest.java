/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class CreateCanvasTest {

	@Test(expected = NullPointerException.class)
	public void whenArgumentIsNullThenExceptionIsThrown() {
		new CreateCanvas(null, new CommandContext(System.in, System.out));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentLengthLessThan2ThenExceptionIsThrown() {
		new CreateCanvas(new String[]{"123"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentAreNotNumericThenExceptionIsThrown() {
		new CreateCanvas(new String[]{"123", "YYY"}, new CommandContext(System.in, System.out));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenArgumentOutOfRangeThenExceptionIsThrown() {
		new CreateCanvas(new String[]{"9999", "8765"}, new CommandContext(System.in, System.out));
	}

	@Test
	public void whenArgumentCorrectThenInstanceCreateSuccessfully() {
		CreateCanvas command = new CreateCanvas(new String[]{"123", "456"}, new CommandContext(System.in, System.out));
		Assert.assertNotNull(command);
	}
}
