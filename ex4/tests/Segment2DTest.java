package ex4.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ex4.geometry.*;

/**
 * This test class will check functions of the segment class.
 * @author Thomas
 *
 */

class Segment2DTest {
	//default segment is -1,-1 to 1,1
	Segment2D seg = new Segment2D();
	@Test
	void testSetters_n_Getters() {
		Segment2D seg = new Segment2D();
		Segment2D seg_c = new Segment2D(seg);

		assertFalse(seg.getA() == seg_c.getA());
		assertTrue(seg.getA().close2equals(seg_c.getA()));
		
		seg.setA(null);
		assertTrue(seg.getA() != null);

	}

	@Test
	void testContains() {
		Point2D ot = new Point2D(0,0);
		assertTrue(seg.contains(ot));

		 ot = new Point2D(-1,-1);
		assertTrue(seg.contains(ot));

		 ot = new Point2D(1,1);
		assertTrue(seg.contains(ot));
	}

	@Test
	void testCenterOfMess()
	{
		Point2D p = new Point2D(0,0);
		assertTrue(seg.centerOfMass().close2equals(p));
		assertTrue(seg.contains(seg.centerOfMass()));
		assertEquals(p.distance(seg.getA()), p.distance(seg.getB()));
	}

	@Test
	void testArea() {
		assertEquals(seg.area(), 0);
		seg.move(new Point2D(2,5));
		assertEquals(seg.area(), 0);
	}
	
	@Test
	void testPerimeter() {
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(5,0);
		Segment2D s = new Segment2D(a,b);
		
		assertEquals(s.perimeter(), 10);
		s.move(new Point2D(43,25));
		assertEquals(s.perimeter(), 10);
		
		b.move(new Point2D(1,0));
		s = new Segment2D(a,b);
		assertEquals(s.perimeter(), 12);
	}
	
	@Test
	void testMove() {
		double a_before = seg.area();
		Point2D vec = new Point2D(Math.sqrt(2), 10);
		seg.move(vec);
		
		assertEquals(seg.area(), a_before);
		assertEquals(seg.getA().x(), (-1) + Math.sqrt(2));
		assertEquals(seg.getB().y(), 11);
	}
	
	@Test
	void testCopy() {
		Segment2D segC = (Segment2D) seg.copy();
		
		assertNotEquals(segC.hashCode(), seg.hashCode());
		assertEquals(segC.area(), seg.area());
		
		assertTrue(seg.centerOfMass().close2equals(segC.centerOfMass()));
		assertEquals(segC.perimeter(), seg.perimeter());
	}
	
	@Test
	void testGetPoints() {
		Point2D[] a = seg.getPoints();
		assertTrue(a[0].close2equals(seg.getA()));
		assertTrue(a[1].close2equals(seg.getB()));
		assertEquals(a.length, 2);
	}
	
	@Test
	void testToString() {
		String str = seg.toString(); 
		assertTrue(seg.toString() instanceof String);
		
		 Point2D a = new Point2D(1,1);
		 Point2D b = new Point2D(1,1);
		 seg = new Segment2D(a,b);
		
		 assertEquals(seg.toString(), "1.0,1.0_1.0,1.0");
		 
		 a = new Point2D(Math.sqrt(2),Math.sqrt(5));
		 b = new Point2D(2,5);
		 seg = new Segment2D(a,b);

		assertEquals(seg.toString(),"1.4142135623730951,2.23606797749979_2.0,5.0");
	}

}
