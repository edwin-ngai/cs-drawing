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
	public void whenWidthIsNegativeThenExceptionIsThrown() {

		Random r = new Random();
		int width = (r.nextInt(RANDOM_BOUND)+1)*-1;
		int height = r.nextInt(RANDOM_BOUND)+1;
		new Canvas(width, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenWidthIsZeroThenExceptionIsThrown() {

		Random r = new Random();
		int height = r.nextInt(RANDOM_BOUND)+1;
		new Canvas(0, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHeightIsNegativeThenExceptionIsThrown() {

		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND)+1;
		int height = (r.nextInt(RANDOM_BOUND)+1)*-1;
		new Canvas(width, height);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenHeightIsZeroThenExceptionIsThrown() {

		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND)+1;
		new Canvas(width, 0);
	}
	
	@Test
	public void whenCanvasIsCreatedThenItsBackgroundIsSpecified() {
		
		Random r = new Random();
		int width = r.nextInt(RANDOM_BOUND)+1;
		int height = r.nextInt(RANDOM_BOUND)+1;
		char color = (char)r.nextInt(RANDOM_BOUND);
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
		int cWidth = r.nextInt(RANDOM_BOUND)+1;
		int cHeight = r.nextInt(RANDOM_BOUND)+1;
		Canvas canvas = new Canvas(cWidth, cHeight);
		char[][] colorData = canvas.getColorData();
		logger.info("canvas created: [{}]", canvas);
		
		int randomOutsideX = r.nextInt(RANDOM_BOUND)+cWidth+1;
		int randomOutsideY = r.nextInt(RANDOM_BOUND)+cHeight+1;
		Point randomOutsidePoint = new Point[]{new Point(randomOutsideX, r.nextInt(RANDOM_BOUND)),
				new Point(r.nextInt(RANDOM_BOUND), randomOutsideY)}[r.nextInt(2)];
		logger.info("randomOutsidePoint created: [{}]", randomOutsidePoint);
		
		Shape randomShape = null;
		switch (r.nextInt(3)) {
		case 0:
			randomShape = new HorizontalLine(randomOutsidePoint, new Point(r.nextInt(RANDOM_BOUND), randomOutsidePoint.getY()));
			break;
		case 1:
			randomShape = new VerticalLine(randomOutsidePoint, new Point(randomOutsidePoint.getX(), r.nextInt(RANDOM_BOUND)));
			break;
		case 2:
			randomShape = new Rectangle(randomOutsidePoint, r.nextInt(RANDOM_BOUND)+1, r.nextInt(RANDOM_BOUND)+1);
			break;
		}
		logger.info("randomShape created: [{}]", randomShape);
		Assert.assertFalse(canvas.drawShape(randomShape));
		Assert.assertArrayEquals(colorData, canvas.getColorData());
	}

	@Test
	public void whenGivenValidShapeThenDrawShapeReturnTrueAndColorDataChanges() {

		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND)+1;
		int cHeight = r.nextInt(RANDOM_BOUND)+1;
		Canvas canvas = new Canvas(cWidth, cHeight);
		char[][] colorData = canvas.getColorData();
		logger.info("canvas created: [{}]", canvas);

		Shape randomShape = null;
		switch (r.nextInt(3)) {
		case 0:
			int hX1 = r.nextInt(cWidth);
			int hY1 = r.nextInt(cHeight);
			int hX2 = r.nextInt(cWidth);
			int hY2 = hY1;
			randomShape = new HorizontalLine(new Point(hX1, hY1), new Point(hX2, hY2));
			break;
		case 1:
			int vX1 = r.nextInt(cWidth);
			int vY1 = r.nextInt(cHeight);
			int vX2 = vX1;
			int vY2 = r.nextInt(cHeight);
			randomShape = new VerticalLine(new Point(vX1, vY1), new Point(vX2, vY2));
			break;
		case 2:
			int rX = r.nextInt(cWidth);
			int rY = r.nextInt(cHeight);
			randomShape = new Rectangle(new Point(rX, rY), r.nextInt(cWidth-rX)+1, r.nextInt(cHeight-rY)+1);
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
		int cWidth = r.nextInt(RANDOM_BOUND)+1;
		int cHeight = r.nextInt(RANDOM_BOUND)+1;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		int startPointX = r.nextInt(cWidth);
		int startPointY = r.nextInt(cHeight);
		logger.info("startPoint: [{},{}].");

		int i = r.nextInt(RANDOM_BOUND);
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char)i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);

		int pointToCheckX = r.nextInt(cWidth);
		int pointToCheckY =  r.nextInt(cHeight);
		logger.info("pointToCheck: [{},{}].", pointToCheckX, pointToCheckY);
		char[][] color = canvas.getColorData();
		char colorToCheck = color[pointToCheckX][pointToCheckY];
		Assert.assertEquals(String.valueOf(replacementColor), String.valueOf(colorToCheck));
	}
	
	@Test
	public void whenBukcetFillWithRandomPointThenPointsConnectedAreFilled() {
		
		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND)+1;
		int cHeight = r.nextInt(RANDOM_BOUND)+1;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);
		
		int r1X = r.nextInt(cWidth);
		int r1Y = r.nextInt(cHeight);
		while (r1X == cWidth) {
			r1X = r.nextInt(cWidth);
		}
		while (r1Y == cHeight) {
			r1Y = r.nextInt(cHeight);
		}
		int r1Width = r.nextInt(cWidth-r1X)+1;
		int r1Height =  r.nextInt(cHeight-r1Y)+1;
		Rectangle r1 = new Rectangle(new Point(r1X, r1Y), r1Width, r1Height);
		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));
	
		int startPointX = r.nextInt(cWidth);
		int startPointY = r.nextInt(cHeight);
		//0 means on the path of rectangle
		int startPointPosition = 0;
		if (startPointX>r1X && startPointX<r1X+r1Width && startPointY>r1Y && startPointY<r1Y+r1Height) {
			//-1 means inside rectangle 
			startPointPosition = -1;
		}else if (r1Width == cWidth) {
			if (startPointY<r1Y) {
				startPointPosition = 1;
			}else if (startPointY>r1Y+r1Height){
				startPointPosition = 2;
			}
		}else if (r1Height == cHeight) {
			if (startPointX<r1X) {
				startPointPosition = 1;
			}else if (startPointX>r1X+r1Width){
				startPointPosition = 2;
			}
		}else if (startPointX<r1X || startPointX>r1X+r1Width|| startPointY<r1Y || startPointY>r1Y+r1Height) {
			startPointPosition = 1;
		}
		logger.info("startPoint: [{},{}]. Its position is [{}]", startPointX, startPointY, startPointPosition);

		int i = r.nextInt(94)+33;
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char)i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);
		
		int pointToCheckX = r.nextInt(cWidth);
		int pointToCheckY =  r.nextInt(cHeight);
		//0 means on the path of rectangle
		int pointToCheckPosition = 0;
		if (pointToCheckX>r1X && pointToCheckX<r1X+r1Width && pointToCheckY>r1Y && pointToCheckY<r1Y+r1Height) {
			//-1 means inside rectangle 
			pointToCheckPosition = -1;
		}else if (r1Width == cWidth) {
			if (pointToCheckY<r1Y) {
				pointToCheckPosition = 1;
			}else if (pointToCheckY>r1Y+r1Height){
				pointToCheckPosition = 2;
			}
		}else if (r1Height == cHeight) {
			if (pointToCheckX<r1X) {
				pointToCheckPosition = 1;
			}else if (pointToCheckX>r1X+r1Width){
				pointToCheckPosition = 2;
			}
		}else if (pointToCheckX<r1X || pointToCheckX>r1X+r1Width|| pointToCheckY<r1Y || pointToCheckY>r1Y+r1Height) {
			pointToCheckPosition = 1;
		}
		logger.info("pointToCheck: [{},{}]. Its position is [{}]", pointToCheckX, pointToCheckY, pointToCheckPosition);
		char[][] color = canvas.getColorData();
		char colorToCheck = color[pointToCheckX][pointToCheckY];
		
		char expectedColor = canvas.getBackgroundColor();
		if (pointToCheckPosition==startPointPosition) {
			expectedColor = replacementColor;
		}else if (pointToCheckPosition==0) {
			expectedColor = r1.getUpperLeft().getColor();
		}
		Assert.assertEquals(String.valueOf(expectedColor), String.valueOf(colorToCheck));
	}

	@Test
	public void whenBukcetFillWithSpecifiedPointThenPointsConnectedAreFilled() {
		
		int cWidth = 80;
		int cHeight = 4;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);
		
		Rectangle r1 = new Rectangle(new Point(16, 0), 31, 4);
		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));
	
		canvas.bucketFill(37, 2, '=');
		char[][] color = canvas.getColorData();

		Point pointToCheck = new Point(40, 0);
		logger.info("pointToCheck created: [{}]", pointToCheck);
		char colorToCheck = color[pointToCheck.getX()][pointToCheck.getY()];
		char expectedColor = 'x';
		Assert.assertEquals(String.valueOf(expectedColor), String.valueOf(colorToCheck));
	}
	
	
//	@Test
//	public void loopTest() {
//		try {
//			int i = 0;
//			while (true) {
//				logger.info("test [{}]", i++);
//				whenGivenValidShapeThenDrawShapeReturnTrueAndColorDataChanges();
//				Thread.sleep(100);
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	


}
