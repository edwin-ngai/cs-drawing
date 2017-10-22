/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Rectangle implements Shape {

	private Point upperLeft;
	private Point lowerRight;
	private int width;
	private int height;
	private Point[] path = null;
	
	public Rectangle(Point upperLeft, Point lowerRight) {
		
		Objects.requireNonNull(upperLeft);
		Objects.requireNonNull(lowerRight);
		int upperLeftX = upperLeft.getX();
		int upperLeftY = upperLeft.getY();
		int lowerRightX = lowerRight.getX();
		int lowerRightY = lowerRight.getY();
		Validate.isTrue(upperLeftX<lowerRightX, 
				"X coordinate of upperLeft should be less than of lowerRight but where %d and %d", upperLeftX, lowerRightX);
		Validate.isTrue(upperLeftY<lowerRightY,
				"Y coordinate of upperLeft should be less than of lowerRight but where %d and %d", upperLeftY, lowerRightY);
		this.width = lowerRightX - upperLeftX + 1;
		this.height = lowerRightY - upperLeftY + 1;
		this.path = new Point[(width+height)*2-4];
		int pathIndex = 0;	
		for (int i=upperLeftX; i<=lowerRightX; i++) {
			path[pathIndex++] = new Point(i, upperLeftY);
		}
		for (int i=upperLeftY+1; i<=lowerRightY; i++) {
			path[pathIndex++] = new Point(lowerRightX, i);
		}
		for (int i=lowerRightX-1; i>=upperLeftX; i--) {
			path[pathIndex++] = new Point(i, lowerRightY);
		}
		for (int i=lowerRightY-1; i>upperLeftY; i--) {
			path[pathIndex++] = new Point(upperLeftX, i);
		}
		
		this.upperLeft = this.path[0];
		this.lowerRight = this.path[width+height-2];

	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	
	public Point getLowerRight() {
		return lowerRight;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}

	
	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#getPath()
	 */
	@Override
	public Point[] getPath() {
		return path;
	}


	@Override
	public String toString() {
		return "Rectangle [upperLeft=" + upperLeft + ", lowerRight=" + lowerRight + ", width=" + width + ", height="
				+ height + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lowerRight == null) ? 0 : lowerRight.hashCode());
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
		if (lowerRight == null) {
			if (other.lowerRight != null)
				return false;
		} else if (!lowerRight.equals(other.lowerRight))
			return false;
		if (upperLeft == null) {
			if (other.upperLeft != null)
				return false;
		} else if (!upperLeft.equals(other.upperLeft))
			return false;
		return true;
	}


	
}
