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
public class CanvasTest {

	private static final Logger logger = LoggerFactory.getLogger(CanvasTest.class);

	@Test(expected = IllegalArgumentException.class)
	public void whenWidthIsLessThan2ThenExceptionIsThrown() {

		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND) * -1 + 1;
		int height = r.nextInt(RANDOM_BOUND) + 2;
		logger.info("[width, height]: [{}, {}]", width, height);
		new Canvas(width, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHeightIsLessThan2ThenExceptionIsThrown() {

		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND) + 2;
		int height = r.nextInt(RANDOM_BOUND) * -1 + 1;
		logger.info("[width, height]: [{}, {}]", width, height);
		new Canvas(width, height);
	}

	@Test
	public void whenCanvasIsCreatedThenItsBackgroundIsSpecified() {

		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND) + 2;
		int height = r.nextInt(RANDOM_BOUND) + 2;
		char color = (char) r.nextInt(RANDOM_BOUND);
		Canvas canvas = new Canvas(width, height, color);
		logger.info("canvas created: [{}]", canvas);

		char[][] colorData = canvas.getColorData();
		Assert.assertNotNull(colorData);
		Assert.assertEquals(width, colorData.length);
		Assert.assertEquals(height, colorData[0].length);

		int randomX = r.nextInt(width);
		int randomY = r.nextInt(height);
		Assert.assertEquals(String.valueOf(color), String.valueOf(colorData[randomX][randomY]));
	}

	@Test
	public void whenGivenShapeOutsideCanvasThenDrawShapeReturnsFalseAndColorDataIsUnchanged() {

		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND) + 2;
		int cHeight = r.nextInt(RANDOM_BOUND) + 2;
		Canvas canvas = new Canvas(cWidth, cHeight);
		char[][] colorData = canvas.getColorData();
		logger.info("canvas created: [{}]", canvas);

		int randomOutsideX = r.nextInt(RANDOM_BOUND) + cWidth;
		int randomOutsideY = r.nextInt(RANDOM_BOUND) + cHeight;

		Point[] randomOutsidePoints = new Point[] { new Point(randomOutsideX, r.nextInt(RANDOM_BOUND)),
				new Point(r.nextInt(RANDOM_BOUND), randomOutsideY), new Point(randomOutsideX, randomOutsideY) };
		Point randomOutsidePoint = randomOutsidePoints[r.nextInt(3)];
		logger.info("randomOutsidePoint created: [{}]", randomOutsidePoint);

		Shape randomShape = null;
		while (randomShape == null) {
			switch (r.nextInt(6)) {
			case 0:
				randomShape = new HorizontalLine(randomOutsidePoint,
						new Point(r.nextInt(RANDOM_BOUND) + randomOutsidePoint.getX() + 1, randomOutsidePoint.getY()));
				break;
			case 1:
				if (randomOutsidePoint.getX() != 0) {
					randomShape = new HorizontalLine(
							new Point(r.nextInt(randomOutsidePoint.getX()), randomOutsidePoint.getY()),
							randomOutsidePoint);
				}
				break;
			case 2:
				randomShape = new VerticalLine(randomOutsidePoint,
						new Point(randomOutsidePoint.getX(), r.nextInt(RANDOM_BOUND) + randomOutsidePoint.getY() + 1));
				break;
			case 3:
				if (randomOutsidePoint.getY() != 0) {
					randomShape = new VerticalLine(
							new Point(randomOutsidePoint.getX(), r.nextInt(randomOutsidePoint.getY())),
							randomOutsidePoint);
				}
				break;
			case 4:
				int rx = randomOutsidePoint.getX() + r.nextInt(RANDOM_BOUND) + 1;
				int ry = randomOutsidePoint.getY() + r.nextInt(RANDOM_BOUND) + 1;
				randomShape = new Rectangle(randomOutsidePoint, new Point(rx, ry));
				break;
			case 5:
				randomOutsidePoint = randomOutsidePoints[2];
				int lx = r.nextInt(cWidth);
				int ly = r.nextInt(cHeight);
				randomShape = new Rectangle(new Point(lx, ly), randomOutsidePoint);
				break;
			}
		}
		logger.info("randomShape created: [{}]", randomShape);
		Assert.assertFalse(canvas.drawShape(randomShape));
		Assert.assertArrayEquals(colorData, canvas.getColorData());
	}

	@Test
	public void whenGivenValidShapeThenDrawShapeReturnTrueAndColorDataChanges() {

		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND) + 2;
		int cHeight = r.nextInt(RANDOM_BOUND) + 2;
		Canvas canvas = new Canvas(cWidth, cHeight);
		char[][] colorData = canvas.getColorData();
		logger.info("canvas created: [{}]", canvas);

		Shape randomShape = null;
		switch (r.nextInt(3)) {
		case 0:
			int hX2 = r.nextInt(cWidth-1)+1;
			int hY2 = r.nextInt(cHeight);
			int hX1 = r.nextInt(hX2);
			int hY1 = hY2;
			randomShape = new HorizontalLine(new Point(hX1, hY1), new Point(hX2, hY2));
			break;
		case 1:
			int vX2 = r.nextInt(cWidth);
			int vY2 = r.nextInt(cHeight-1)+1;
			int vX1 = vX2;
			int vY1 = r.nextInt(vY2);
			randomShape = new VerticalLine(new Point(vX1, vY1), new Point(vX2, vY2));
			break;
		case 2:
			int r1RX = r.nextInt(cWidth-1)+1;
			int r1RY = r.nextInt(cHeight-1)+1;
			int r1LX = r.nextInt(r1RX);
			int r1LY = r.nextInt(r1RY);
			randomShape = new Rectangle(new Point(r1LX, r1LY), new Point(r1RX, r1RY));
			break;
		}
		logger.info("randomShape created: [{}]", randomShape);
		Assert.assertTrue(canvas.drawShape(randomShape));

		char[][] updatedColorData = canvas.getColorData();
		Assert.assertFalse(colorData.equals(updatedColorData));

		Point[] shapePath = randomShape.getPath();
		Point randomPointOnPath = shapePath[r.nextInt(shapePath.length)];
		logger.info("randomPointOnPath is: [{}]", randomPointOnPath);
		char randomPointColor = updatedColorData[randomPointOnPath.getX()][randomPointOnPath.getY()];
		char expectedColor = randomPointOnPath.getColor();
		Assert.assertEquals(String.valueOf(expectedColor), String.valueOf(randomPointColor));
	}

	@Test
	public void whenBuckFillWithoutShapeThenAllPointsAreFilled() {

		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND) + 2;
		int cHeight = r.nextInt(RANDOM_BOUND) + 2;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		int startPointX = r.nextInt(cWidth);
		int startPointY = r.nextInt(cHeight);
		logger.info("startPoint: [{},{}].");

		int i = r.nextInt(94) + 33;
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char) i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);

		int pointToCheckX = r.nextInt(cWidth);
		int pointToCheckY = r.nextInt(cHeight);
		logger.info("pointToCheck: [{},{}].", pointToCheckX, pointToCheckY);
		char[][] color = canvas.getColorData();
		char colorToCheck = color[pointToCheckX][pointToCheckY];
		Assert.assertEquals(String.valueOf(replacementColor), String.valueOf(colorToCheck));
	}

	@Test
	public void whenBukcetFillWithRandomPointThenPointsConnectedAreFilled() {

		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND) + 2;
		int cHeight = r.nextInt(RANDOM_BOUND) + 2;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		int r1RX = r.nextInt(cWidth-1)+1;
		int r1RY = r.nextInt(cHeight-1)+1;
		int r1LX = r.nextInt(r1RX);
		int r1LY = r.nextInt(r1RY);
		Rectangle r1 = new Rectangle(new Point(r1LX, r1LY), new Point(r1RX, r1RY));

		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));

		int startPointX = r.nextInt(cWidth);
		int startPointY = r.nextInt(cHeight);
		// 0 means on the path of rectangle
		int startPointPosition = 0;
		if (startPointX > r1LX && startPointX < r1RX && startPointY > r1LY && startPointY < r1RY) {
			// -1 means inside rectangle
			startPointPosition = -1;
		} else if (r1RX - r1LX == cWidth - 1) {
			if (startPointY < r1LY) {
				startPointPosition = 1;
			} else if (startPointY > r1RY) {
				startPointPosition = 2;
			}
		} else if (r1RY - r1LY == cHeight - 1) {
			if (startPointX < r1LX) {
				startPointPosition = 1;
			} else if (startPointX > r1RX) {
				startPointPosition = 2;
			}
		} else if (startPointX < r1LX || startPointX > r1RX || startPointY < r1LY || startPointY > r1RY) {
			startPointPosition = 1;
		}
		logger.info("startPoint: [{},{}]. Its position is [{}]", startPointX, startPointY, startPointPosition);

		int i = r.nextInt(94) + 33;
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char) i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);

		int pointToCheckX = r.nextInt(cWidth);
		int pointToCheckY = r.nextInt(cHeight);
		// 0 means on the path of rectangle
		int pointToCheckPosition = 0;
		if (pointToCheckX > r1LX && pointToCheckX < r1RX && pointToCheckY > r1LY && pointToCheckY < r1RY) {
			// -1 means inside rectangle
			pointToCheckPosition = -1;
		} else if (r1RX - r1LX == cWidth - 1) {
			if (pointToCheckY < r1LY) {
				pointToCheckPosition = 1;
			} else if (pointToCheckY > r1RY) {
				pointToCheckPosition = 2;
			}
		} else if (r1RY - r1LY == cHeight - 1) {
			if (pointToCheckX < r1LX) {
				pointToCheckPosition = 1;
			} else if (pointToCheckX > r1RX) {
				pointToCheckPosition = 2;
			}
		} else if (pointToCheckX < r1LX || pointToCheckX > r1RX || pointToCheckY < r1LY || pointToCheckY > r1RY) {
			pointToCheckPosition = 1;
		}
		logger.info("pointToCheck: [{},{}]. Its position is [{}]", pointToCheckX, pointToCheckY, pointToCheckPosition);
		char[][] color = canvas.getColorData();
		char colorToCheck = color[pointToCheckX][pointToCheckY];

		char expectedColor = canvas.getBackgroundColor();
		if (pointToCheckPosition == startPointPosition) {
			expectedColor = replacementColor;
		} else if (pointToCheckPosition == 0) {
			expectedColor = r1.getUpperLeft().getColor();
		}
		Assert.assertEquals(String.valueOf(expectedColor), String.valueOf(colorToCheck));
	}

	@Test
	public void whenBukcetFillWithSpecifiedPointThenPointsConnectedAreFilled() {

		int cWidth = 98;
		int cHeight = 91;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		Rectangle r1 = new Rectangle(new Point(21, 1), new Point(77, 12));
		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));

		canvas.bucketFill(67, 0, 't');
		char[][] color = canvas.getColorData();

		Point pointToCheck = new Point(26, 81);
		logger.info("pointToCheck created: [{}]", pointToCheck);
		char colorToCheck = color[pointToCheck.getX()][pointToCheck.getY()];
		char expectedColor = 't';
		Assert.assertEquals(String.valueOf(expectedColor), String.valueOf(colorToCheck));
	}

}
