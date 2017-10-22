/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.renderer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.drawing.shape.Canvas;

/**
 * A simple renderer that renders a canvas in console window
 * 
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class StreamRenderer implements Renderer {

	private static final Logger logger = LoggerFactory.getLogger(StreamRenderer.class);

	private OutputStream os;

	public StreamRenderer(OutputStream os) {
		Objects.requireNonNull(os);
		this.os = os;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.drawing.renderer.CanvasRenderer#render(io.drawing.shape.Canvas)
	 */
	@Override
	public void render(Canvas canvas) {

		Objects.requireNonNull(canvas);
		try {
			char[][] data = canvas.getColorData();
			// print the ceiling
			for (int i = 0, size = data.length + 2; i < size; i++) {
				os.write('-');
			}
			os.write('\n');
			// print the canvas
			for (int j = 0; j < data[0].length; j++) {
				os.write('|');
				for (int i = 0; i < data.length; i++) {
					os.write(data[i][j]);
				}
				os.write('|');
				os.write('\n');
			}
			// print the floor
			for (int i = 0, size = data.length + 2; i < size; i++) {
				os.write('-');
			}
			os.write('\n');
		}catch (IOException ex) {
			logger.debug(ex.getMessage(), ex);
		}
	}

}
