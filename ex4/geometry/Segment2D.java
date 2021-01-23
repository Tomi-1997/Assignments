package ex4.geometry;

/**
 * This class represents a two dimension line between two points in the plane
 * @author Thomas
 *
 */
public class Segment2D implements GeoShape {
	private Point2D _A,_B;	
	
	/**
	 * Segment2D Constructor with parameters.
	 * @param a
	 * @param b
	 */
	public Segment2D(Point2D a, Point2D b) {
		this._A = new Point2D(a);
		this._B = new Point2D(b);
	}
	
	/**
	 * Segment2D Default constructor.
	 */
	public Segment2D() {
		this._A = new Point2D(-1,-1);
		this._B = new Point2D(1,1);
	}
	
	/**
	 * Segment2D Copy constructor.
	 * @param seg
	 */
	public Segment2D(Segment2D seg) {
		this._A = new Point2D(seg._A);
		this._B = new Point2D(seg._B);
	}

	/**
	 * Point A get function.
	 * @return
	 */
	public Point2D getA() {
		return this._A;
	}
	
	/**
	 * Point A set function.
	 * @param a
	 */
	public void setA(Point2D a) {
		if (!(a == null))
		this._A = new Point2D(a);
	}
	
	/**
	 * Point B get function.
	 * @return
	 */
	public Point2D getB() {
		return this._B;
	}
	
	/**
	 * Point B set function.
	 * @param b
	 */
	public void setB(Point2D b) {
		if (!(b == null))
		this._B = new Point2D(b);
	}
	
	/**
	 * Returns true if a given point is contained in the segment.
	 * (Between the two points)
	 */
	@Override
	public boolean contains(Point2D ot) {
		double d = this._A.distance(this._B);
		double d1 = ot.distance(this._A);
		double d2 = ot.distance(this._B);
		
		return (d == d1 + d2);
	}

	/**
	 * Returns the point which is the center of mass.
	 */
	@Override
	public Point2D centerOfMass() {
		double a = this._A.x() + this._B.x();
		a = Math.abs(a / 2);
		double b = this._A.y() + this._B.y();
		a = Math.abs(b / 2);
		Point2D p = new Point2D(a,b);
		return p;
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		double dt = this._A.distance(this._B);
		dt = dt * 2;
		return dt;
	}

	@Override
	public void move(Point2D vec) {
		this._A = this._A.add(vec);
		this._B = this._B.add(vec);
	}

	/**
	 * Returns a deep copy of the segment.
	 */
	@Override
	public GeoShape copy() {
		Point2D a = new Point2D(this._A);
		Point2D b = new Point2D(this._B);
		Segment2D segC= new Segment2D(a,b);
		return segC;
	}

	/**
	 * Returns an array containing the two edge points of the segment.
	 */
	@Override
	public Point2D[] getPoints() {
		Point2D[] a = {this._A, this._B};
		return a;
	}

	/**
	 * Prints
	 */
	public String toString() {
		return this._A + "," + this._B;
	}
}
