/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

import org.apache.commons.lang3.Validate;

/**
 * Canvas for drawing, its width and length should be greater than 1
 * so that a rectangle can be drawn on it
 * @author Edwin Ngai (edwin.ngai@mail.com)
 *
 */
public class Canvas {

	private static final char TRANSPARENT_COLOR = ' ';

	private int width;
	private int height;
	private char backgroundColor;
	private Point[][] points = null;
	private List<Shape> shapes = new ArrayList<>();
	
	public Canvas(int width, int height) {
		this(width, height, TRANSPARENT_COLOR);
	}
	
	public Canvas(int width, int height, char backgroundColor) {
		
		Validate.isTrue(width > 1, "width should be greater than 1 but was %d.", width);
		Validate.isTrue(height > 1, "height should be greater than 1 but was %d.", height);
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;

		points = new Point[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				points[i][j] = new Point(i, j, backgroundColor);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public char getBackgroundColor() {
		return backgroundColor;
	}

	public boolean drawShape(Shape shape) {
		
		Objects.requireNonNull(shape, "shape should not be null.");
		boolean result = true;
		Point[] shapePath = shape.getPath();
		
		Point[][] updatedPoints = new Point[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				updatedPoints[i][j] = points[i][j];
			}
		}
		for (int i=0; i<shapePath.length; i++) {
			int x = shapePath[i].getX();
			int y = shapePath[i].getY();
			//the specified shape should be inside the canvas
			if (x > width-1 || y > height-1) {
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
			doBucketFill(startPoint, replacementColor);
		}
	}
	
	public char[][] getColorData() {
		char[][] result = new char[width][height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				result[i][j] = points[i][j].getColor();
			}
		}
		return result;
	}
	
	private boolean contains(int x, int y) {
		return x>=0 && x<width && y>=0 && y<height; 
	}
	
	private void doBucketFill(Point startPoint, char replacementColor) {
		
		char targetColor = startPoint.getColor();
		Queue<Point> pointQueue = new LinkedList<>();
		pointQueue.offer(startPoint);
		Point currentPoint = null;
		while ((currentPoint=pointQueue.poll())!=null) {
			//fill the point and enqueue its north and south
			handlePoint(currentPoint, pointQueue, targetColor, replacementColor);
			
			//handle west in a loop until logic boundary
			Point west = getWestPoint(currentPoint);
			while (west!=null && west.getColor()==targetColor) {
				handlePoint(west, pointQueue, targetColor, replacementColor);
				west = getWestPoint(west);
			}
			
			//handle east in a loop until logic boundary
			Point east = getEastPoint(currentPoint);
			while (east!=null && east.getColor()==targetColor) {
				handlePoint(east, pointQueue, targetColor, replacementColor);
				east = getEastPoint(east);
			}
		}
	}	
	
	
	private void handlePoint(Point point, Queue<Point> queue, char targetColor, char replacementColor) {
		
		point.setColor(replacementColor);
		enqueueNorthPoint(point, queue, targetColor);
		enqueueSouthPoint(point, queue, targetColor);
	}
	
	private void enqueueNorthPoint(Point point, Queue<Point> queue, char targetColor) {
		
		if (point.getY()>0) {
			Point north = points[point.getX()][point.getY()-1];
			if (north.getColor() == targetColor) {
				queue.offer(north);
			}
		}
	}
	
	private void enqueueSouthPoint(Point point, Queue<Point> queue, char targetColor) {
		if (point.getY()<height-1) {
			Point south = points[point.getX()][point.getY()+1];
			if (south.getColor() == targetColor) {
				queue.offer(south);
			}
		}
	}
	
	private Point getWestPoint(Point point) {
		
		Point result = null;
		if (point.getX()>0) {
			result = points[point.getX()-1][point.getY()];
		}
		return result;
	}
	
	private Point getEastPoint(Point point) {
		
		Point result = null;
		if (point.getX()<width-1) {
			result = points[point.getX()+1][point.getY()];
		}
		return result;
	}

	@Override
	public String toString() {
		return "Canvas [width=" + width + ", height=" + height + ", backgroundColor=" + backgroundColor + "]";
	}
	
	
	
	
}
