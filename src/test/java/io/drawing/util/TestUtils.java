/**
 * This is a simple console version of a drawing program.
 */
package io.drawing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.drawing.shape.CanvasTest;

/**
 * @author Edwin Ngai (edwin.ngai@mail.con)
 *
 */
public class TestUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);


	public static final int RANDOM_BOUND = 100;
	
	private static void loopTest(Runnable runnable) {
		
		try {
			int i = 0;
			while (true) {
				logger.info("test [{}]", i++);
				runnable.run();
				Thread.sleep(100);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		loopTest(new Runnable(){
			
			private CanvasTest test = new CanvasTest();
			@Override
			public void run() {
				test.whenBukcetFillWithRandomPointThenPointsConnectedAreFilled();
			}
			
		});
	}
}
