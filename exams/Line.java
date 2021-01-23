package exams;

public class Line {
	private int _X1, _Y1;
	private int _X2, _Y2;
	
	public Line() 
	{
		this._X1 = 0;
		this._Y1 = 0;
		this._X2 = 0;
		this._Y2 = 0;
	}
	
	/**
	 * Constructor of a line with two points.
	 * a,b - x,y of point 1
	 * c,d - x,y of point 2
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 */
	public Line(int a, int b , int c , int d)
	{
		this._X1 = a;
		this._Y1 = b;
		this._X2 = c;
		this._Y2 = d;
	}
	
	public double length()
	{
		double deltaX = this._X1 - this._X2;
		deltaX = deltaX * deltaX;
		
		double deltaY = this._Y1 - this._Y2;
		deltaY = deltaY * deltaY;
		
		return Math.sqrt(deltaX+deltaY);
	}
	
	public boolean isIn(int x, int y)
	{
		Line l1 = new Line(this._X1, this._Y1, x , y);
		Line l2 = new Line(this._X2, this._Y2, x , y);
		
		return this.length() == l1.length() + l2.length();
	}
}
