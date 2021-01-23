package ex4.tests;
/**
 * This test class will check shape_collection class, such as checking the add functions, removeAll and remove
 * at a certain index.
 * @author Thomas
 *
 */
import ex4.*;
import ex4.geometry.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;

class Shape_CollectionTest {

	@Test
	void test() {
		int tag = 0;
		Shape_Collection shapes = new Shape_Collection();
		assertEquals(shapes.size(), 0);
		/*Just after initializing, size should be 0 as it is empty*/
		
		Point2D p1 = new Point2D(0.333,0.666);
		Point2D p2 = new Point2D(0.2, 0.4);
		Rect2D r1 = new Rect2D(p1, p2);
		
		GUIShape gr1 = new GUIShape(r1, true, Color.pink, tag++);
		
		shapes.add(gr1);
		assertEquals(shapes.size(), 1);
		assertTrue(shapes.get(0).isFilled());
		boolean flag = shapes.getBoundingBox().area() == r1.area();
		assertTrue(flag);
		assertTrue(r1.centerOfMass().close2equals(shapes.getBoundingBox().centerOfMass()));
		/*with just one rectangle, the bounding box should be equal, but with no same memory*/

		shapes.removeAll();
		tag = 0;
		assertEquals(shapes.size(), 0);
		
		GUIShape gp1 = new GUIShape(p1, true, Color.green, tag++);
		GUIShape gp2 = new GUIShape(p2, true, Color.red, tag++);
		shapes.add(gp1);
		shapes.add(gp2);
		
		assertEquals(shapes.size(), 2);
		flag = shapes.getBoundingBox().centerOfMass().close2equals(r1.centerOfMass());
		assertTrue(flag);
		/*Bounding box made by the two points r1 is made from should be the same center of mass*/
		
		Point2D p3 = new Point2D(10, 20);
		GUIShape gp3 = new GUIShape(p3, true, Color.black, tag++);
		shapes.add(gp3);
		
		flag = shapes.getBoundingBox().centerOfMass().close2equals(r1.centerOfMass());
		assertFalse(flag);
		/*flag should now be false, as the bounding box should update after we added a new point*/
		
		assertFalse(shapes.getInfo().equals(""));
		/*Test to see if get info is not an empty string, as the list has at least one element.*/
		
		while(shapes.size()>0) {
			shapes.removeElementAt(0);
		}
		/*Get info should be empty, as we removed all element at the first index until it is empty*/
		assertTrue(shapes.getInfo().equals(""));
		
		shapes.add(gr1);
		shapes.add(gp1);
		String shapes_str = shapes.toString();
		
		shapes_str = shapes_str.substring(1, shapes_str.length()-1);
		String[] shapes_arr = shapes_str.split(",");
		
		/*Testing if the toString representation of points is able to convert back into double*/
		for (int i = 0; i < shapes_arr.length; i ++) {
			flag = Double.parseDouble(shapes_arr[i]) >= 0 || Double.parseDouble(shapes_arr[i]) < 0;
			assertTrue(flag);
		}
		}
}
