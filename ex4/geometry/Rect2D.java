package ex4.geometry;

/**
 * This class represents a two dimension rectangle in the plane.
 * Made by two edge points, top right, and bottom left.
 * @author Thomas
 *
 */
public class Rect2D implements GeoShape {

	private Point2D _A, _B;
	private final double EPS = 0.0000001;
	
	/**
	 * Rect2D Constructor with parameters.
	 * @param a
	 * @param b
	 */
	public Rect2D(Point2D a, Point2D b){
		this._A = new Point2D(a);
		this._B = new Point2D(b);
	}
	
	/**
	 * Rect2D default constructor.
	 */
	public Rect2D(){
		this._A = new Point2D(0,0);
		this._B = new Point2D(1,1);
	}

	/**
	 * Rect2D Copy constructor.
	 * @param rec
	 */
	public Rect2D(Rect2D rec){
		this._A = new Point2D(rec._A);
		this._B = new Point2D(rec._B);
	}
	
	/**
	 * A Get function
	 * @return
	 */
	public Point2D get_A() {
		return _A;
	}

	/**
	 * A Set function
	 * @return
	 */
	public void set_A(Point2D _A) {
		this._A = _A;
	}

	/**
	 * B Get function
	 * @return
	 */
	public Point2D get_B() {
		return _B;
	}

	/**
	 * B Set function
	 * @return
	 */
	public void set_B(Point2D _B) {
		this._B = _B;
	}
	
	/**
	 * Returns true if a given point is located inside the rectangle.
	 */
	@Override
	public boolean contains(Point2D ot) {
		double min_x = Math.min(this._B.x(), this._A.x());
		double min_y = Math.min(this._B.y(), this._A.y());
		double max_x = Math.max(this._B.x(), this._A.x());
		double max_y = Math.max(this._B.y(), this._A.y());
		
		/*
		 * Return true if the point "ot" is between the 
		 * x axis and y axis of the given rectangle.
		 */
		
		boolean flag = ot.x() >= min_x && ot.x() <= max_x;
		flag = flag && (ot.y() >= min_y && ot.y() <= max_y);
		
		return flag;
	}

	/**
	 * Returns the point which is at the center of mass.
	 */
	@Override
	public Point2D centerOfMass() {
		double x = this._A.x() + this._B.x(); 
		x = x / 2;
		double y = this._A.y() + this._B.y();
		y = y / 2;
		Point2D p = new Point2D(x,y);
		return p;
	}

	/**
	 * Area of the rectangle.
	 */
	@Override
	public double area() {
		double dx = this._A.x() - this._B.x();
		dx = Math.abs(dx);
		
		double dy = this._A.y() - this._B.y();
		dy = Math.abs(dy);
		return dx * dy;
	}

	/**
	 * Perimeter of the rectangle.
	 */
	@Override
	public double perimeter() {
		double dx = this._A.x() - this._B.x();
		dx = Math.abs(dx);
		
		double dy = this._A.y() - this._B.y();
		dy = Math.abs(dy);
		
		return (2 * dx) + (dy * 2);
	}

	/**
	 * Move the whole rectangle by a single vector.
	 */
	@Override
	public void move(Point2D vec) {
		this._A = new Point2D(this._A.add(vec));
		this._B = new Point2D(this._B.add(vec));
	}

	/**
	 * Deep copy of a rectangle.
	 */
	@Override
	public GeoShape copy() {
		Point2D a = this._A;
		Point2D b = this._B;
		Rect2D new_r = new Rect2D(a,b);
		return new_r;
	}

	/**
	 * Returns an array containing all points of the rectangle.
	 */
	@Override
	public Point2D[] getPoints() {
		Point2D[] p_arr = new Point2D[2];
		
		if (this._A.x() < this._B.x())
		{
		p_arr[0] = this._A;
		p_arr[1] = this._B;
		}
		else
		{
			p_arr[1] = this._A;
			p_arr[0] = this._B;
		}
		
		
		return p_arr;
	}
	
	/**
	 * Prints point A, and point B. 
	 * A being the lower left, B upper right.
	 */
	public String toString() {
		String str = "";
		if (this._A.x() <= this._B.x() && this._A.y() <= this._B.y())
		{
			str = str + this._A.toString()+",";
			str = str + this._B.toString();
		}
		else
		{		
			str = str + this._B.toString()+",";
			str = str + this._A.toString();
		}
		return str;
	}
	


}
