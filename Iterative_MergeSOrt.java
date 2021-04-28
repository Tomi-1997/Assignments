// my first version of iterative merge sort
// **not memory friendly
	public static void mergeSort2(int[] arr) { 
		
		double sizeBaseTwo = logTwo(arr.length);
		if (sizeBaseTwo - (int)(logTwo(arr.length)) != 0)
				sizeBaseTwo = (int) (sizeBaseTwo+1);
		
		// make an array with the size of a power of two 
		// if the integer value of log2 of the size is equal to the 'double', then 
		// the length of the array is already a power of two.
		// if not, make an array with a power of two that is bigger than the current array.
		
		sizeBaseTwo= Math.pow(2, sizeBaseTwo);
		int[] temp = new int[(int)sizeBaseTwo]; 
		for(int i=0; i<temp.length;i++)
		{
			if (i<arr.length)
				temp[i]=arr[i];
			else
				temp[i]=Integer.MAX_VALUE;
		}

		
		// Merge every two neighboring elements, and then every four neighboring
		// so on until the two last halves of the array are merged.
		// array is guaranteed to divide into each power of two evenly as we built a new array 
		// for that purpose.
		
		int PowerOfTwo = 2;
    
    // last merge is when PowerOfTwo is equal temp.length
    // in which the two merged halves are merged into one whole bitch ass array
		while (PowerOfTwo <= temp.length)
		{
			for(int i=0; i<temp.length; i=i+PowerOfTwo)
			{
				merge(temp, i, i+PowerOfTwo/2 , i+PowerOfTwo);
			}
			PowerOfTwo = PowerOfTwo * 2;	
		}
    
		//copy into original array
		for (int i=0;i<arr.length;i++)
			arr[i]=temp[i];
      
	} // fin
	
	//Merges two sorted sub arrays into one
	private static void merge(int[] arr, int low,int mid ,int high) {
		int[] temp = new int[high-low];
		
		int i=low, j=mid;
		int z = 0;
		while(i<mid && j<high)
		{
			if(arr[i] < arr[j])
				temp[z++] = arr[i++];
			else
				temp[z++] = arr[j++];
		}
		
		while(i<mid)
			temp[z++] = arr[i++];
		
		while(j<high)
			temp[z++] = arr[j++];
		
		z=0;
	
		for(int k=low;k<high;k++)
			arr[k] = temp[z++];
	}
  
  // Log two of N
  private static double logTwo(int n) {
		return Math.log(n)/Math.log(2);
	}
