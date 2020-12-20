
public class Ex3 {
	
	public static String myID() {
		return "";
	}
	
	//
	
    	/**
    * This function receives a string [str] and a character [ch],
    * and returns the string with all occurrences of [ch] inside [str] 
    * to the end of the string via recursion.
    * for example, given "shalom", and 'l' returns "shaoml"
    * 
    * author Tom Inbar
    * @param str
    * @param ch
    * @return
    */
	public static String moveCharToLast(String str, char ch)
	{
		if (str.length() < 1) return ""; // Empty string
		if (str.length() == 1) return str; // Last character
		
		/* if the character at the start of the string is equal
		 * to the given ch, the function returns via recursion with
		 * the string one place further + the ch at the end
		 */
		if (str.charAt(0)==ch){ 
			return moveCharToLast(str.substring(1), ch)+ch;
		}
		
		/*if the character at the start of the string is not equal
		 * to the given ch it returns the first character of the string
		 * and also returns via recursion to the function with 
		 * the same string, one place further.
		 * (basically, does not change anything but shortens the string
		 * to reach the end of the recursion)
		 */
		return str.charAt(0) + moveCharToLast(str.substring(1) ,ch);
	}
	
	/**
	 * This function receives a string [str] and returns the same string
	 * with no repeats of any character in the string via recursion.
	 * for example, given "shalllllllllommmm", return "shalom"
	 * 
	 * author Tom Inbar 
	 * @param str
	 * @return
	 */
	public static String reduce(String str)
	{
		if (str.length() < 1) return ""; // incase of an empty string
		if (str.length() == 1) return str; //Last character
		
		/* if the current character is the same as the next character
		 * the function returns via recursion back to the function 
		 * as a substring one place further, basically cutting one of the characters.
		 */
		if (str.charAt(0) == str.charAt(1))
		{
			return reduce(str.substring(1));
		}
		
		/*
		 * if the current character is different than the next character,
		 * the function returns the first character, and recurses back with a
		 * substring one place further in the string
		 * basically does not remove any characters.
		 */
		else return str.charAt(0)+reduce(str.substring(1));
	}
	
	/**
	 * This function is given an array and returns true if it has two sub arrays,
	 * when one has all multiples of 5,
	 * and the other multiples of 3 (no multiples of 5, i.e 30, 15), 
	 * and the sum of their entries is equal.
	 * (while no entries from main array are leftover without being in a sub array**)
	 * 
	 * i.e for an array {30, 27, 3} returns true, since it contains {27 , 3} and {30}
	 * another example, for an array {5,3,3} returns false as {3,3} and {5} don't sum up to the same value
	 * 
	 * Author Tom Inbar 
	 * @param nums
	 * @return
	 */
	public static boolean mySplit(int[] nums)
	{
		if (nums.length < 2)
			return false;
		/*
		 * Divide 'nums' into three arrays, 
		 * array nums1 contains all multiples of five (** no common dividers of 5 and 3)
		 * array nums2 contains all multiples of three
		 * array nums contains all the leftovers
		 * i.e for 'nums' = { 2 , 3 , 5}
		 * nums 1 = { 0 , 3 , 5 }
		 * nums 2 = { 0 , 0 , 5 }
		 * nums = { 2 , 0 , 0 }
		 */
		int[] nums1 = new int[nums.length];
		int[] nums2 = new int[nums.length];
		divideTo(nums, nums1, nums2); 

		/*
		 * Gets rid of leftover 0's 
		 * i.e { 0, 0, 0, 3 } turns into { 3 }
		 */
		nums = shorten(nums);
		nums1 = shorten(nums1);
		nums2 = shorten(nums2);
				
		/*
		 * The recursive function 'check_and_split'checks if the arrays fulfills our condition,
		 * if not, we split only 'nums' [the leftovers] and check again, and again
		 * until nums reach the length of one entry, for which we check one final time.
		 */
		return check_and_split(nums, nums1, nums2);
	}

	/**
	 * This function returns a new two dimensional array made by assigning each entry the value
	 * of the sum of it's neighboring entries. minimum neighbors are 3, max neighbors are 8.
	 * 
	 * for example: for the matrice [1 2]
	 * 								[3 4]
	 * 
	 * the function will return     [9 8]
	 * 		  this matrice	[7 6]
	 * 
	 * As around 1, it sums up 2+3+4, around 2 are 1+3+4, around 3 are 1+2+4, and around 4 are 1+2+3.
	 * 
	 * author Tom Inbar ID
	 * @param mat
	 * @return
	 */	
	public static int[][] SumOfNeighbours(int [][]mat)
	{
		int [][] new_mat = new int[mat.length][mat[0].length];
		
		for (int i=0; i<mat.length; i++)
		{
			for (int j=0; j<mat[0].length; j++)
			{
				/*
				 * This function sums all nearby entries of mat[i][j], except mat[i][j] itself.
				 * Using a double for ranging from (i/j - 1) to (i/j + 1) using try{} and catch{}
				 * to avoid index out of bounds error.
				 */
				new_mat[i][j] = sumNearby(mat, i, j);
			}
		}
		
		return new_mat;
	}
	
	/** This function receives a string and moves each letter to x number of steps
	 * to the left or right, depending on the given key.
	 * For example given string "abcd" and
	 * key = 1, the function will return "bcdf". 
	 * for key = 2, the function will return "cdef".
	 * 
	 * for a negative value key, each letter will move some steps to the left, for a
	 * positive key, each letter will move some steps to the right.
	 * 
	 * author Tom Inbar ID
	 * @param str
	 * @param key
	 * @return
	 */
	public static String caesarCipherText(String str, int key)
	{
		
		if (!(key >= 0 || key < 0))
			return "INVALID KEY"; // Checking if it is invalid.
		
		/*
		 * We start with an empty string and add each letter within the number range
		 * of the lowercase abc. 
		 */
		String decoded = "";
		for (int i=0;i<str.length();i++)
		{
			if (str.charAt(i) != (' ') )
			{
				if ( (char) (str.charAt(i) + key) > 122 || (char) (str.charAt(i) + key) < 97)
					decoded = decoded + keepInRange(str.charAt(i), key);
				// Keeps it between the ASCII range for lowercase letters
				else
					decoded = decoded + (char)(str.charAt(i)+key);
			}
			/*
			 * If the letter at the current index is blank, we add just blank space.
			 */
			else
				decoded = decoded +" ";
		}
				
		return decoded;
	}
	
	/**
	 * This function is given two strings, first string [str] to cipher and a string [key]
     * to cipher [str] with, based on [key]'s letters. the function returns the ciphered string.
     * The function sums each letter of [str] with a letter of [key] and thus making a ciphered string.
     * 
     * for example:
     * 'str' = "cba"
     * 'key' = "abc" ---> 'c' will be pushed by 0, 'b' will be pushed by '1', 'a' will be pushed by '2'.
     * thus the deciphered string will be "ccc".
     * after looping key's letters, it will return to the first letter, in this case to 'a'.
     *  
     * author Tom Inbar 
	 * @param str
	 * @param key
	 * @return
	 */
	public static String vigenereCipherText(String str, String key)
	{
		String decoded = "";
		int j = 0;
		/*
		 * j will be the index for our key.
		 * we will set j to be j % key's length as to loop the key's letters over and over.
		 * for example, for the key "honor", thanks to j % key.length() line j will always go
		 * from 0 to 1 to 2 ... 4 and will loop back to 0 when it reaches 5. 
		 */
		for (int i = 0; i<str.length(); i++)
		{
				j = j % key.length();
			if (str.charAt(i)!= ' ')
			{
				/*
				 * The lower case letters for ASCII starts at 97 and ends at 122, that is why
				 * we subtract from our key 97, so for the letter 'a' it will add 0, for 'b'
				 * it will add 1, for 'h' it will add 7 so on. Again we make use of our KeepInRange 
				 * function, to sum the string's letter at the 'i' index and the key's letter at 
				 * the 'j' index.
				 * Inside the function we also make sure to loop in case the summation
				 * flows above 122, or below 97.
				 */
				decoded = decoded + keepInRange(str.charAt(i),(int)(key.charAt(j) - 97));
			}
			else
			{
				decoded = decoded + " ";
			}
			/*
			 * we advance the j index even though our letter from 'str' at the i index might be a blank space.
			 */
			j++;
		}
		return decoded;
	}
	
	/**
	 * This function is given two strings, first string [str] to decipher and a string [key]
     * to decipher [str] with, based on [key]'s letters. the function returns the deciphered string.
     * The function subtracts each letter of [str] the letter at the j index of [key] and thus deciphering it.
     * 
     * For example, for our given ciphered "ccc" with the key "abc":
     * 'c' will be pushed BACK by 0, the second 'c' will be pushed back by 1, the third 'c' will be pushed back by 3,
     * and so our deciphered string will be "cba", exactly the original string before ciphering it with our previous function.
     * 
     * author Tom Inbar
	 * @param str
	 * @param key
	 * @return
	 */
	public static String vigenereDecipherText(String str, String key)
	{
		/**
		 * This is almost the same as the decipher function, except the marked line below.
		 */
		String uncoded = "";
		int j = 0;
		
		for (int i = 0; i<str.length(); i++)
		{
				j = j % key.length();
			if (str.charAt(i)!= ' ')
			{
				/*
				 * In this line is the main difference between the cipher function and this one.
				 * Instead of adding the ASCII value of the character of the 'key' at the j index,
				 * we subtract it, basically deciphering our given 'str'.
				 */
				uncoded = uncoded + keepInRange(str.charAt(i), - (int)(key.charAt(j) - 97));
			}
			else
			{
				uncoded = uncoded + " ";
			}
			j++;
		}
		return uncoded;
	}
	

	//
	
		//Helping functions
	/**
	 * Prints given array such as [x1, x2, x3,....xn]
	 * @param arr
	 */
	public static void printArr(int[] arr)
	{
		/*
		 * Simple function to print an array 
		 * i.e "[x1 , x2 , x3 ...]"
		 */
		if (arr.length > 0) {
		int i=0;
		System.out.print("["+arr[i]);
		i++;
		for (; i<arr.length; i++)
		{
			System.out.print(","+arr[i]);
		}
		System.out.println("]");
		}
		}
	
	/**
	 * This function is given an integer array and returns the sum of all values
	 * @param arr
	 * @return
	 */
	public static int sumArray(int[] arr)
	{
		/*
		 * Simple function to sum the value
		 * of all of the array's entries using a for loop
		 */
		int sum = 0;
		for(int i=0;i<arr.length;i++)
		{
			sum = sum + arr[i];
		}
		return sum;
	}

	/**
	 * This function returns true if two arrays have the same sum, when combining the value
	 * of all entities
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static int sumArrays(int []arr1, int[] arr2)
	{
		/*
		 * using sumArray function, this function returns the sum of two arrays.
		 */
		return sumArray(arr1) + sumArray(arr2);
	}
	
	/**
	 * Returns a new array made from the given array from the starting point
	 * 'start', up to ending point 'end'.
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static int[] getSubArray(int[] arr, int start, int end)
	{
		/*
		 * This function makes a new area, from the index 'start', to the index 'end'.
		 */
		int[] new_arr = new int[end - start];
		for (int i=0; i<new_arr.length; i++)
		{
			new_arr[i] = arr[start];
			start++;
		}
		return new_arr;
	}

	/**
	 * Returns true if a the sum of a given array 'left' + another final array1 is equal to final array2, 
	 * or vice versa,
	 * Also returns true if a the sum of a given array 'right' + another final array1 
	 * is equal to final array2, or vice versa.
	 * @param nums
	 * @param nums1_final
	 * @param nums2_final
	 * @return
	 */
	public static boolean condition(int[] left, int[] right,int[] nums1_final,int[] nums2_final)
	{
		/*
		 * This function returns true if the array fulfills our condition.
		 * The condition is 'can we make a two sub arrays from a big given array when one sub array
		 * has all multiples of five, and the other all multiples of three,
		 * with the same sum of all entries?
		 * 
		 * This function is to check if our array with leftover numbers will fulfill it
		 * after being divided to two arrays over and over.
		 */
		return sumArrays(left, nums1_final) == sumArrays(nums2_final, right)
				||
				  sumArrays(left, nums2_final) == sumArrays(nums1_final, right);

	}
	
	/**
	 * Returns true if the sum of a given array + final array1 is equal to a final array2,
	 * and the opposite 
	 * @param nums
	 * @param nums1_final
	 * @param nums2_final
	 * @return
	 */
	public static boolean condition(int[] nums, int[] nums1_final,int[] nums2_final)
	{
		/*
		 * This function returns true if the array fulfills our condition.
		 * The condition is 'can we make a two sub arrays from a big given array when one sub array
		 * has all multiples of five, and the other all multiples of three,
		 * with the same sum of all entries?
		 */
		return sumArray(nums) + sumArray(nums1_final) == sumArray(nums2_final)
				|| sumArray(nums) + sumArray(nums2_final) == sumArray(nums1_final);
		
	}
	
	/**
	 * This functions divides a given array 'nums', with 'nums1' containing all multiples of 5,
	 * 'nums2' containing all multiples of 3 and leaving the leftovers at 'nums'
	 * @param nums
	 * @param nums1
	 * @param nums2
	 */
	public static void divideTo(int[] nums, int[] nums1,int[] nums2)
	{
		/*
		 * This function divides a given array into three different arrays
		 * nums1 which has all the multiples of five
		 * nums2 which has all the multiples of three
		 * nums is left unchanged, basically has all other numbers
		 */
		int j=0;
		for (int i=0; i<nums.length; i++)
		{
			if (nums[i] % 5 == 0)
			{
			nums1[i] = nums[i];
			nums[i] = 0;
			}
			j++;
		}
		for (int i=0; i<nums.length; i++)
		{
			if (nums[i] % 3 == 0 && nums[i] != 0)
			{
			nums2[i] = nums[i];
			nums[i] = 0;
			}
		}
		
	}
	
	/**
	 * This function returns a new array without any 0 values
	 * @param arr
	 * @return
	 */
	public static int[] shorten(int[] arr)
	{
		/*
		 * This function returns a new array, without any entries with the value 0,
		 * with length <= of the length it was given.
		 */
		int c = 0;
		for (int i=0; i<arr.length; i++)
		{
			if (arr[i] != 0)
				c++;
		}
		
		int[] new_arr = new int[c];
		int j = 0;
		for (int i=0; i<arr.length && j<c ;i++)
		{
			if (arr[i] != 0)
				{
				new_arr[j] = arr[i];
				j++;
				}
		}
		
		return new_arr;
	}
	
	public static boolean check_and_split(int[] nums, int[] nums1_final, int[] nums2_final)
	{
		if (nums.length > 1)
		{
			int[] left = getSubArray(nums , 0 , nums.length/2);
			int[] right = getSubArray(nums , nums.length/2 , nums.length);
			
			return condition(left, right ,nums1_final, nums2_final)
						||	( check_and_split(left, nums1_final, nums2_final)
							||	check_and_split(right, nums1_final, nums2_final) )
								|| condition(nums, nums1_final, nums2_final);
		}
		/*
		 * The exit condition, checks if nums1_final + nums is equal to nums2_final,
		 * or nums2_final + nums is equal to nums1_final
		 * I call them final because after the first division into three different arrays
		 *  in mySplit, they are not changed throughout the program 
		 */
		return condition(nums, nums1_final, nums2_final);
	}

	public static char keepInRange(char charAt, int key) {
		/*
		 * This function receives a character and a key which made it go outside the range
		 * for the ASCII small letters, and corrects it by adding or subtracting the number
		 * of overall letters.
		 */
		char a = (char) (charAt + key);
		
		while ( a > 122 || a < 97)
		{
			if ( a > 122) a = (char) (a - 26);
			if ( a < 97) a = (char) (a + 26);
		}
		
		return a;
	}

	public static int sumNearby(int[][] mat, int x, int y) {
		int sum = 0;
		
		for (int i=(x-1);i<=(x+1); i++)
		{
			for (int j=(y-1); j<=(y+1); j++)
			{
				/*
				 * Sum all neighboring entries (except our given mat[x][y])
				 * and if there is an index out of bounds error,
				 * continue to the next j iteration.
				 */
				try {
					if ( !((i == x) && (j == y)) ) 
						sum = sum + mat[i][j];
					}
					
					catch (ArrayIndexOutOfBoundsException e)
					{
						continue;
					}
			}
		}
		return sum;
	}
	
}//

