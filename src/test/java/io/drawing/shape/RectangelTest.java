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
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);		
		new Rectangle(null, new Point(x, y));
	}

	@Test(expected = NullPointerException.class)
	public void whenLowerRightPointIsNullThenExceptionIsThrown() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);		
		new Rectangle(new Point(x, y), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenLowerRightIsNotRightToUpperLeftThenExceptionIsThrown() {
		
		Random r = new Random();
		int lx = r.nextInt(RANDOM_BOUND);
		int ly = r.nextInt(RANDOM_BOUND);
		int rx = lx - r.nextInt(lx);
		int ry = ly + r.nextInt(RANDOM_BOUND);
		logger.info("upperLeft point: ({}, {}), lowerRight point: ({}, {})", lx, ly, rx, ry);
		new Rectangle(new Point(lx,ly), new Point(rx,ry));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenLowerRightIsNotLowerThanUpperLeftThenExceptionIsThrown() {
		
		Random r = new Random();
		int lx = r.nextInt(RANDOM_BOUND);
		int ly = r.nextInt(RANDOM_BOUND);
		int rx = lx + r.nextInt(RANDOM_BOUND);
		int ry = ly - r.nextInt(ly);
		logger.info("upperLeft point: ({}, {}), lowerRight point: ({}, {})", lx, ly, rx, ry);
		new Rectangle(new Point(lx,ly), new Point(rx,ry));
	}


	@Test
	public void whenCorrectArgumentIsProvidedThenInstanceIsCreatedSuccessfully() {
		
		Random r = new Random();
		int lx = r.nextInt(RANDOM_BOUND);
		int ly = r.nextInt(RANDOM_BOUND);
		int rx = lx + r.nextInt(RANDOM_BOUND)+1;
		int ry = ly + r.nextInt(RANDOM_BOUND)+1;
		Rectangle rectangle = new Rectangle(new Point(lx,ly), new Point(rx, ry));
		logger.info("rectangle created: [{}]", rectangle);

		Assert.assertEquals(lx, rectangle.getUpperLeft().getX());
		Assert.assertEquals(ly, rectangle.getUpperLeft().getY());
		Assert.assertEquals(rx-lx+1, rectangle.getWidth());
		Assert.assertEquals(ry-ly+1, rectangle.getHeight());
	}
	
	@Test
	public void whenRectangleIsCreatedThenItsPathCanBeGotten() {

		Random r = new Random();
		int lx = r.nextInt(RANDOM_BOUND);
		int ly = r.nextInt(RANDOM_BOUND);
		int rx = lx + r.nextInt(RANDOM_BOUND)+1;
		int ry = ly + r.nextInt(RANDOM_BOUND)+1;
		Rectangle rectangle = new Rectangle(new Point(lx,ly), new Point(rx, ry));
		logger.info("rectangle created: [{}]", rectangle);
		
		Point[] path = rectangle.getPath();
		Assert.assertNotNull(path);
		
		int expectedLength = (rectangle.getWidth()+rectangle.getHeight())*2-4;
		Assert.assertEquals(expectedLength, path.length);
		
		int randomIndex = r.nextInt(expectedLength);
		logger.info("randomIndex: [{}]", randomIndex);
		Point randomPoint = path[randomIndex];
		logger.info("randomPoint: [{}]", randomPoint);
		Assert.assertNotNull(randomPoint);
		
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();
		int expectedX = lx;
		int expectedY = ly;
		if (randomIndex < width) {
			expectedX = lx+randomIndex;
			expectedY = ly;
		}else if (randomIndex>=width && randomIndex<width+(height-1)) {
			expectedX = lx+(width-1);
			expectedY = ly+(randomIndex-(width-1));
		}else if (randomIndex>=width+(height-1) && randomIndex<width+(height-1)+(width-1)) {
			expectedX = (lx+(width-1))-(randomIndex-(width-1)-(height-1));
			expectedY = ly+(height-1);
		}else {
			expectedX = lx;
			expectedY = (ly+(height-1)) - (randomIndex-(width-1)-(height-1)-(width-1));
		}
		Assert.assertEquals(expectedX, randomPoint.getX());
		Assert.assertEquals(expectedY, randomPoint.getY());
	}


}
