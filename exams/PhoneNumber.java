package exams;

public class PhoneNumber {
private String _cityCode;
private int _phoneNum;

public PhoneNumber(String cc, int pn)
{
	this._cityCode = cc;
	this._phoneNum = pn;
}

public PhoneNumber(PhoneNumber pn)
{
	this._cityCode = pn._cityCode;
	this._phoneNum = pn._phoneNum;
}

public boolean checkInput()
{
	return is_ccValid() && is_pnValid();
}

public boolean is_pnValid()
{
	int digits = 0;
	int pn = this._phoneNum;
	
	while (pn > 0)
	{
		digits++;
		pn = pn / 10;
	}
	
	return digits <= 7;
}

public boolean is_ccValid()
{
	try 
	{
	Integer.parseInt(this._cityCode);	
	}
	catch (NumberFormatException e)
	{
	return false;
	}
	
	return this._cityCode.length() == 2;	
}

public String toString()
{
	return this._cityCode+"-"+this._phoneNum;
}

public static void main(String[] args)
{
	PhoneNumber pn  = new PhoneNumber("22", 1234567);
	System.out.println(pn);
}

}
