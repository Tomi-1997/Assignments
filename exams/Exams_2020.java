package exams;
import ex4.geometry.Point2D;

public class Exams_2020 {
	
	public static void main(String[]args)
	{
		for (int i = 2;i <100; i++)
		{
			if (semi(i)) System.out.print(i + "  ");
		}
	}
	
	public static boolean semi(int n)
	{
		int count = 0;
		int z = 2;
		while (n > 1)
		{
			if (n % z == 0)
			{
				count++;
				n = n/z;
			}
			else z++;
		}
		return count == 2;
	}
	
	public static void spiral(int [][] a )
	{
		int z = 0;
		int count = 0;
		while (count <= a.length * a.length)
		{
			for (int i = 0 + z; i<a.length - z; i++)
			{
				System.out.print(a[0 + z][i]+"  ");
				count++;
			}
			if (count > a.length * a.length)
				break;
			
			for (int i = 1 + z ; i < a.length - 1; i++)
			{
				System.out.print(a[i][a.length - 1 - z] + "  ");
				count++;
			}
			if (count > a.length * a.length)
				break;
			
			for (int i = a.length - 1 - z; i>=1 + z; i--)
			{
				System.out.print(a[a.length - 1  - z][i]+"  ");
				count++;
			}
			if (count > a.length * a.length)
				break;
			
			for (int i = a.length - 1 - z; i>= 1 + z; i--)
			{
				System.out.print(a[i][z]+"  ");
				count++;
			}
			z++;
		}
		
	}
	

	/**
	 * given a string of only S or R 
	 * returns number of sub strings which have the same number of S or R 
	 * @param s
	 * @return
	 */
	public static int balanced(String s)
	{
		if (s.length() < 1)
			return 0;
		
		int count = 0;
		int R_count = 0;
		int S_count = 0;
		
		int i = 0;
		while (i < s.length())
		{
			char z = s.charAt(i);
			
			if (z == 'R')
				R_count++;
			else
				S_count++;
			
			if (R_count == S_count)
				count++;
			
			i++;
		}
		
		
		
		
		return count;
	}
	
	/**
	 * Returns true if a number divides both p and p squared for all p he divides
	 * for example returns true for 1225 which is 5 * 5 * 7 * 7
	 * also returns false for 175 which is 7 * 7 * 5, which divides both 7 and 49 but 
	 * does not divide 25
	 * @param n
	 * @return
	 */
	public static boolean powerful(int n)
	{
		for(int i = 2; i <= n/2; i++)
		{
			if (n%i == 0 && isPrime(i) && n % (i*i) != 0)
				return false;
		}
		return true;
	}
	
	public static boolean isMagicSquare(int[][] a)
	{
		int n = a.length;
		int sum = 0;
		int sum_next = 0;
		
		for (int i = 0; i<a.length; i++)
			sum = sum + a[0][i];
		
		for (int i = 0; i < a.length; i++)
		{
			if (a[i].length != n ) 
				return false;
			
			for (int j = 0; j < a[i].length; j++)
			{
				sum_next = sum_next + a[i][j];
			}
			if (sum != sum_next) return false;
			sum_next = 0;
		}
		
		for (int i = 0, j = 0; i<a.length; i++, j++)
			sum_next = sum_next + a[i][j];
		
		if (sum != sum_next) return false;
		
		return true;
	}

	public static String longestCommonSequence(String shrt, String t)
	{
		String temp = "";
		String maxLength = "";
		if (shrt.length() > t.length())
			return longestCommonSequence(t, shrt);
		
		/*we start assuming t has the highest or equal length*/
		for (int i=0; i<t.length(); i++)
		{	
			char z = t.charAt(i);
			for (int j = 0; j < shrt.length(); j++)
			{
				char z_s = shrt.charAt(j);
				
				/* if the first string has a letter which the second string also have,
				 * we test from the specific index forward if they have a matching substring */
				if (z == z_s)
				{
					/*Sub for to run from the moment both strings share a character and break
					 * at the moment they do not*/
					for (int k = i, m = j; k < t.length() && m < shrt.length(); m++, k++)
					{
						z = t.charAt(k);
						z_s = shrt.charAt(m);
						if (z!= z_s)
							break;
						else
							temp = temp + z;
					}
				if (temp.length() > maxLength.length())
					maxLength = temp;
				//
					temp = "";
					
				}
			}
				
		}
		
		return maxLength;
	}
	
	public static boolean sphenic(int n )
	{
		String multipliers = "";
		int count = 0;
		for (int i = 2; i<n; i++)
		{
			if (n % i == 0 && isPrime(i))
				{
				count++;
				multipliers = multipliers + " "+ i;
				}
		}
		if (count == 3)
			System.out.print(multipliers+"  ");
		return count == 3;
	}
	
	private static boolean isPrime(int num) {
		if (num<2) return false;
		
		for (int i = 2; i <= Math.sqrt(num); i++)
			if (num % i == 0) return false;
		return true;
	}

	public static boolean horizontal(int[][] a)
	{
		if (a.length % 2 == 1 ) return false;
		
		int top_half = 0;
		int bottom_half = 0;
		
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j<a[i].length; j++)
			{
				if (i<a.length/2)
					top_half = top_half + a[i][j];
				else
					bottom_half = bottom_half + a[i][j];
			}
		}
		
		return top_half == bottom_half;
	}
	
	public static String single(String s)
	{
		String sl = "";
		if (s.isBlank()) return sl;
		
		while(s.length() > 0)
		{
			/*we take z as the first character of s and remove it from s*/
		char z = s.charAt(0);
		s = s.substring(1);
		
			/*if the character z is, was the only one we add it to the output*/
		if (!s.contains(z+""))
			sl = sl + z;
		else
			s = s.replaceAll(z+"", "");
		}
	
		return sl;
	}
	
	public static boolean symmetric(int[][]a, int[][] b)
	{
		if (a.length != b.length) return false;
		
		return symmetricX(a,b) || symmetricY(a,b);
	}
	
	private static boolean symmetricY(int[][] a, int[][] b)
	{
		for (int i = 0; i<a.length; i++)
		{
			for (int j = 0; j<a[i].length; j++)
			{
				if (a[i][j] != b[i][a[i].length - 1 - j])
					return false;
			}
		}
		return true;
	}

	private static boolean symmetricX(int[][] a, int[][] b) 
	{
		for (int i = 0; i<a.length; i++)
		{
			for (int j = 0; j<a[i].length; j++)
			{
				if (a[i][j] != b[a.length - i - 1][j])
					return false;
			}
		}
		return true;
	}
	
	public static String reduce(String st)
	{
		if (st.length()>1)
		{
			if (st.charAt(0) == st.charAt(1))
				return reduce(st.substring(1, st.length()));

				return st.charAt(0) + reduce(st.substring(1, st.length()));
		}
		
		return st.charAt(0)+"";
	}
	
	public static boolean square(int a)
	{
		int sum = 1 + a*a;
		for (int i = 2; i<= a/2; i++)
			if (a%i == 0) sum = sum + i*i;
		
		return Math.sqrt(sum) == (int)(Math.sqrt(sum));
	}
	
	public static String decimal2Binary(int a) 
	{
		String a_base2 = "";
		int r = a%2;
		a_base2 = r + a_base2;
		a = a / 2;
		
		while (a>0)
		{
			r = a%2;
			a_base2 = r + a_base2;
			a = a / 2;
		}
		
		return a_base2;
	}
		
	public static boolean sameNumbers(int[][]mat)
	{
		for (int i=0; i<mat.length-1; i++)
		{
			for (int j=0; j<mat[i].length-1; j++)
			{
				if (mat[i][j] != mat[i+1][j+1])
					return false;
			}
		}
		
		return true;
	}
		
	public static int isIn(int[]a, int b[]) {
		if (a.length > b.length || a.length == 0) 
			return -1;
		
		boolean in = true;
		int c = 0;
		for (int i = 0; i<b.length - a.length + 1; i++)
		{
			if (b[i] == a[0])
			{
				for (int j=0; j<a.length; j++)
				{
					if (b[i+j] != a[j])
						{ 
						in = false;
						break;
						}
				}
				
				if (in) 
					c++;
				else in = true;
			}
		}
		
		return c;
	}
		
	public static int Binary2Dec(String s)
	{
		boolean valid = true;
		int decimal = 0;
		
		for(int i = 0; i < s.length(); i++)
		{
			try {
				/*Try to convert the string into a digits*/
			int z = Integer.parseInt(s.charAt(i)+"");
			
				/*In case there is a digit higher than 1*/
			if (z != 0 && z!= 1)
			{
				valid = false;
				break;
			}
				/*Valid input
			 	if there is a 1 we add 2 to the power of index*/
			else if (z == 1)
			{
				decimal = decimal + (int) Math.pow(2, s.length() - i - 1);
			}
				}
			catch (java.lang.NumberFormatException e)
			{
				/*If it contains a letter which isn't a digit
				 	the input is invalid*/
				valid = false;
				break;
			}
	
		}
		if (valid) 
			return decimal;
		
		return -1;
	}
	
	public static Object[] interlace(Object[] a, Object[]b)
	{
		int i=0, j =0;
		Object[] c = new Object[a.length + b.length];
		
		while(i<a.length || j<b.length)
		{
			if (i < a.length)
			{
				c[i+j] = a[i];
				i++;
			}
			
			/*Operate on the array which i or j have not passed the length yet */
			
			if (j < b.length)
			{
				c[i+j] = b[j];
				j++;
			}
		}
		return c;
	}
	
	/**quick sort for an array of points, sorting by distance to the point 0,0**/
	public static void pSort(Point2D[] p)
	{
		quick_p_Sort(p, 0, p.length-1);
	}
	
	private static void quick_p_Sort(Point2D[] p, int start, int end) {
		if (start < end)
		{
			int piv = partition_point2D(p, start, end);
			
			quick_p_Sort(p, start, piv - 1);
			quick_p_Sort(p, piv + 1 , end);
		}
		
	}
	
	private static int partition_point2D(Point2D[] p, int low, int high) {
		int pivot = high;
		int i = low - 1;
		
		for (int j = low; j<=high; j++)
		{
			if (p[j].distance() < p[pivot].distance())
			{
				i++;
				swap(p, i, j);
			}
		}
		swap (p, i+1, high);
		
		return i+1;
	}
	
	public static void swap(Point2D[] p, int i, int j) {
		Point2D temp = new Point2D(p[i]);
		p[i] = new Point2D(p[j]);
		p[j] = new Point2D(temp);
	}

}//e

