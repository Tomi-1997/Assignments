package ex4.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import ex4.geometry.*;

/**
 * This test class will check functions of the circle class.
 * @author Thomas
 *
 */

class Circle2DTest {
	final double EPS = 0.0000001;
	double pi = Math.PI;
	Circle2D c = new Circle2D();

	@Test
	void testGet() {
		assertEquals(c.get_R(),1);
		c.set_R(2);
		assertEquals(c.get_R(), 2);
	}
	
	@Test 
	void testSet() { 
		assertEquals(c.get_R(), 1);
		assertTrue(c.get_R() >= 0);

		boolean flag = false;
		try
		{
			c.set_R(-1);
		}
		catch (IllegalArgumentException e)
		{
			flag = true;
		}
		assertTrue(flag);
	}
	
	@Test
	void testContains() {
		Point2D pt = new Point2D(0.0001, 0.0001);
		assertTrue(c.contains(pt));
		pt = new Point2D(1.0, 0);
		assertTrue(c.contains(pt));
		pt.move(pt);
		//Now the point pt is at (2.0,0)
		assertFalse(c.contains(pt));
		pt = new Point2D(0, 0.99);
		assertTrue(c.contains(pt));
	}
	
	@Test
	void testCenterOfMass() {
		Point2D expected_p = new Point2D(0,0);
		assertTrue(c.get_P().close2equals(expected_p));
		
		expected_p = new Point2D(0.001,0.001);
		assertFalse(c.get_P().close2equals(expected_p));
		
		c.set_P(new Point2D(0.001, 0.001));
		assertTrue(c.get_P().close2equals(expected_p));
		
		c = new Circle2D();
		Point2D p = new Point2D(2,3);
		c.set_P(p);
		expected_p = new Point2D(2,3);
		assertTrue(c.get_P().close2equals(expected_p));
	}
	
	@Test
	void testArea() {
		assertEquals(c.area(), pi);
		
		Point2D vec = new Point2D(1,1);
		c.move(vec);
		assertEquals(c.area(), pi);
		
		c.set_R(pi);
		assertEquals(c.area(), pi*pi*pi);

		c.set_R(2);
		assertEquals(c.area(), pi * 4);
		
		boolean flag = (c.area() - pi * 4) < EPS;
		assertTrue(flag);
		
	}
	
	@Test
	void testPerimeter() {
		assertEquals(c.perimeter(), pi*2);
		c.move(new Point2D(2,2));
		assertEquals(c.perimeter(), pi*2);
		
		c.set_R(4);
		assertEquals(c.perimeter(), pi*2*4);
		
		c.set_R(0.000001);
		assertEquals(c.perimeter(), pi*2*0.000001);
		double per1 = c.perimeter();
		double per2;
		
		c.set_R(0.001);
		per2 = c.perimeter();
		assertNotEquals(per1,per2);
	}
	
	@Test
	void testMove() {
		double a,p;
		a = c.area(); 
		p = c.perimeter();
		double x = c.get_P().x();
		c.move(new Point2D(pi,2));
		assertEquals(a, c.area());
		assertEquals(p, c.perimeter());
		
		c.move(new Point2D(-pi, 34));
		assertEquals(x, c.get_P().x());
		
		double x1 = 100000;
		double y1 = 999999;
		Point2D vec = new Point2D(x1,y1);
		c.move(vec);
		assertNotNull(c);
	}
	
	@Test
	void testCopy() {
		Circle2D c_copy = (Circle2D) c.copy();
		assertNotEquals(c.hashCode(), c_copy.hashCode());
		assertEquals(c_copy.area(), c.area());
		assertEquals(c_copy.perimeter(), c.perimeter());
		
		assertTrue(c_copy.contains(c.get_P()));
		assertEquals(c_copy.get_R(), c.get_R());
	}
	
	@Test
	void testGetPoints() {
		Point2D[] arr_circ = c.getPoints();
		assertEquals(arr_circ.length, 1);
		assertTrue(arr_circ[0].close2equals(c.get_P()));
		assertTrue(c.contains(arr_circ[0]));
	}
	
	@Test
	void testToString() {
		assertTrue(c.toString() instanceof String);
		assertFalse(c.toString().length() == 0);
		String[] stra = c.toString().split(" ");
		assertTrue(stra.length == 2);
	}

}
