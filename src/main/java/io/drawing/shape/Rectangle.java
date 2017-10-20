/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.Validate;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Rectangle implements Shape {

	private Point upperLeft;
	private int width, height;
	private Point[] path = null;
	
	public Rectangle(Point upperLeft, int width, int height) {
		
		this.setUpperLeft(upperLeft);
		this.setWidth(width);
		this.setHeight(height);
		
		//init path array following clockwise direction
		path = new Point[(width+height)*2];
		int startX = upperLeft.getX();
		int startY = upperLeft.getY();
		int pathIndex = 0;
		for (int i=0; i<width; i++, pathIndex++) {
			path[pathIndex] = new Point(startX+i, startY);
		}
		
		startX += width;
		for (int i=0; i<height; i++, pathIndex++) {
			path[pathIndex] = new Point(startX, startY+i);
		}
		
		startY += height;
		for (int i=0; i<width; i++, pathIndex++) {
			path[pathIndex] = new Point(startX-i, startY);
		}
		
		startX -= width;
		for (int i=0; i<height; i++, pathIndex++) {
			path[pathIndex] = new Point(startX, startY-i);
		}
	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	private void setUpperLeft(Point upperLeft) {
		Objects.requireNonNull(upperLeft, "upperLeft point should not be null.");
		this.upperLeft = upperLeft;
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		Validate.isTrue(width > 0, "width should be greater than 0 but was %d", width);
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		Validate.isTrue(height > 0, "height should be greater than 0 but was %d", height);
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#contains(io.drawing.shape.Point)
	 */
//	@Override
//	public boolean contains(Point p) {
//
//		int x0 = upperLeft.getX();
//        int y0 = upperLeft.getY();
//        int x = p.getX();
//        int y = p.getY();
//        return (x >= x0 &&
//                y >= y0 &&
//                x < x0 + getWidth() &&
//                y < y0 + getHeight());
//	}

	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#getPath()
	 */
	@Override
	public Point[] getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	public String toString() {
		return "Rectangle [upperLeft=" + upperLeft + ", width=" + width + ", height=" + height + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + Arrays.hashCode(path);
		result = prime * result + ((upperLeft == null) ? 0 : upperLeft.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (height != other.height)
			return false;
		if (!Arrays.equals(path, other.path))
			return false;
		if (upperLeft == null) {
			if (other.upperLeft != null)
				return false;
		} else if (!upperLeft.equals(other.upperLeft))
			return false;
		return true;
	}
	
	

}
