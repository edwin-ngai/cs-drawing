/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import static io.drawing.util.TestUtils.RANDOM_BOUND;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class RectangelTest {
	
    private static final Logger logger = LoggerFactory.getLogger(RectangelTest.class);


	@Test(expected = NullPointerException.class)
	public void whenUpperLeftPointIsNullThenExceptionIsThrown() {
		
		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND)+1;
		int height = r.nextInt(RANDOM_BOUND)+1;
		new Rectangle(null, width, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenWidthIsNegativeThenExceptionIsThrown() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		int width = (r.nextInt(RANDOM_BOUND)+1)*-1;
		int height = r.nextInt(RANDOM_BOUND)+1;
		new Rectangle(new Point(x,y), width, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenWidthIsZeroThenExceptionIsThrown() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		int height = r.nextInt(RANDOM_BOUND)+1;
		new Rectangle(new Point(x,y), 0, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHeightIsNegativeThenExceptionIsThrown() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		int width = r.nextInt(RANDOM_BOUND)+1;
		int height = (r.nextInt(RANDOM_BOUND)+1)*-1;
		new Rectangle(new Point(x,y), width, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHeightIsZeroThenExceptionIsThrown() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		int width = r.nextInt(RANDOM_BOUND)+1;
		new Rectangle(new Point(x,y), width, 0);
	}
	
	@Test
	public void whenCorrectArgumentIsProvidedThenInstanceIsCreatedSuccessfully() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		int width = r.nextInt(RANDOM_BOUND)+1;
		int height = r.nextInt(RANDOM_BOUND)+1;
		Rectangle rectangle = new Rectangle(new Point(x,y), width, height);
		Assert.assertEquals(x, rectangle.getUpperLeft().getX());
		Assert.assertEquals(y, rectangle.getUpperLeft().getY());
		Assert.assertEquals(width, rectangle.getWidth());
		Assert.assertEquals(height, rectangle.getHeight());
	}
	
	@Test
	public void whenRectangleIsCreatedThenItsPathCanBeGotten() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		int width = r.nextInt(RANDOM_BOUND)+1;
		int height = r.nextInt(RANDOM_BOUND)+1;
		Rectangle rectangle = new Rectangle(new Point(x,y), width, height);
		logger.info("rectangle created: [{}]", rectangle);
		
		Point[] path = rectangle.getPath();
		Assert.assertNotNull(path);
		
		int expectedLength = (width+height)*2;
		Assert.assertEquals(expectedLength, path.length);
		
		int randomIndex = r.nextInt(expectedLength);
		logger.info("randomIndex: [{}]", randomIndex);
		Point randomPoint = path[randomIndex];
		logger.info("randomPoint created: [{}]", randomPoint);
		Assert.assertNotNull(randomPoint);
		
		int expectedX = x;
		int expectedY = y;
		if (randomIndex < width) {
			expectedX = x+randomIndex;
			expectedY = y;
		}else if (randomIndex>=width && randomIndex<width+height) {
			expectedX = x+width;
			expectedY = y+(randomIndex-width);
		}else if (randomIndex>=width+height && randomIndex<width*2+height) {
			expectedX = (x+width)-(randomIndex-width-height);
			expectedY = y+height;
		}else {
			expectedX = x;
			expectedY = (y+height) - (randomIndex-width*2-height);
		}
		Assert.assertEquals(expectedX, randomPoint.getX());
		Assert.assertEquals(expectedY, randomPoint.getY());
	}
	
//	@Test
//	public void loopTest() {
//		try {
//			int i = 0;
//			while (true) {
//				logger.info("test [{}]", i++);
//				whenRectangleIsCreatedThenItsPathCanBeGotten();
//				Thread.sleep(100);
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

}
