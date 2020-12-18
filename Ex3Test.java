import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Ex3Test {
String str1 = "";
String str2 = "hello";
String str3 = "aaabbccccxxxyzz";
String str4 = "very long string in order to zzzzzzzzz test my fffffffffunctions";
String str5 = "ppuubblliicc ssttaattiicc vvooiidd mmaaiinn";

	@Test
	void moveCharToLast() {
		String str1_charToLast = Ex3.moveCharToLast(str1, 'z');
		String str2_charToLast = Ex3.moveCharToLast(str2, 'l');
		String str3_charToLast = Ex3.moveCharToLast(str3, 'c');
		String str4_charToLast = Ex3.moveCharToLast(str4, 'z');
		
		int t1 = str1_charToLast.compareTo("");
		int t2 = str2_charToLast.compareTo("heoll");
		int t3 = str3_charToLast.compareTo("aaabbxxxyzzcccc");
		int t4 = str4_charToLast.compareTo("very long string in order to  test my fffffffffunctionszzzzzzzzz");
		
		assertEquals(t1,0);
		assertEquals(t2,0);
		assertEquals(t3,0);
		assertEquals(t4,0);
		
		assertEquals(str1_charToLast,"");
		assertEquals(str2_charToLast,"heoll");
		assertEquals(str3_charToLast,"aaabbxxxyzzcccc");
		assertEquals(str4_charToLast,"very long string in order to  test my fffffffffunctionszzzzzzzzz");
	}
	@Test
	void reduce() {
		String str1_reduced = Ex3.reduce(str1);
		String str2_reduced = Ex3.reduce(str2);
		String str3_reduced = Ex3.reduce(str3);
		String str4_reduced = Ex3.reduce(str4);
		String str5_reduced = Ex3.reduce(str5);
		
		assertEquals(str1_reduced,"");
		assertEquals(str2_reduced,"helo");
		assertEquals(str3_reduced,"abcxyz");
		assertEquals(str4_reduced,"very long string in order to z test my functions");
		assertEquals(str5_reduced,"public static void main");

	}
	@Test
	void SumOfNeighbours()
	{
		int [][] nums1 = { {0,0}, {0,0} };
		int [][] nums2 = { {1,2} , {3,4} };
		int [][] nums3 = { {3,-4,9}, {5,2,-50}, {7,10,3}, {5,11,60} };
		
		int [][] nums1_expected = { {0,0}, {0,0} };
		int [][] nums2_expected = { {9,8} , {7,6} };
		int [][] nums3_expected = { {3,-31,-52}, {18,-17,20}, {33,43,33}, {28,85,24} };
		
		assertTrue(areEqual(Ex3.SumOfNeighbours(nums1), nums1_expected));
		assertTrue(areEqual(Ex3.SumOfNeighbours(nums2), nums2_expected));
		assertTrue(areEqual(Ex3.SumOfNeighbours(nums3), nums3_expected));
	}
	@Test
	void MySplit()
	{
		int[] nums1 = { 5 , 21 , 8 , 7  , 15 }; // {15,5,8} {21,7}
		int[] nums2 = { 2, 3, 5}; 		    // {2,3} {5}
		int[] nums3 = {333, 330 , 4 , 1};       // {{333, 1} {330 , 4}
		int[] nums4 = {900003,900000,1,1,1}; //
		int[] nums5 = {27,3,267,3,297,3,600,333,300,30,1,1,1,3000,3003,1,1,1,6000,6003,2,1,3333,3330,1,1,1};
		//messy array to test it further
		int[] nums6 = {900003,900000,1,1,1,1}; // {900003} {900000, 1,1,1} with {1} left over should be false
		int[] nums7 = { 2, 3 , 5 , 3 }; // {3,3} {5} leftover {2} should be false 
		
		assertTrue(Ex3.mySplit(nums1));
		assertTrue(Ex3.mySplit(nums2));
		assertTrue(Ex3.mySplit(nums3));
		assertTrue(Ex3.mySplit(nums4));
		assertTrue(Ex3.mySplit(nums5));
		assertFalse(Ex3.mySplit(nums6));
		assertFalse(Ex3.mySplit(nums7));
	}
	@Test
	void caesarCipherText()
	{
		String str1 = "defend the eastern wall";
		String str2 = "test this caeser cipher text";
		String str3 = "a b c d e f g";
		String str4 = "tomorrow it might rain";
		
		String str1_coded = "hijirh xli iewxivr aepp";
		assertEquals(Ex3.caesarCipherText(str1, 4), str1_coded);
		
		String str2_coded = "docd drsc mkocob mszrob dohd";
		assertEquals(Ex3.caesarCipherText(str2, 10), str2_coded);
		
		String str3_coded = "b c d e f g h";
		assertEquals(Ex3.caesarCipherText(str3, 1), str3_coded);
		
		assertEquals(Ex3.caesarCipherText(str3, 27), str3_coded);
		
		String str4_coded = "xsqsvvsa mx qmklx vemr";
		assertEquals(Ex3.caesarCipherText(str4_coded, -4), str4);
		
	}
	@Test
	void vigenereCipherText()
	{
		String str1 = "impressive student from ariel university";
		String str2 = "a b c d e f g";
		String str3 = "my vigenere code ignores blank spaces";
		
		String key1 = "honor";
		String key2 = "bab";
		String key3 = "abcde";
		
		String expected1 = "pacfvzgvjv ggiulbg wycz rywrz bbvjvygvhp";
		String expected2 = "b c c e f f h";
		String expected3 = "mz ymgfphve erhe kjrosgv bmcqo trdget";;
		
		assertEquals(Ex3.vigenereCipherText(str1, key1) , expected1);
		assertEquals(Ex3.vigenereCipherText(str2, key2) , expected2);
		assertEquals(Ex3.vigenereCipherText(str3, key3) , expected3);
	}	
	@Test
	void vigenereDecipherText()
	{
		String str1 = "pacfvzgvjv ggiulbg wycz rywrz bbvjvygvhp";
		String str2 = "b c c e f f h";
		String str3 = "mz ymgfphve erhe kjrosgv bmcqo trdget";;
		
		String key1 = "honor";
		String key2 = "bab";
		String key3 = "abcde";
		
		String expected1 = "impressive student from ariel university";
		String expected2 = "a b c d e f g";
		String expected3 = "my vigenere code ignores blank spaces";
		
		assertEquals(Ex3.vigenereDecipherText(str1, key1) , expected1);
		assertEquals(Ex3.vigenereDecipherText(str2, key2) , expected2);
		assertEquals(Ex3.vigenereDecipherText(str3, key3) , expected3);
	}
	
	public boolean areEqual(int[][]nums1, int[][] nums2)
	{
		if (nums1.length != nums2.length || nums1[0].length != nums2[0].length) 
			return false;
		for (int i=0; i<nums1.length; i++)
		{
			for (int j=0; j<nums1[0].length; j++)
			{
			if (nums1[i][j] != nums2[i][j]) 
				return false;
			}
		}
		return true;
	}
}
