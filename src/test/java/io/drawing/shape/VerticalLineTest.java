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
public class VerticalLineTest {

    private static final Logger logger = LoggerFactory.getLogger(VerticalLineTest.class);

	@Test(expected = NullPointerException.class)
	public void whenStartPointIsNullThenExceptionIsThrown() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		new VerticalLine(null, new Point(x,y));
	}

	@Test(expected = NullPointerException.class)
	public void whenEndPointIsNullThenExceptionIsThrown() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		new VerticalLine(new Point(x,y), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenPointsAreNotVerticalThenExceptionIsThrown() {
		
		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = r.nextInt(RANDOM_BOUND);
		int y2 = y1 + r.nextInt(RANDOM_BOUND) + 1;
		while (x2 == x1) {
			x2 = r.nextInt(RANDOM_BOUND);
		}
		logger.info("start point: ({}, {}), end point: ({}, {})", x1, y1, x2, y2);
		new VerticalLine(new Point(x1,y1), new Point(x2,y2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenSameYCoordinatesThenExceptionIsThrown() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = r.nextInt(RANDOM_BOUND);
		int y2 = y1;
		logger.info("start point: ({}, {}), end point: ({}, {})", x1, y1, x2, y2);
		new VerticalLine(new Point(x1, y1), new Point(x2, y2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenStartPointLowerThanEndPointThenExceptionIsThrown() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1;
		int y2 = y1 - r.nextInt(y1);
		while (y2 == y1) {
			y2 = y1 - r.nextInt(y1);
		}
		logger.info("start point: ({}, {}), end point: ({}, {})", x1, y1, x2, y2);
		new VerticalLine(new Point(x1, y1), new Point(x2, y2));
	}

	
	@Test
	public void whenGivenPointsAreVerticalThenInstanceCreatedSuccessfully() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1;
		int y2 = y1 + r.nextInt(RANDOM_BOUND) + 1;
		VerticalLine line = new VerticalLine(new Point(x1,y1), new Point(x2,y2));
		logger.info("line created: [{}]", line);
		
		Assert.assertEquals(x1, line.getStart().getX());
		Assert.assertEquals(y1, line.getStart().getY());
		Assert.assertEquals(x2, line.getEnd().getX());
		Assert.assertEquals(y2, line.getEnd().getY());
	}

	@Test
	public void whenLineIsCreatedThenItsPathCanBeGotten() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1;
		int y2 = y1 + r.nextInt(RANDOM_BOUND) + 1;
		VerticalLine line = new VerticalLine(new Point(x1, y1), new Point(x2, y2));
		logger.info("line created: [{}]", line);
		
		Point[] path = line.getPath();
		Assert.assertNotNull(path);
		
		int expectedLength = (y2-y1) + 1;
		Assert.assertEquals(expectedLength, path.length);
		
		int randomIndex = r.nextInt(expectedLength);
		Point randomPoint = path[randomIndex];
		Assert.assertNotNull(randomPoint);
		
		int expectedY = y1+randomIndex;
		Assert.assertEquals(expectedY, randomPoint.getY());
		
	}

}
