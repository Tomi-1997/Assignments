import java.util.Scanner;
public class Ex2 {

	public static void main(String[] args) {

		double eps = 0.001;
		//tests
		double[] p6 = { 6 , 5 , 1 };
		System.out.println(poly(p6));
		double root = root(p6, -2.5 , 100 , eps);

		System.out.println("Root is "+root);
		double[] p1 = { 2 , 0 , 3 , -1 , 0} ;
		System.out.println(poly(derivative(p1)));



		
	}
	

	// Calculates f ( x ) given function and value of X
public static double f(double []poly, double x) {
	double y = 0;
	for (int i = 0; i < poly.length; i++) 
	{
		y = poly[i] * Math.pow(x, i) + y;
	}
	return y;
}


//  Returns array as a function string [highest powers first]
public static String poly(double []poly)  
{
	String polygon = "";

	if (poly.length == 0) { return polygon ; }       // incase the array is empty    
	
	if (poly.length == 1) { 						 // incase it has a single monome
		if ((poly[0]!= 0)) 
		{ polygon = ""+poly[0]; } 
		return polygon; }     
       
	int i = poly.length-1;
	for ( ;i>0;i--)
	{
		if ( poly[i] > 0)
		polygon += "+"+poly[i]+"x^"+(i);              // positive monome
		if ( poly[i] < 0)                             // negative monome
		polygon += ""+poly[i]+"x^"+(i);
	}
	
	if ( poly[i] > 0)
	{	polygon += "+"+poly[i];      }               // positive monome
	if ( poly[i] < 0)                                // negative monome
	{	polygon += ""+poly[i];       }
 	
	char z = 0;
	if (polygon.length() > 0) 
	{    z = polygon.charAt(0)    ;}// Strips an extra '+' sign at the start
	if ( z == '+' ) {    polygon = polygon.substring(1, polygon.length());   }
	
	return polygon;
}


//returns a value of X that intersects the y plane between x1, x2 using eps as a tiny approximation.
// *** assuming f (poly, x ) * f (poly, x2) < = 0 ***X is a very rough approximation
public static double root (double []poly , double x1, double x2, double eps)
{
	if ( f(poly,x1) * f(poly,x2) > 0)
		{ 	System.out.println("Please enter valid x1, x2");	return 0; }
	
	double f;
	double mid = x1;
	while ( x2 - x1  >= eps)
	{
		mid  = (x1 + x2) / 2;      // middle point between x1, x2
		f = f(poly,mid);
		if ( f - eps == 0)           // if f(mid) is eps or less, it is the root
		{ return mid; }
		
		if (f(poly,mid) * f(poly,x1) <= 0 ) { x2 = mid; } 
		// check to decide which new points have the root between them
		
		else  if (f(poly,mid) * f(poly,x2) <= 0 ) { x1 = mid; }
	}
	return mid;
}


//returns a new array [new_p] by adding two polynoms [p1 + p2]
public static double[] add (double[] p1 , double[] p2)
{
	if (p2.length > p1.length)  // we start assuming p1 has the longer / equal length
	 { return add(p2, p1); }    // but if it is not the case, we return to the same function
								 // but now, we swap p1 to have the longest length
	int size = p1.length;
	double[] newP = new double[size];
	
	for ( int i=0; i<p2.length; i++)  // as p2 has a shorter length, we operate on p2 
	{ newP[i] = p1[i] + p2[i]; }		// first as not to have index out of bounds error
	
	if (p1.length != p2.length)
	{
		for ( int i=p2.length; i<p1.length; i++)
			{ newP[i] = newP[i] + p1[i];}				
	}

	return newP;
}

//returns a new array newP made by multiplying each array entry p1[i] by p2[j]
public static double[] mul(double[] p1, double[] p2) 
{
	if (p1.length < p2.length)  // we start assuming p1 has a longer length
		return mul(p2, p1);     // if not, we swap them
	
	
	if (p1.length == 1)      // incase first p1 is short [which means p2 is also short]
	{
		if (p2.length == 0) { return p1; }
		else
		{
		for (int i=0; i<p1.length; i++)
		{
			p1[i] = p1[i] * p2[0];
		}
		return p1; 
		}
	}
	
	//p1 highest power is x ^ (p.length) -1
	//p2 highest power is x ^ (p.length) -1
	//as a result of this, newP highest power is x^(( p1.length -1 ) + ( p2.length -1 ) + 1)
	int size = (p1.length + p2.length);
	size = size - 1;
	double[] newP = new double[size]; 
	
	for (int i=0; i<p1.length; i++)
	{
		for (int j=0; j<p2.length; j++)
		{
			if ( (p1[i] != 0) && (p2[j] != 0) )
			{
				newP[i+j] = ( p1[i] * p2[j] ) + newP[i+j]; 
			}				// i+j might reach the same value in more than one iteration
			
		}
	}
	
	
	return newP;
}

//returns a new array newP representing the derivative of a given polynom
public static double[] derivative (double[] p1)
{
	//newP's length is one less, as a derivative subtracts each power by one
	double[] newP = new double [p1.length - 1]; 
	
	//each element is multiplied by it's power, and it's power is subtracted by one
	//p1's first element is deleted. for example (2x+5) the 5 is gone
	for ( int i = 0 ; i<newP.length; i++)
	{		newP[i] = p1[i+1]*(i+1);		 }
	// newP's length is defined by p1.length -1, so hopefully 
	// try to reach an index of i+1 won't cause an out of bounds error
	return newP;
}
}
