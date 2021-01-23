package ex4.tests;

import ex4.*;
import ex4.geometry.*;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;

/**
 * This test class will check the overall Ex4 main class,
 * by loading a text file and checking if it runs with no errors, and then
 * saving the text file and loading it again into a new Ex4 object.
 * @author Thomas
 *
 */

class Ex4Test {

	Ex4 win1 = new Ex4();
	GUI_Shape_Collection win_collection1 = win1.getShape_Collection();
	
	@Test
	void test() {
		assertTrue(win_collection1.equals(win1.getShape_Collection()));
		/*Same collection, win_collection is just a pointer*/
		
		/*Loading temp txt file ::
		 *  GUIShape,-16776961,false,0,Point2D,0.2,0.4
			GUIShape,-65536,true,1,Circle2D,0.1,0.2, 0.14
			GUIShape,-16711936,true,2,Rect2D,0.1,0.2,0.5,0.45
			GUIShape,-16711681,false,3,Rect2D,0.1,0.2,0.17,0.77
			GUIShape,-14336,false,4,Triangle2D,0.8,0.7,0.3,0.65,0.1,0.1
			GUIShape,-20561,false,5,Segment2D,0.2,0.6,0.7,0.2
			GUIShape,-8355712,false,6,Rect2D,-0.04000000000000001,0.06,0.8,0.77
		 
		 */
		win_collection1.load("temp.txt");
		assertFalse(win_collection1.size() == 0);

		String expected = "GUIShape,-16711936,true,2,Rect2D,0.1,0.2,0.5,0.45";
		assertTrue(win1.getInfo().contains(expected));
		/*Test if it has successfully loaded*/
		
		while (win_collection1.size()>1)
		win_collection1.removeElementAt(win_collection1.size()-1);
		/*Remove all but the first shape*/

		win_collection1.save("temp2.txt");
		win_collection1 = null;
		win1 = null;
		/*Reseting*/
		
		Ex4 win2 = new Ex4();
		GUI_Shape_Collection win_collection2 = win2.getShape_Collection();
		
		win_collection2.load("temp2.txt");
		/*Testing if the algorithm can save into a text file
		  and read from it successfully*/
		
		expected = "GUIShape,-16776961,false,0,Point2D,0.2,0.4";
		assertTrue(win2.getInfo().contains(expected));
		int nullExcpetion = 0;
		
		try {
		win_collection2.add(null);	
		System.out.println(win2.getInfo());
		}
		catch (NullPointerException e) {
			nullExcpetion = 1;
		}
		
		assertEquals(nullExcpetion , 1);
	}

}
