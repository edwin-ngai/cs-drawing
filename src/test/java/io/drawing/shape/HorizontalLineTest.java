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
		new HorizontalLine(null, new Point(x,y));
	}

	@Test(expected = NullPointerException.class)
	public void whenEndPointIsNullThenExceptionIsThrown() {
		
		Random r = new Random();
		int x = r.nextInt(RANDOM_BOUND);
		int y = r.nextInt(RANDOM_BOUND);
		new HorizontalLine(new Point(x,y), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenGivenPointsAreNotHorizontalThenExceptionIsThrown() {
		
		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = r.nextInt(RANDOM_BOUND);
		int y2 = r.nextInt(RANDOM_BOUND);
		new HorizontalLine(new Point(x1,y1), new Point(x2,y2));
	}

	@Test
	public void whenGivenPointsAreHorizontalThenInstanceCreatedSuccessfully() {
		
		Random r = new Random();
		int x1 = r.nextInt(RANDOM_BOUND);
		int y1 = r.nextInt(RANDOM_BOUND);
		int x2 = r.nextInt(RANDOM_BOUND);
		int y2 = y1;
		HorizontalLine line = new HorizontalLine(new Point(x1,y1), new Point(x2,y2));
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
		int x2 = r.nextInt(RANDOM_BOUND);
		int y2 = y1;
		Point start = new Point(x1, y1);
		Point end = new Point(x2, y2);
		HorizontalLine line = new HorizontalLine(start, end);
		logger.info("line created: [{}]", line);
		
		Point[] path = line.getPath();
		Assert.assertNotNull(path);
		
		int direction = x2>x1?1:-1;
		int expectedLength = (x2-x1)*direction + 1;
		Assert.assertEquals(expectedLength, path.length);
		
		int randomIndex = r.nextInt(expectedLength);
		Point randomPoint = path[randomIndex];
		Assert.assertNotNull(randomPoint);
		
		int expectedX = x1+randomIndex*direction;
		Assert.assertEquals(expectedX, randomPoint.getX());
	}
	
//	@Test
//	public void loopTest() {
//		try {
//			int i = 0;
//			while (true) {
//				logger.info("test [{}]", i++);
//				whenLineIsCreatedThenItsPathCanBeGotten();
//				Thread.sleep(100);
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
}
