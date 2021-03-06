import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Ex2Test {
	final double eps = 0.001;
	
double[] p1 = { 2 , 0 , 3 , -1 , 0} ;
double[] p2 = { 0 , 0 , -1 , 10 , 0 , 0 , 0 , 1 , 2 , -1 , 0 , 1} ;
double[] p3 = { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1};

// p4 p5 p6 are defined mostly to test the root function
double[] p4 = { -4 , 0 , 1 };			// x^2 - 4
double[] p5 = { -8 , -2 , 1 } ;			// 			x^2 - 2x - 8
double[] p6 = { 40 , 2 , -7 , 1 };		
// made up by this trinomial multiplication (x-4)(x-2)(x+5)---x^3 - 7x^2 + 2x + 40


	@Test     // the unmarked text and variables are from the original Ex2.rar file
	
	void testF() {
		/*double fx0 = Ex2.f(p1, 0);
		double fx2 = Ex2.f(p1, 2);
		double fx4 = Ex2.f(p1, 4);
		assertEquals(fx0,4);
		assertEquals(fx2,20);
		assertEquals(fx4,60);*/
		
		double fx0 = Ex2.f(p1, 0);
		double fx1 = Ex2.f(p1, 1);
		double fx2 = Ex2.f(p1, 2);
		double fx2_p3 = Ex2.f(p3, 2);
		
		assertEquals(fx0, 2 );
		assertEquals(fx1, 4 );
		assertEquals(fx2, 6 );
		assertEquals(fx2_p3, 256 );
	}
	@Test
	void testPoly()
	{
		String str1 = Ex2.poly(p1);
		String str2 = Ex2.poly(p2);
		String str3 = Ex2.poly(p3);
		assertEquals(str1, "-1.0x^3+3.0x^2+2.0");
		assertEquals(str2, "1.0x^11-1.0x^9+2.0x^8+1.0x^7+10.0x^3-1.0x^2");
		assertEquals(str3, "1.0x^8");
	}
	@Test
	void testAdd() {
		double[] newP2 = Ex2.add(p1,p2);
		double[] newP3 = Ex2.add(p3, p3);
		
		String str1 = Ex2.poly(newP2);
		String str2 = Ex2.poly(newP3);
		
		assertEquals(str1, "1.0x^11-1.0x^9+2.0x^8+1.0x^7+9.0x^3+2.0x^2+2.0");
		assertEquals(str2, "2.0x^8");
		
	}
@Test
	void testMul() {
		double[] testP1, testP2, testP3;
		testP1 = Ex2.mul(p3, p3);   	// (x^8) * (x^8)
		testP2 = Ex2.mul(testP1, p1);	 //  x^16 * (-x^3 + 3x^2 + 2)
		testP3 = Ex2.mul(p4, p5); 		  //  (x^2 - 4) * (x^2 - 2x - 8)
		
		String testP1_str = Ex2.poly(testP1);
		String testP2_str = Ex2.poly(testP2);
		String testP3_str = Ex2.poly(testP3);
		
		assertEquals ( testP1_str , "1.0x^16" );
		assertEquals ( testP2_str , "-1.0x^19+3.0x^18+2.0x^16" );
		assertEquals ( testP3_str , "1.0x^4-2.0x^3-12.0x^2+8.0x^1+32.0" );
		
//		System.out.println(testP1_str);
//		System.out.println(testP2_str);
//		System.out.println(testP3_str);
		
	}
@Test
	void testRoot()
	{
	double root1 = Ex2.root(p4, -10, 1, eps);
	int root1_cast = (int)(root1-0.5);
	assertEquals(root1_cast, -2);
	
	double root2 = Ex2.root(p5, 0, 10, eps);
	int root2_cast = (int)(root2+0.5);
	assertEquals(root2_cast, 4);
	
	double root3 = Ex2.root(p6, 2, 5, eps);
	int root3_cast = (int)(root3+0.5);
	assertEquals(root3_cast, 4);
	
	}
@Test
	void testDerivative()
	{
	
	String p1_test = Ex2.poly(Ex2.derivative(p1));
	String p2_test = Ex2.poly(Ex2.derivative(p2));
	String p3_test = Ex2.poly(Ex2.derivative(p3));
	String p4_test = Ex2.poly(Ex2.derivative(p4));
	String p5_test = Ex2.poly(Ex2.derivative(p5));
	String p6_test = Ex2.poly(Ex2.derivative(p6));
	
	
	// String tests
	assertEquals(p1_test,"-3.0x^2+6.0x^1");
	assertEquals(p2_test,"11.0x^10-9.0x^8+16.0x^7+7.0x^6+30.0x^2-2.0x^1");
	assertEquals(p3_test,"8.0x^7");
	assertEquals(p4_test,"2.0x^1");
	assertEquals(p5_test,"2.0x^1-2.0");
	assertEquals(p6_test,"3.0x^2-14.0x^1+2.0");
	
	
	// Value by value tests
	double[] p1 = { 2 , 4 , 6 , 8 } ; // 8x^3 + 6x^2 + 4x + 2 
	double[] dp1 = {  4 , 12 , 24 } ; // 24x^2 + 12x + 4
	double[] dp1_after = Ex2.derivative(p1);
	
	for ( int i = 0 ; i < dp1.length; i++)
	{	assertEquals (dp1[i] , dp1_after[i]); 	}
	}
}
