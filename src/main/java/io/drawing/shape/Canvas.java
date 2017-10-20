/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import static io.drawing.util.Constant.TRANSPARENT_COLOR;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.apache.commons.lang3.Validate;

/**
 * Canvas for drawing, the coordinates of this Canvas is start from 1
 * @author Edwin Ngai (edwin.ngai@mail.com)
 *
 */
public class Canvas {

	private int width, height;
	private char backgroundColor;
	private Point[][] points = null;
	private List<Shape> shapes = new ArrayList<Shape>();
	
	public Canvas(int width, int height) {
		this(width, height, TRANSPARENT_COLOR);
	}
	
	public Canvas(int width, int height, char backgroundColor) {
		this.setWidth(width);
		this.setHeight(height);
		this.setBackgroundColor(backgroundColor);
		
		//a line segment with length has (length+1) points
		points = new Point[width+1][height+1];
		for (int i=0; i<=width; i++) {
			for (int j=0; j<=height; j++) {
				points[i][j] = new Point(i, j, backgroundColor);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		Validate.isTrue(width > 0, "width should be greater than 0 but was %d.", width);
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		Validate.isTrue(height > 0, "height should be greater than 0 but was %d.", height);
		this.height = height;
	}

	
	public char getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(char background) {
		this.backgroundColor = background;
	}

	public boolean drawShape(Shape shape) {
		
		Objects.requireNonNull(shape, "shape should not be null.");
		boolean result = true;
		Point[] shapePath = shape.getPath();
		Point[][] updatedPoints = points;
		for (int i=0; i<shapePath.length; i++) {
			int x = shapePath[i].getX();
			int y = shapePath[i].getY();
			//the specified shape should be inside the canvas
			if (x > width || y > height) {
				result = false;
				break;
			}else {
				updatedPoints[x][y] = shapePath[i];
			}
		}
		if (result) {
			points = updatedPoints;
			this.shapes.add(shape);
		}
		return result;
	}
	
	public void bucketFill(int x, int y, char replacementColor) {
		Validate.isTrue(contains(x, y));
		Point startPoint = points[x][y];
		char targetColor = startPoint.getColor();
		if (replacementColor != targetColor) {
//			doBucketFill(point.getX(), point.getY(), targetColor, replacementColor);
			doBucketFill(startPoint, replacementColor);
		}
	}
	
	public char[][] getColorData() {
		char[][] result = new char[width+1][height+1];
		for (int i=0; i<width+1; i++) {
			for (int j=0; j<height+1; j++) {
				result[i][j] = points[i][j].getColor();
			}
		}
		return result;
	}
	
	private boolean contains(int x, int y) {
		return x>=0 && x<=width && y>=0 && y<=height; 
	}
	
	private void doBucketFill(Point startPoint, char replacementColor) {
		
		char targetColor = startPoint.getColor();
		Queue<Point> pointQueue = new LinkedList<Point>();
		pointQueue.offer(startPoint);
		Point currentPoint = null;
		while ((currentPoint=pointQueue.poll())!=null) {
			handlePoint(currentPoint, pointQueue, targetColor, replacementColor);
			
			Point west = null;
			if (currentPoint.getX()>0) {
				west = points[currentPoint.getX()-1][currentPoint.getY()];
			}
			while (west!=null && west.getColor()==targetColor) {
				handlePoint(west, pointQueue, targetColor, replacementColor);
				if (west.getX()>0) {
					west = points[west.getX()-1][west.getY()];
				}else {
					west = null;
				}
			}
			
			Point east = null;
			if (currentPoint.getX()<width) {
				east = points[currentPoint.getX()+1][currentPoint.getY()];
			}
			while (east!=null && east.getColor()==targetColor) {
				handlePoint(east, pointQueue, targetColor, replacementColor);
				if (east.getX()<width) {
					east = points[east.getX()+1][east.getY()];
				}else {
					east = null;
				}
			}

		}
	}	
	
	private void handlePoint(Point point, Queue queue, char targetColor, char replacementColor) {
		
		point.setColor(replacementColor);
		enqueueNorthPoint(point, queue, targetColor);
		enqueueSouthPoint(point, queue, targetColor);
	}
	
	private void enqueueNorthPoint(Point point, Queue queue, char targetColor) {
		
		if (point.getY()>0) {
			Point north = points[point.getX()][point.getY()-1];
			if (north.getColor() == targetColor) {
				queue.offer(north);
			}
		}
	}
	
	private void enqueueSouthPoint(Point point, Queue queue, char targetColor) {
		if (point.getY()<height) {
			Point south = points[point.getX()][point.getY()+1];
			if (south.getColor() == targetColor) {
				queue.offer(south);
			}
		}

	}
	//recursive version may lead to stack overflow
//	private void doBucketFill(int x, int y, char targetColor, char replacementColor) {
//		
//		Point point = points[x][y];
//		char pointColor = point.getColor();
//		if (pointColor == targetColor) {
//			point.setColor(replacementColor);
//			if (y>0) {
//				doBucketFill(x, y-1, targetColor, replacementColor);
//			}
//			if (x<width-1) {
//				doBucketFill(x+1, y, targetColor, replacementColor);
//			}
//			if (y<height-1) {
//				doBucketFill(x, y+1, targetColor, replacementColor);
//			}
//			if (x>0) {
//				doBucketFill(x-1, y, targetColor, replacementColor);
//			}
//		}
//	}

	@Override
	public String toString() {
		return "Canvas [width=" + width + ", height=" + height + ", backgroundColor=" + backgroundColor + "]";
	}
	
	
	
	
}
