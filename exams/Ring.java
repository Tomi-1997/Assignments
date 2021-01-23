package exams;
import ex4.geometry.Point2D;

public class Ring {
	
private Point2D _P;
private double _R1;
private double _R2;

public Ring(Point2D cen, double r1, double r2) {
	this._P = new Point2D(cen);
	this._R1 = r1;
	this._R2 = r2;
}

public Ring(Ring or) {
	this._P = new Point2D(or._P);
	this._R1 = or._R1;
	this._R2 = or._R2;
}

public int inArea(Point2D p)
{
	if (p.distance(this._P) <= 
			Math.min(this._R1, this._R2)) return 1;
	
	if (p.distance(this._P) <= 
			Math.max(this._R1, this._R2)) return 2;
	
	return 3;	
}

}
