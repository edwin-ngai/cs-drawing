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
public class ConsoleRendererTest {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleRendererTest.class);

    @Test
	public void whenCalledThenCanvasWithoutShapeWillBeOutputToConsole() {

    	Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND)+1;
		int cHeight = r.nextInt(RANDOM_BOUND)+1;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);
		new ConsoleRenderer().render(canvas);
	}

    @Test
	public void whenCalledThenCanvasWithShapeWillBeOutputToConsole() {

    	Random r = new Random();
		int cWidth = r.nextInt(RANDOM_BOUND)+1;
		int cHeight = r.nextInt(RANDOM_BOUND)+1;
		Canvas canvas = new Canvas(cWidth, cHeight);
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
		
		new ConsoleRenderer().render(canvas);
		
	}
    
    @Test
	public void whenCalledThenCanvasFilledWillBeOutputToConsole() {

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
		logger.info("startPoint: [{},{}]", startPointX, startPointY);
		
		int i = r.nextInt(94)+33;
		logger.info("replacementColor in int: [{}]", i);
		char replacementColor = (char)i;
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);
		
		new ConsoleRenderer().render(canvas);
		
	}
    
    @Test
	public void whenCalledThenCanvasWithMultipleShapesdWillBeOutputToConsole() {

		int cWidth = 19;
		int cHeight = 3;
		Canvas canvas = new Canvas(cWidth, cHeight);
		logger.info("canvas created: [{}]", canvas);
		
		int l1x1 = 0;
		int l1y1 = 1;
		int l1x2 = 5;
		int l1y2 = 1;
		HorizontalLine l1 = new HorizontalLine(new Point(l1x1,l1y1), new Point(l1x2, l1y2));
		Assert.assertTrue(canvas.drawShape(l1));
		
		int l2x1 = 5;
		int l2y1 = 2;
		int l2x2 = 5;
		int l2y2 = 3;
		VerticalLine l2 = new VerticalLine(new Point(l2x1,l2y1), new Point(l2x2, l2y2));
		Assert.assertTrue(canvas.drawShape(l2));
		
		int r1X = 13;
		int r1Y = 0;
		int r1Width = 4;
		int r1Height =  2;
		Rectangle r1 = new Rectangle(new Point(r1X, r1Y), r1Width, r1Height);
		logger.info("rectangle created: [{}]", r1);
		Assert.assertTrue(canvas.drawShape(r1));
		
		int startPointX = 9;
		int startPointY = 2;
		logger.info("startPoint: [{},{}]", startPointX, startPointY);
		
		char replacementColor = 'o';
		logger.info("replacementColor: [{}]", replacementColor);
		canvas.bucketFill(startPointX, startPointY, replacementColor);
		
		new ConsoleRenderer().render(canvas);
		
	}
}
