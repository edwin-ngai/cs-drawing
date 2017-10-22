/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import org.junit.Test;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class CommandContextTest {

	@Test(expected = NullPointerException.class)
	public void whenInputStreamIsNullThenExceptionIsThrown() {
		new CommandContext(null, System.out);
	}

	@Test(expected = NullPointerException.class)
	public void whenOutputStreamIsNullThenExceptionIsThrown() {
		new CommandContext(System.in, null);
	}

	@Test(expected = NullPointerException.class)
	public void whenCanvasIsNullThenExceptionIsThrown() {
		new CommandContext(System.in, System.out).setCanvas(null);
	}

	@Test(expected = NullPointerException.class)
	public void whenRendererIsNullThenExceptionIsThrown() {
		new CommandContext(System.in, System.out).setRenderer(null);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void whenInputIsNullThenExceptionIsThrown() throws InvalidCommandException{
		new CommandContext(System.in, System.out).createCommand(null);
	}
	
	@Test(expected = InvalidCommandException.class)
	public void wheCommandUnrecognizedThenExceptionIsThrown() throws InvalidCommandException{
		new CommandContext(System.in, System.out).createCommand("XXX");
	}

}
