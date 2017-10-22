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
public abstract class Line implements Shape {

	private Point start;
	private Point end;
	
	public Line(Point start, Point end) {
		
		Objects.requireNonNull(start, "start point should not be null.");
		Objects.requireNonNull(end, "end point should not be null.");
		Validate.isTrue(start.getX()<=end.getX(), 
				"X coordinate of start point should not be less than of end point, but were %d and %d", start.getX(), end.getX());
		this.start = start;
		this.end = end;
		this.setStart(start);
		this.setEnd(end);
	}

	public Point getStart() {
		return start;
	}

	private void setStart(Point start) {
		Objects.requireNonNull(start, "start point should not be null.");
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	private void setEnd(Point end) {
		Objects.requireNonNull(end, "end point should not be null.");
		this.end = end;
	}

	@Override
	public String toString() {
		return "Line [start=" + start + ", end=" + end + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Line other = (Line) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

}
