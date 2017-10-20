/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.shape;

import org.apache.commons.lang3.Validate;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class VerticalLine extends Line {

	private Point[] path = null;

	public VerticalLine(Point start, Point end) {
		super(start, end);
		Validate.isTrue(start.getX()==end.getX(), 
				"X coordinates of start and end point should be same but were %d and %d.", start.getX(), end.getX());
		int startY = start.getY();
		int endY = end.getY();
		int x = start.getX();
		//line segment includes both start and end point, so that the path length should be endX-startX+1
		int direction = endY>startY?1:-1;
		int distance = (endY-startY)*direction+1;
		path = new Point[distance];
		for (int i=0; i<path.length; i++) {
			path[i] = new Point(x, startY+i*direction);
		}

	}
	
	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#contains(io.drawing.shape.Point)
	 */
//	@Override
//	public boolean contains(Point point) {
//		
//		Objects.requireNonNull(point);
//		return point.getX() == this.getStart().getX();
//	}

	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#getPath()
	 */
	@Override
	public Point[] getPath() {
		return path;
	}

	
	public static boolean isVertical(Point start, Point end) {
		
		boolean result = false;
		if (start!=null && end!=null) {
			result = (start.getX()==end.getX());
		}
		return result;
	}
}
