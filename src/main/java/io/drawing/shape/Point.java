/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import org.apache.commons.lang3.Validate;

/**
 * A point representing a location in user coordinate system
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Point {

	private static final char DEFAULT_COLOR = 'x';

	private int x;
	private int y;
	private char color;
	
	public Point(int x, int y) {
		this(x, y, DEFAULT_COLOR);
	}
	
	public Point(int x, int y, char color) {
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
	
	public int getX() {
		return x;
	}

	private void setX(int x) {
		Validate.isTrue(x >= 0, "x should not be negative but was %d.", x);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		Validate.isTrue(y >= 0, "y should not be negative but was %d.", y);
		this.y = y;
	}

	
	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		result = prime * result + x;
		result = prime * result + y;
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
		Point other = (Point) obj;
		return color==other.color && x==other.x && y==other.y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", color=" + color + "]";
	}
	
}
