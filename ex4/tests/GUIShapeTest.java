package ex4.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ex4.*;
import ex4.geometry.*;

/**
 * This test class will check the function of the GUIShape class, such as
 * isFilled, getShape, color.
 * @author Thomas
 *
 */


class GUIShapeTest {

	GUIShape sh = new GUIShape();
	@Test
	void test_Shape() {
		
		Point2D a = new Point2D(1,1);
		sh = new GUIShape (a, false, Color.black, 0);
		assertEquals(sh.getShape().getPoints().length, 1);
		assertTrue(sh.getShape().getClass().toString().contains("Point"));
		/*Testing if the class name contains the actual shape name, 
		 * and the array has just one point.*/
		
		
		Segment2D seg = new Segment2D(a,new Point2D(0,0));
		sh.setShape(seg);
		
		assertEquals(sh.getShape().getPoints().length, 2);
		assertTrue(sh.getShape().getClass().toString().contains("Segment"));
		/*Array of points should have two elements*/
		
		sh.setColor(Color.cyan);
		assertEquals(sh.getColor(), Color.cyan);
		sh.setColor(Color.black);
		assertNotEquals(sh.getColor(), Color.cyan);
		/*Testing set and get color*/
		
		sh.setFilled(true);
		assertTrue(sh.isFilled());
		sh.setFilled(false);
		assertFalse(sh.isFilled());
		/*Testing set and is filled.*/
		
		assertEquals(sh.getTag(), 0);
		sh.setTag(1);
		assertEquals(sh.getTag(), 1);
		sh.setTag(0);
		assertEquals(sh.getTag(), 0);
		
		GUIShape sh_cop = (GUIShape) sh.copy();
		assertEquals(sh.getShape().getClass(), sh_cop.getShape().getClass());
		assertNotEquals(sh.hashCode(), sh_cop.hashCode());
		assertNotEquals(sh.getShape().hashCode(), sh_cop.getShape().hashCode());
		/*asserting that the deep copy does not point to the original*/
	}

}
