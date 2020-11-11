package Ex1;

public class Ex1 {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
int a,b;
a=Integer.parseInt(args[0]);
b=Integer.parseInt(args[1]);
System.out.println("Input is: ["+a+", "+b+"]");

int min=Math.min(a, b);
//System.out.println("Smallest of them is: "+min);

int index=min;
int answer=1;
boolean no_ans_yet=true;

//System.out.println(isPrime(33333331));
while ((index>=2)&&(no_ans_yet))
{
	if (( a % index == 0 ) 
		&& ( b % index == 0)
		&& (isPrime(index)) )
	{
		answer = index;
		no_ans_yet = false;
	}
	index = index - 1;
}

if (!no_ans_yet)
{ 
System.out.println("Greatest prime divisor is: "+answer+".");
}
else
{
System.out.println("Undivisible!");

}
}
	
	public static boolean isPrime (int number)
	{
		int i=number/2;
		while (i>1)
		{
			if ( number % i == 0 )
			{
				return false;
			}
			i=i-1;
		}
		return true;
	}
	
}
