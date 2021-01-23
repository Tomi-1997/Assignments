package ex4.geometry;

/**
 * This class represents a two dimensional triangle in the plane.
 * Made by three points.
 * @author Thomas
 *
 */
public class Triangle2D implements GeoShape
{
	private final double EPS = 0.0000001;
	private Point2D _A, _B, _C;
	
	/**
	 * Triangle2D Constructor with parameters.
	 * @param a
	 * @param b
	 * @param c
	 */
	public Triangle2D(Point2D a, Point2D b, Point2D c)
	{
		this._A = new Point2D(a);
		this._B = new Point2D(b);
		this._C = new Point2D(c);
	}
	
	/**
	 * Triangle2D Default constructor
	 */
	public Triangle2D()
	{
	Point2D a = new Point2D(-1, 0);
	Point2D b = new Point2D(0, 1);
	Point2D c = new Point2D(1, 0);
	this._A = a;
	this._B = b;
	this._C = c;
	}
	
	/**
	 * Triangle2D Copy constructor
	 * @param tri
	 */
	public Triangle2D(Triangle2D tri)
	{
	tri._A = new Point2D(this._A);
	tri._B = new Point2D(this._B);
	tri._C = new Point2D(this._C);
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
	 * C Get function
	 * @return
	 */
	public Point2D get_C() {
		return _C;
	}

	/**
	 * C Set function
	 * @return
	 */
	public void set_C(Point2D _C) {
		this._C = _C;
	}

	/**
	 * Returns true if a given point is located inside the triangle.
	 */
	@Override
	public boolean contains(Point2D ot) {
		Triangle2D tri1 = new Triangle2D(this._A, this._B, ot);
		Triangle2D tri2 = new Triangle2D(this._A, this._C, ot);
		Triangle2D tri3 = new Triangle2D(this._B, this._C, ot);
		
		double new_a = tri1.area() + tri2.area() + tri3.area();
		/*
		 * Comparing the triangle's area with the area of the three triangles
		 * made by connecting each edge of the triangle to the point we check.
		 * If the the point is inside, the area of original triangle should be
		 * greater or equal.
		 */
		boolean flag =  new_a -  this.area() < EPS;
		 
		return flag;
	}

	/**
	 * Returns the point which is at the center of mass.
	 */
	@Override
	public Point2D centerOfMass() {
		double a,b;
		a = this._A.x() + this._B.x() + this._C.x();
		a = a / 3;
		b = this._A.y() + this._B.y() + this._C.y();
		b = b / 3;
		/*
		 * The center of mass is located at the average between the three
		 * x and y coordinates of the three points of the triangle.
		 */
		return new Point2D(a,b);
	}

	/**
	 * The area of the triangle
	 */
	@Override
	public double area() {
		/*
		 * Using the heron's formula to calculate the area 
		 * where s is the perimeter divided by two.
		 * Area = square root of (s * (s-a) (s-b) (s-c) ) where a,b,c
		 * are the values of the triangle's sides
		 */
		double s = this.perimeter_half();
		double a = this._A.distance(this._B);
		double b = this._B.distance(this._C);
		double c = this._C.distance(this._A);
		
		a = s - a;
		b = s - b;
		c = s - c;
		s = s * a * b * c;
		s = Math.sqrt(s);
		
		return s;
	}

	/**
	 * Perimeter of the triangle
	 */
	@Override
	public double perimeter() {
		/*
		 * Sum the distance between the three points.
		 * i.e from a -> b + b -> c + c -> a.
		 */
		double a,b,c, peri;
		
		a = this._A.distance(this._B);
		b = this._B.distance(this._C);
		c = this._C.distance(this._A);
		
		peri = a + b + c;
		return peri;
	}
	
	public double perimeter_half()
	{
		return (perimeter()) / 2;
	}

	/**
	 * Move the whole triangle by a single vector.
	 */
	@Override
	public void move(Point2D vec) {
		this._A = new Point2D(this._A.add(vec));
		this._B = new Point2D(this._B.add(vec));
		this._C = new Point2D(this._C.add(vec));
		/*
		 * Add the given 'vec' point to each point of the triagle.
		 * i.e given vec = (1,0) we move each point, a,b,c by one in the x - axis,
		 * and 0 in the y - axis.
		 */
	}

	/**
	 * Deep copy of a triangle.
	 */
	@Override
	public GeoShape copy() {
		Point2D a = this._A;
		Point2D b = this._B;
		Point2D c = this._C;
		/*
		 * Making a deep copy of the triangle we operate on.
		 */
		Triangle2D tri_c = new Triangle2D(a, b, c);
		return tri_c;
	}

	/**
	 * Returns an array containing all points of the triangle.
	 */
	@Override
	public Point2D[] getPoints() {
		Point2D[] p_arr = new Point2D[3];
		p_arr[0] = this._A;
		p_arr[1] = this._B;
		p_arr[2] = this._C;
		return p_arr;
	}

	/**
	 * Prints point A, point B, point C in that order.
	 * i.e x(A), y(A) - x(B), y(B) - x(C), y(C)
	 */
	public String toString()
	{
		String str = "";
		str = str + this._A.toString()+",";
		str = str + this._B.toString()+",";
		str = str + this._C.toString()+"";
		return str;
	}
	
}
