/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import org.apache.commons.lang3.Validate;
import static io.drawing.util.Constant.*;

/**
 * A point representing a location in user coordinate system
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class Point {

	private int x, y;
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
		if (color != other.color)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", color=" + color + "]";
	}
	
}
