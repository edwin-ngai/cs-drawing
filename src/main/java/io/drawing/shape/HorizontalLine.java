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
		Validate.isTrue(start.getY()==end.getY(), "Y coordinates of start and end point should be same but were %d and %d.", start.getY(), end.getY());
		int startX = start.getX();
		int endX = end.getX();
		int y = start.getY();
		//line segment includes both start and end point, so that the path length should be endX-startX+1
		int direction = endX>startX?1:-1;
		int distance = (endX-startX)*direction+1;
		path = new Point[distance];
		for (int i=0; i<path.length; i++) {
			path[i] = new Point(startX+i*direction, y);
		}
	}

	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#contains(io.drawing.shape.Point)
	 */
//	@Override
//	public boolean contains(Point point) {
//		
//		Objects.requireNonNull(point);
//		return point.getY() == this.getStart().getY();
//	}

	/* (non-Javadoc)
	 * @see io.drawing.shape.Shape#getPath()
	 */
	@Override
	public Point[] getPath() {
		return path;
	}
	
	
	public static boolean isHorizontal(Point start, Point end) {
		
		boolean result = false;
		if (start!=null && end!=null) {
			result = (start.getY()==end.getY());
		}
		return result;
	}
}
