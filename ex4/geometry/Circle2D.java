package ex4.geometry;

/**
 * This class represents a two dimensional circle in the plane.
 * Made by a central point and a radius.
 * @author Thomas
 *
 */
public class Circle2D implements GeoShape {
	private Point2D _P;
	private double _R;

	 /**
 	 * Circle2D Constructor with parameters.
	 * @param p
 	 * @param r
 	 */
	public Circle2D(Point2D p, double r) {
	this._P = new Point2D(p);
	this.set_R(r);
}

	 /**
 	 * Circle2D Default constructor.
 	 */
	public Circle2D() {
	this._P = new Point2D(0,0);
	this._R = 1;
}

	/**
	 * Circle2D Copy instructor.
	 * @param oc
	 */
	public Circle2D(Circle2D oc) {
		this._P = new Point2D(oc.get_P());
		this._R = oc.get_R();
	}

	/**
	 * Point P get function.
	 * @return
	 */
	public Point2D get_P() {
		return _P;
	}

	/**
	 * Point P set function.
	 * @param _P
	 */
	public void set_P(Point2D _P) {
		this._P = _P;
	}

	/** 
	 * Radius R get function.
	 * @return
	 */
	public double get_R() {
		return _R;
	}

	/**
	 * Radius R set function. Throws error if the radius is 0.
	 * @param _R
	 */
	public void set_R(double _R) {
		if (_R>=0)
		this._R = _R;
		else {
			this._R = 1;
			throw new IllegalArgumentException("Negative radius given! assigned default radius of 1.");
		}
	}

	
	/**
	 * Returns true if a given point is located inside the circle.
	 */
	@Override
	public boolean contains(Point2D ot) {
		double ot_r = this._P.distance(ot);
		boolean flag = ot_r <= this._R;
		return flag;
	}
	
	/**
	 * Returns the central point, for a 2d circle it is _P.
	 */

	@Override
	public Point2D centerOfMass() {
		return this._P;
	}
	
	/**
	 * Returns the area of a circle.
	 */

	@Override
	public double area() {
		/*
		 * Pi * (radius)^2
		 */
		double pi = Math.PI;
		return pi * this._R * this._R;
	}

	
	/**
	 * Returns the circumference of a circle.
	 */
	@Override
	public double perimeter() {
		/*
		 * Using the circumference formula for a circle : 2(pi)(r)
		 */
		double pi = Math.PI;
		return 2 * this._R * pi;
	}

	
	/**
	 * Moves the central point by a single vector.
	 */
	@Override
	public void move(Point2D vec) {
		this._P = new Point2D(this._P.add(vec));
	}
	
	/**
	 * Deep copy of a circle.
	 */
	@Override
	public GeoShape copy() {
		Point2D p = new Point2D(this._P);
		Circle2D circC = new Circle2D(p, this._R);
		return circC;
	}

	
	/**
	 * Returns an array containing the central point of the circle.
	 */
	@Override
	public Point2D[] getPoints() {
		Point2D[] arr_p = new Point2D[2];
				arr_p[0] = new Point2D(this._P);
				arr_p[1] = new Point2D(this._R, this._R);
		return arr_p;
	}

	/**
	 * Prints the central point and a point on the boundary.
	 */
	public String toString() {
		Point2D p;
		p = new Point2D(this._P.x() + this._R, this._P.y());
		String str = "";


		str = this._P.toString() + ", " + _R;
		return str;
	}

}
