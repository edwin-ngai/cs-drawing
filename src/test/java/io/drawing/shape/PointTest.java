/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import static io.drawing.util.Constant.DEFAULT_COLOR;

import static io.drawing.util.TestUtils.*;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class PointTest {

	@Test(expected = IllegalArgumentException.class)
	public void whenXIsNegativeThenExceptionIsThrown() {
		
		Random r = new Random();
		int x = (r.nextInt(RANDOM_BOUND)+1)*-1;
		int y = r.nextInt(RANDOM_BOUND);
		new Point(x, y);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenYIsNegativeThenExceptionIsThrown() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = (r.nextInt(RANDOM_BOUND)+1)*-1;
		new Point(x, y);
	}

	@Test
	public void whenNoColorIsProvidedThenDefaultColorIsUsed() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		Point point = new Point(x, y);
		Assert.assertEquals(String.valueOf( DEFAULT_COLOR), String.valueOf(point.getColor()));
	}
	
	@Test
	public void whenCorrectArgumentIsProvidedThenInstanceIsCreatedSuccessfully() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		char color = (char)r.nextInt(RANDOM_BOUND);
		Point point = new Point(x, y, color);
		Assert.assertEquals(x, point.getX());
		Assert.assertEquals(y, point.getY());
		Assert.assertEquals(String.valueOf(color), String.valueOf(point.getColor()));
	}
	
}
