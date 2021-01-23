package exams;

public class Line_v2 {
	
	private int _X1;
	private int _Y1;
	private int _X2;
	private int	_Y2;

	public Line_v2(int x1, int y1, int x2, int y2)
	{
		this._X1 = x1;
		this._Y1 = y1;
		this._X2 = x2;
		this._Y2 = y2;
	}
	
	public Line_v2(Line_v2 ol)
	{
		this._X1 = ol._X1;
		this._Y1 = ol._Y1;
		this._X2 = ol._X2;
		this._Y2 = ol._Y2;
	}
	
	public double length()
	{
		double deltax = this._X1 - this._X2;
		deltax = deltax * deltax;
		
		double deltay = this._Y1 - this._Y2;
		deltay = deltay * deltay;
		
		return Math.sqrt(deltax + deltay);
	}

	public boolean isIn(int x, int y)
	{
		double eps = 0.0000001;
		
		Line_v2 l1 = new Line_v2 (this._X1, this._Y1 , x , y);
		Line_v2 l2 = new Line_v2 (x , y , this._X2, this._Y2);
		
		double sum = l1.length() + l2.length();

		return Math.abs(this.length() - sum) < eps;
	}

	public static void main(String[]args)
	{
		int z = 10;
		Line_v2 l1 = new Line_v2 (0, 0 , z , z);
		for (int i = 0; i<z; i++)
		{
			System.out.print(i+") "+l1.isIn(z, z)+"  ");
		}
	}
}
