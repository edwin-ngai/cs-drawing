/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.drawing.shape.Canvas;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public final class CommandContext {

	private Canvas canvas;
	private Map<String, Object> arguments = new HashMap<String, Object>();
	
	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		Objects.requireNonNull(canvas);
		this.canvas = canvas;
	}

	public Object getArgument(String key) {
		return arguments.get(key);
	}
	
	public Object addArgument(String key, Object value) {
		return arguments.put(key, value);
	}
}
