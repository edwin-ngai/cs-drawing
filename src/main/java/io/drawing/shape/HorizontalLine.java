/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import org.apache.commons.lang3.Validate;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class HorizontalLine extends Line {

	private Point[] path = null;

	public HorizontalLine(Point start, Point end) {
		super(start, end);
		Validate.isTrue(start.getY()==end.getY(), 
				"Y coordinates of start and end point should be same but were %d and %d.", start.getY(), end.getY());
		Validate.isTrue(start.getX() < end.getX(), 
				"X coordinate of start point should be less than of end point, but were %d and %d", start.getX(), end.getX());
		int startX = start.getX();
		int endX = end.getX();
		int y = start.getY();
		//line segment includes both start and end point, so that the path length should be endX-startX+1
		path = new Point[(endX-startX)+1];
		for (int i=0; i<path.length; i++) {
			path[i] = new Point(startX+i, y);
		}
	}


	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#getPath()
	 */
	@Override
	public Point[] getPath() {
		return path;
	}
	
}
