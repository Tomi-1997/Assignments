package ex4;
import ex4.geometry.*;

import java.awt.Color;

public class GUIShape implements GUI_Shape{
	GeoShape _SH;
	boolean _isFilled;
	Color _cl; 
	int _tag;
	
	/**
	 * Parameters constructor.
	 * @param sh
	 * @param fill
	 * @param c
	 * @param i
	 */
	public GUIShape(GeoShape sh, boolean fill, Color c, int tag) {
	this._SH = sh;
	this._isFilled = fill;
	this._cl = c;
	this._tag = tag;
	}
	
	/**
	 * Default constructor;
	 */
	public GUIShape () {
		this._SH = new Circle2D();
		this._isFilled = false;
		this._cl = Color.blue;
		this._tag = 1;
	}

	@Override
	public GeoShape getShape() {
		return this._SH;
	}

	@Override
	public void setShape(GeoShape g) {
		this._SH = g;
	}

	@Override
	public boolean isFilled() {
		return this._isFilled;	
		}

	@Override
	public void setFilled(boolean filled) {
		this._isFilled = filled;
	}

	@Override
	public Color getColor() {
		return this._cl;
	}

	@Override
	public void setColor(Color cl) {
		this._cl = cl;
	}

	@Override
	public int getTag() {
		return this._tag;
	}

	@Override
	public void setTag(int tag) {
		this._tag = tag;
	}

	/** 
	 * Deep copy of the GUI_Shape
	 */
	@Override
	public GUI_Shape copy() {
		GUI_Shape c = new GUIShape(this._SH.copy(), this._isFilled, this._cl, this._tag);
		return c;
	}
	
	@Override
	public String toString() {
		return this._SH.toString();
	}

}
