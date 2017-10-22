/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.renderer;

import static io.drawing.util.TestUtils.RANDOM_BOUND;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.drawing.shape.Canvas;
import io.drawing.shape.HorizontalLine;
import io.drawing.shape.Point;
import io.drawing.shape.Rectangle;
import io.drawing.shape.Shape;
import io.drawing.shape.VerticalLine;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class StreamRendererTest {

    private static final Logger logger = LoggerFactory.getLogger(StreamRendererTest.class);

    @Test
	public void whenCalledThenCanvasWithoutShapeWillBeOutputToConsole() {

    	Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND)+2;
		int cHeight = r.nextInt(RANDOM_BOUND)+2;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);
		new StreamRenderer(System.out).render(canvas);
	}

    @Test
	public void whenCalledThenCanvasWithSpecifiedShapeWillBeOutputToConsole() {
    
    	int cWidth = 98;
		int cHeight = 91;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		Rectangle r1 = new Rectangle(new Point(21, 1), new Point(77, 12));
		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));
		new StreamRenderer(System.out).render(canvas);
    }
    @Test
	public void whenCalledThenCanvasWithRandomShapeWillBeOutputToConsole() {

    	Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND)+2;
		int cHeight = r.nextInt(RANDOM_BOUND)+2;
		Canvas canvas = new Canvas(cWidth, cHeight);
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
		
		new StreamRenderer(System.out).render(canvas);
		
	}
    
    @Test
   	public void whenCalledThenFilledCanvasWithSpecifiedShapeWillBeOutputToConsole() {
    	
    	int cWidth = 98;
		int cHeight = 91;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		Rectangle r1 = new Rectangle(new Point(21, 1), new Point(77, 12));
		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));

		canvas.bucketFill(67, 0, 't');
		new StreamRenderer(System.out).render(canvas);
    }
    
    @Test
	public void whenCalledThenFilledCanvasWithRandomShapeWillBeOutputToConsole() {

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
		logger.info("startPoint: [{},{}]. Its position is [{}]", startPointX, startPointY);
		int i = r.nextInt(94) + 33;
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char) i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);
		
		new StreamRenderer(System.out).render(canvas);
		
	}
    
    @Test
	public void whenCalledThenFilledCanvasWithMultipleShapesdWillBeOutputToConsole() {

		Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND) + 2;
		int cHeight = r.nextInt(RANDOM_BOUND) + 2;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);

		int hX2 = r.nextInt(cWidth-1)+1;
		int hY2 = r.nextInt(cHeight);
		int hX1 = r.nextInt(hX2);
		int hY1 = hY2;
		Shape randomShape1 = new HorizontalLine(new Point(hX1, hY1), new Point(hX2, hY2));
		logger.info("randomShape1 created: [{}]", randomShape1);
		Assert.assertTrue(canvas.drawShape(randomShape1));

		int vX2 = r.nextInt(cWidth);
		int vY2 = r.nextInt(cHeight-1)+1;
		int vX1 = vX2;
		int vY1 = r.nextInt(vY2);
		Shape randomShape2 = new VerticalLine(new Point(vX1, vY1), new Point(vX2, vY2));
		logger.info("randomShape2 created: [{}]", randomShape2);
		Assert.assertTrue(canvas.drawShape(randomShape2));


		int r1RX = r.nextInt(cWidth-1)+1;
		int r1RY = r.nextInt(cHeight-1)+1;
		int r1LX = r.nextInt(r1RX);
		int r1LY = r.nextInt(r1RY);
		Shape randomShape3 = new Rectangle(new Point(r1LX, r1LY), new Point(r1RX, r1RY));
		logger.info("randomShape3 created: [{}]", randomShape3);
		Assert.assertTrue(canvas.drawShape(randomShape3));
		
		int startPointX = r.nextInt(cWidth);
		int startPointY = r.nextInt(cHeight);
		logger.info("startPoint: [{},{}]", startPointX, startPointY);
		
		int i = r.nextInt(94) + 33;
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char) i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);
		
		new StreamRenderer(System.out).render(canvas);
		
	}
}
