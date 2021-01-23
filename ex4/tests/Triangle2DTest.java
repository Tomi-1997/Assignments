package ex4.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ex4.geometry.*;

/**
 * This test class will check functions of the triangle class.
 * @author Thomas
 *
 */

class Triangle2DTest {
	final double EPS = 0.0000001;
	
	@Test
	void testGet()
	{
		Point2D a = new Point2D(0,2);
		Point2D b = new Point2D(1,0);
		Point2D c = new Point2D(0,-1);
		Triangle2D tri = new Triangle2D(a,b,c);
		
		double a_x = tri.get_A().x();
		double c_y = tri.get_C().y();
		
		assertEquals(a_x, 0.0);
		assertEquals(c_y, -1.0);
	}
	
	@Test
	void testContains()
	{
		Point2D a = new Point2D(0,2);
		Point2D b = new Point2D(1,0);
		Point2D c = new Point2D(0,-1);
		Triangle2D tri = new Triangle2D(a,b,c);
		
		Point2D pt = new Point2D(0,0);
		assertTrue(tri.contains(pt));
		
		pt = new Point2D(0.33,0.33);
		assertTrue(tri.contains(pt));
		
		pt = new Point2D(0.5,0.5);
		assertTrue(tri.contains(pt));
		
		pt = new Point2D(1,1);
		assertFalse(tri.contains(pt));
		
		pt = new Point2D(1,0.1);
		assertFalse(tri.contains(pt));

		pt = new Point2D(-1,-0.1);
		assertFalse(tri.contains(pt));
		assertTrue(tri.contains(tri.centerOfMass()));
	}
	
	@Test
	void testCenterOfMass()
	{
		Point2D a = new Point2D(15,15);
		Point2D b = new Point2D(64,20);
		Point2D c = new Point2D(47,40);
		Triangle2D tri = new Triangle2D(a, b, c);
		assertEquals(tri.centerOfMass().x() , 42);
		assertEquals(tri.centerOfMass().y() , 25);
		
		tri = new Triangle2D(new Point2D(0,0),new Point2D(0,21),new Point2D(99,0));
		assertEquals(tri.centerOfMass().x() , 33);
		assertEquals(tri.centerOfMass().y() , 7);
	}
	
	@Test
	void testArea()
	{
		Triangle2D tri = new Triangle2D(new Point2D(0,0),new Point2D(0,3),new Point2D(4,0));
		assertEquals(tri.area() , 6);
		
		tri = new Triangle2D(new Point2D(0,0),new Point2D(0,20),new Point2D(99,0));
		assertEquals(tri.area() , 990);
		
		tri = new Triangle2D(new Point2D(0,0),new Point2D(0,1),new Point2D(1,0));
		boolean flag = (tri.area() - EPS < (0.5));
		assertTrue(flag);	
		
		tri = new Triangle2D(new Point2D(0,0),new Point2D(0,8),new Point2D(6,0));
		assertEquals(tri.perimeter(), tri.area());
		
		/*  |\
		 * 	| \
 		 *  |__\
		 */ 
	}
	
	@Test
	void testPerimeter() {
		Point2D a = new Point2D(2, 0);
		Point2D b = new Point2D(0, 2);
		Point2D c = new Point2D(0, 0);
		Triangle2D tri = new Triangle2D(a,b,c);
		
		double peri = tri.perimeter();
		double peri_halved = tri.perimeter_half();
		
		double peri_expected = 2 + 2 + (Math.sqrt(8));
		double peri_expected_halved = peri_expected /2 ;
		
		assertEquals(peri,peri_expected);
		assertEquals(peri_halved,peri_expected_halved);
		
		 a = new Point2D(-3, 0);
		 b = new Point2D(4, 0);
		 c = new Point2D(1, 1);
		 tri = new Triangle2D(a,b,c);
		 
		 double d1 = a.distance(b);
		 double d2 = b.distance(c);
		 double d3 = c.distance(a);
		 
		 assertEquals(d1+d2+d3, tri.perimeter());
		 assertEquals((d1+d2+d3)/2, tri.perimeter_half());
	}

	@Test
	void testMove()
	{
		Point2D a = new Point2D(1,1);
		Point2D b = new Point2D(2,5);
		Point2D c = new Point2D(7,0);
		Triangle2D tri = new Triangle2D(a,b,c);
		
		Point2D m = new Point2D (1,0);
		tri.move(m);
		assertEquals(tri.get_A().x(), 2);
		assertEquals(tri.get_A().y(), 1);
		
		m = new Point2D (10,10);
		tri.move(m);
		assertEquals(tri.get_B().x(), 13);
		assertEquals(tri.get_B().y(), 15);
		
		m = new Point2D(-11, -10);
		tri.move(m);
		assertEquals(tri.get_C().x(), 7);
		assertEquals(tri.get_C().y(), 0);
	}

	@Test
	void testGetPoints()
	{
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(0,5);
		Point2D c = new Point2D(12,0);
		Triangle2D tri = new Triangle2D(a,b,c);
		Point2D[] tri_p = tri.getPoints();
		
		assertEquals(tri.get_A(), tri_p[0]);
		assertEquals(tri.get_B(), tri_p[1]);
		assertEquals(tri.get_C(), tri_p[2]);
		
		assertTrue(tri.get_A().close2equals(tri_p[0]));
		assertTrue(tri.get_B().close2equals(tri_p[1]));
		assertTrue(tri.get_C().close2equals(tri_p[2]));
		
	}

	@Test
	void testToString()
	{
		Point2D a = new Point2D(0,0);
		Point2D b = new Point2D(8,0);
		Point2D c = new Point2D(0,6);
		Triangle2D tri = new Triangle2D(a , b , c);
		
		String str = tri.toString();
		String str_tri[] = str.split("-");
		
		String as = str_tri[0];
		Point2D a_t = new Point2D(as);

		String bs = str_tri[1];
		Point2D b_t = new Point2D(bs);
		
		String cs = str_tri[2];
		Point2D c_t = new Point2D(cs);

		Point2D[] tri_p = tri.getPoints();
		
		assertTrue(tri_p[0].close2equals(a_t));
		assertTrue(tri_p[1].close2equals(b_t));
		assertTrue(tri_p[2].close2equals(c_t));
		
		assertTrue(tri_p[0].close2equals(a));
		assertTrue(tri_p[1].close2equals(b));
		assertTrue(tri_p[2].close2equals(c));
		
		
	}

}
