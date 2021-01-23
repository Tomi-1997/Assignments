package ex4.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ex4.geometry.*;

/**
 * This test class will check functions of the rectangle class.
 * @author Thomas
 *
 */

class Rect2DTest {

	
	@Test
	void testGet() {
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(1,2);
		Rect2D rec = new Rect2D(a,b);
		
		assertEquals(rec.get_A().x(), 0);
		assertEquals(rec.get_B().y(), 2);
	}

	@Test
	void testContains() {
		Point2D a = new Point2D(1,1);
		Point2D b = new Point2D(10,10);
		Rect2D rect = new Rect2D(a,b);
		
		Point2D c = new Point2D(2,9);
		Point2D d = new Point2D(0,11);
		Point2D f = new Point2D(0.999, 1);
		Point2D g = new Point2D(100, 200);
		Point2D h = new Point2D(1.000001, 9.999999);
		
		assertTrue(rect.contains(c));
		assertFalse(rect.contains(d));
		assertFalse(rect.contains(f));
		assertFalse(rect.contains(g));
		assertTrue(rect.contains(h));
		assertTrue(rect.contains(rect.centerOfMass()));
		
	}

	@Test
	void testCenterOfMass() {
		Point2D a = new Point2D(1,1);
		Point2D b = new Point2D(2,2);
		Rect2D rect = new Rect2D(a,b);
		
		Point2D p = new Point2D(1.5, 1.5);
		assertTrue(p.close2equals(rect.centerOfMass()));
		
		a = new Point2D(0,0);
		b = new Point2D(0, 20);
		rect = new Rect2D(a,b);
		p = new Point2D(0,10);
		
		assertTrue(p.close2equals(rect.centerOfMass()));
		
		a = new Point2D(1,1);
		b = new Point2D(1.5, 1.5);
		rect = new Rect2D(a,b);
		p = new Point2D(1.25,1.25);
		
		assertTrue(p.close2equals(rect.centerOfMass()));
		
		a = new Point2D(0,0);
		b = new Point2D(0, 0);
		rect = new Rect2D(a,b);
		p = new Point2D(0,0);
		
		assertTrue(p.close2equals(rect.centerOfMass()));
		
		a = new Point2D(6.8,7.6);
		b = new Point2D(10, 7.6);
		rect = new Rect2D(a,b);
		p = new Point2D(8.4,7.6);
		
		assertTrue(p.close2equals(rect.centerOfMass()));
	}

	@Test
	void testArea() {
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(1,1);
		Rect2D rect = new Rect2D(a,b);
		double area = rect.area();
		assertEquals(area, 1);
		
		a = new Point2D(0,0);
		b = new Point2D(2,20);
		rect = new Rect2D(a,b);
		area = rect.area();
		assertEquals(area, 40);
		
		a = new Point2D(15,35);
		b = new Point2D(5,45);
		rect = new Rect2D(a,b);
		area = rect.area();
		assertEquals(area, 100);
		
		a = new Point2D(-1,1);
		b = new Point2D(-2,2);
		rect = new Rect2D(a,b);
		area = rect.area();
		assertEquals(area, 1);
		
		a = new Point2D(-1,1);
		b = new Point2D(1,-1);
		rect = new Rect2D(a,b);
		area = rect.area();
		assertEquals(area, 4);
	}
	
	@Test
	void testPerimeter() {
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(1,1);
		Rect2D rect = new Rect2D(a,b);
		double perimeter = rect.perimeter();
		assertEquals(perimeter, 4);
		
		a = new Point2D(0,0);
		b = new Point2D(2,20);
		rect = new Rect2D(a,b);
		perimeter = rect.perimeter();
		assertEquals(perimeter, 44);
		
		a = new Point2D(15,35);
		b = new Point2D(5,45);
		rect = new Rect2D(a,b);
		perimeter = rect.perimeter();
		assertEquals(perimeter, 40);
		
		a = new Point2D(-1,1);
		b = new Point2D(-2,2);
		rect = new Rect2D(a,b);
		perimeter = rect.perimeter();
		assertEquals(perimeter, 4);
		
		a = new Point2D(-1,1);
		b = new Point2D(1,-1);
		rect = new Rect2D(a,b);
		perimeter = rect.perimeter();
		assertEquals(perimeter, 8);
	}

	@Test
	void testMove() { 
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(1,1);
		Rect2D rect = new Rect2D(a,b);
		
		Point2D vec = new Point2D(50,50);
		rect.move(vec);
		Point2D cen = new Point2D(50.5, 50.5);
		assertTrue(rect.contains(cen));
		assertEquals(rect.get_A().x(), 50);
		assertEquals(rect.perimeter(), 4);
		
		vec = new Point2D(-50, -50);
		rect.move(vec);
		assertFalse(rect.contains(cen));
		assertEquals(rect.get_B().y(), 1);
		assertEquals(rect.perimeter(), 4);
	}

	@Test
	void testCopy() {
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(1,1);
		Rect2D rect = new Rect2D(a,b);
		
		Rect2D rectC = (Rect2D) rect.copy();
		assertEquals(rect.hashCode(), rect.hashCode());
		assertNotEquals(rectC.hashCode(), rect.hashCode());
		
		assertEquals(rect.area(), rectC.area());
		assertTrue(rect.get_A().close2equals(rectC.get_A()));
	}

	@Test
	void testGetPoints() {
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(1,1);
		Rect2D rect = new Rect2D(a,b);
		Point2D[] ab = rect.getPoints();
		assertEquals(ab.length, 2);
				
		assertTrue(a.close2equals(ab[0]));
		assertTrue(b.close2equals(ab[1]));

		assertTrue(rect.get_A().close2equals(ab[0]));
		assertTrue(rect.get_B().close2equals(ab[1]));
	}
	
	@Test
	void testToString() {
		Point2D a = new Point2D(6.5,3.5);
		Point2D b = new Point2D(0.1,0.6);
		Rect2D rect = new Rect2D(a,b);
		
		String rectS = rect.toString();
		String[] rect_arr = rectS.split("_");
		Point2D a_str = new Point2D(rect_arr[1]);
		Point2D b_str = new Point2D(rect_arr[0]);
		
		assertTrue(a.close2equals(a_str));
		assertTrue(b.close2equals(b_str));
		
		assertTrue(rect.get_A().close2equals(a_str));
		assertTrue(rect.get_B().close2equals(b_str));
		
		 a = new Point2D(0,0);
		 b = new Point2D(0,0);
		 rect = new Rect2D(a,b);
		
		 assertEquals(rect.toString(), "0.0,0.0_0.0,0.0");
		 
		 a = new Point2D(1,1);
		 b = new Point2D(1,1);
		 rect = new Rect2D(a,b);
		
		 assertEquals(rect.toString(), "1.0,1.0_1.0,1.0");
	}
}
