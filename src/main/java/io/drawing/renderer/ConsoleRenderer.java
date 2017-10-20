/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.renderer;

import java.util.Objects;

import io.drawing.shape.Canvas;
import io.drawing.util.Utils;

/**
 * A simple renderer that renders a canvas in console window
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class ConsoleRenderer implements Renderer {

	/* (non-Javadoc)
	 * @see io.drawing.renderer.CanvasRenderer#render(io.drawing.shape.Canvas)
	 */
	@Override
	public void render(Canvas canvas) {

		Objects.requireNonNull(canvas);
		char[][] data = canvas.getColorData();
		//print the ceiling
		for (int i=0, size=data.length+2; i<size; i++) {
			Utils.print('-');
		}
		Utils.print("\n");
		//print the canvas
		for (int j=0; j<data[0].length; j++) {
			Utils.print('|');
			for (int i=0; i<data.length; i++) {
				Utils.print(data[i][j]);
			}
			Utils.print('|');
			Utils.print("\n");
		}
		//print the floor
		for (int i=0, size=data.length+2; i<size; i++) {
			Utils.print('-');
		}
		Utils.print("\n");
	}

}
