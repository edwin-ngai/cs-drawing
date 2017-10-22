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
public class HorizontalLineTest {

	private static final Logger logger = LoggerFactory.getLogger(HorizontalLineTest.class);

	@Test(expected = NullPointerException.class)
	public void whenStartPointIsNullThenExceptionIsThrown() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		new HorizontalLine(null, new Point(x, y));
	}

	@Test(expected = NullPointerException.class)
	public void whenEndPointIsNullThenExceptionIsThrown() {

		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		new HorizontalLine(new Point(x, y), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenPointsAreNotHorizontalThenExceptionIsThrown() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1 + r.nextInt(RANDOM_BOUND) + 1;
		int y2 = r.nextInt(RANDOM_BOUND);
		while (y2 == y1) {
			y2 = r.nextInt(RANDOM_BOUND);
		}
		logger.info("start point: ({}, {}), end point: ({}, {})", x1, y1, x2, y2);
		new HorizontalLine(new Point(x1, y1), new Point(x2, y2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenSameXCoordinatesThenExceptionIsThrown() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1;
		int y2 = r.nextInt(RANDOM_BOUND);
		logger.info("start point: ({}, {}), end point: ({}, {})", x1, y1, x2, y2);
		new HorizontalLine(new Point(x1, y1), new Point(x2, y2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenStartPointRightToEndPointThenExceptionIsThrown() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND) + 1;
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1 - r.nextInt(x1);
		while (x2 == x1) {
			x2 = x1 - r.nextInt(x1);
		}
		int y2 = y1;
		logger.info("start point: ({}, {}), end point: ({}, {})", x1, y1, x2, y2);
		new HorizontalLine(new Point(x1, y1), new Point(x2, y2));
	}

	@Test
	public void whenGivenPointsAreHorizontalThenInstanceCreatedSuccessfully() {

		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = x1 + r.nextInt(RANDOM_BOUND) + 1;
		int y2 = y1;
		HorizontalLine line = new HorizontalLine(new Point(x1, y1), new Point(x2, y2));
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
		int x2 = x1 + r.nextInt(RANDOM_BOUND) + 1;
		int y2 = y1;
		HorizontalLine line = new HorizontalLine(new Point(x1, y1), new Point(x2, y2));
		logger.info("line created: [{}]", line);

		Point[] path = line.getPath();
		Assert.assertNotNull(path);

		int expectedLength = (x2 - x1) + 1;
		Assert.assertEquals(expectedLength, path.length);

		int randomIndex = r.nextInt(expectedLength);
		Point randomPoint = path[randomIndex];
		Assert.assertNotNull(randomPoint);

		int expectedX = x1 + randomIndex;
		Assert.assertEquals(expectedX, randomPoint.getX());
	}

}
