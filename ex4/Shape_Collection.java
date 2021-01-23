package ex4;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import ex4.geometry.*;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */

	public class Shape_Collection implements GUI_Shape_Collection{
		
	ArrayList<GUIShape> sc = new ArrayList<GUIShape>();
	public String info = this.getInfo();
	
	/**
	 * Constructor with parameter.
	 * @param geo
	 */
	public Shape_Collection(GUIShape geo)
	{
		sc.add(geo);
	}
	
	/**
	 * Default constructor
	 */
	public Shape_Collection() {

	}
	
	@Override
	public GUI_Shape get(int i) {
		return sc.get(i);
	}

	/**
	 * Returns how many GUI_Shapes are contained.
	 */
	@Override
	public int size() {
		return sc.size();
	}

	/**
	 * Returns the removed GUI_Shape at the given i index.
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		return sc.remove(i);
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		sc.add(i, (GUIShape) s);
	}
	
	@Override
	
	public void add(GUI_Shape s) {
		/*	add new element as is, not a deep copy	*/
		//GUIShape g = new GUIShape(s.getShape(), s.isFilled(), s.getColor(), s.getTag());
		sc.add((GUIShape)s);
	}
	
	@Override
	public GUI_Shape_Collection copy() {
		/*	create a deep copy of this collection	*/
		Shape_Collection sc_c = new Shape_Collection();
		sc_c.removeElementAt(0);
		for (int i=0; i<this.size(); i++)
		{
			sc_c.add(this.get(i).copy());
		}
		return sc_c;
	}

	@Override
	public void sort(Comparator comp) {
		sc.sort(comp);
	}

	@Override
	public void removeAll() {
		int i=this.size()-1;
		while (this.size()>0)
		{
			this.sc.remove(i);
			i--;
		}
	}

	/**
	 * Saves the info of the collection into a text file.
	 */
	@Override
	public void save(String file_name) {
		try {
			FileWriter file = new FileWriter(file_name, false);
			file.write(this.getInfo());
			file.close();
			this.info = "";
		}
		catch (IOException e){ 
			e.printStackTrace();
		}
	}

	/**
	 * Returns a string depicting the collection of shapes such as:
	 * [Class name, RGB value, isFilled, index, Geometric shape, Points]
	 */
	public String getInfo() {
		String in = "";
		int color = 0;
		for (int i=0; i<this.size(); i++)
		{
			in += sc.get(i).getClass().getSimpleName(); // "GUISHAPE"

			/*RGB Value of the color*/
			color = sc.get(i).getColor().getRGB();
			in += ","+color; 
			
			/*Filled or not*/
			in += ","+this.get(i).isFilled();
			
			/*Index*/
			in += ","+i;
			
			/*Type of geometric shape (rectangle, circle etc..)*/
			in += ","+this.get(i).getShape().getClass().getSimpleName(); 
			
			/*String of the shape's points,
			 * in case of a circle, center point and the radius*/
			in += ","+this.get(i).toString()+"\n";
		}
		return in;
	}
	
	@Override
	public void load(String file) {
		/*new list, to make our current list point to it once we load all shapes*/
		ArrayList<GUIShape> sc_new = new ArrayList<GUIShape>();
		String loaded_info="";
		
		try {
			FileReader read = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(read);
			String line;
			
			while ((line = bufferedReader.readLine()) != null)
			{
				/*	Build each shape each line	*/
				applyInfo(line, sc_new);
				loaded_info += line+"\n";
			}
			read.close();
		}
		
		catch (IOException e ) { 
			e.printStackTrace();
		}
		this.info = loaded_info;
		
		/*Point our current list to the loaded list*/
		this.sc = sc_new;
		sc_new = null;
	}

	private void applyInfo(String info, ArrayList<GUIShape> new_sc) {
		String[] basic_info = info.split(",");
		String color = (basic_info[1]);
		
		/* example for basic_info 
		 * 
		 * [GUIShape,65280,true,2,Rect2D,0.1,0.2,0.5,0.45]
		 * 
		 * the first element "GUIShape" has no importance on the construction.
		 * the second is the color, 
		 * the third element - if it is filled or not
		 * fourth is the tag, integer ID
		 * fifth is the shape, and afterwards pair of points,
		 * and in the case of a circle, ends with the radius.*/
		
		boolean isFilled = false;
		if (basic_info[2].contains("true"))
			isFilled = true;
		
		int index = Integer.parseInt(basic_info[3]);
		
		String sh_str = basic_info[4];
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		
		int i = 5;
		for (; i<basic_info.length-1; i = i + 2)
				{
				Point2D p = new Point2D(basic_info[i] +","+ basic_info[i+1]);
				points.add(p);
				}
		
		/*	In case of a circle, one more value [radius] will be not assigned to a point,
		 * so we make a new point made by only the radius to help the circle function.	*/
		if (i<basic_info.length )
			points.add(new Point2D(basic_info[i] +","+ basic_info[i]));
		
		GeoShape sh = new Circle2D(); //default choice is a circle.
		sh = buildGeo(sh_str, points);
		
		GUIShape geo = new GUIShape(sh, isFilled, Color.decode(color), index);
		new_sc.add(geo);
	}
	
	/*	Load text file and create a new collection	*/

	private GeoShape buildGeo(String sh_str, ArrayList<Point2D> points) {
		
		if (sh_str.contains("Point2D")) return buildP2D(points); 
		if (sh_str.contains("Rect2D")) return BuildR2D(points); 
		if (sh_str.contains("Triangle2D")) return BuildT2D(points); 
		if (sh_str.contains("Segment2D")) return BuildS2D(points); 
		
		return BuildC2D(points); //circle
	}
	
	private GeoShape BuildC2D(ArrayList<Point2D> points) {
		Point2D a = new Point2D(points.get(0));
		Point2D r_helper = new Point2D(points.get(1));
		double r = r_helper.y();

		return new Circle2D(a,r);
	}

	private GeoShape BuildS2D(ArrayList<Point2D> points) {
		/*	Invalid input	*/
		if (points.size()!= 2 )
			return new Point2D(0,0);
		
		Point2D a = new Point2D(points.get(0)),
				b = new Point2D(points.get(1));
		
		return new Segment2D(a,b);
	}

	private GeoShape BuildT2D(ArrayList<Point2D> points) {
		/*	Invalid input	*/
		if (points.size()!= 3 )
		return new Point2D(0,0);
		
		Point2D a = new Point2D(points.get(0)),
				b = new Point2D(points.get(1)),
				c = new Point2D(points.get(2));
		
		return new Triangle2D(a,b,c);
	}

	private GeoShape BuildR2D(ArrayList<Point2D> points) {
		Point2D a = new Point2D(points.get(0));
		Point2D b = new Point2D(points.get(1));
		return new Rect2D(a,b);
	}

	private GeoShape buildP2D(ArrayList<Point2D> points) {
		if (points.size()== 1)
		return new Point2D(points.get(0));
		
		/*	Invalid input (more or less than 1 point)	*/
		return new Point2D(0,0);
	}
	
	@Override
	public Rect2D getBoundingBox() {
		double min_x = 0, min_y = 0, max_x = 0, max_y = 0;
		double smallx = 0, smally = 0, bigx = 0, bigy = 0;
		double _R = 0;
		if (this.size()==0) return null;
		
		/*At start, our smallest and biggest x is the first one.*/
		/*Same for y.*/
		Point2D[] pts = sc.get(0).getShape().getPoints();
		min_x = getMinX(pts);
		min_y = getMinY(pts);
		max_x = getMaxX(pts);
		max_y = getMaxY(pts);
		
		for (int i=1; i<sc.size(); i++)
		{
			pts = sc.get(i).getShape().getPoints();
			String class_of_current = sc.get(i).getShape().getClass().getSimpleName();
			
			/*Check if it is a circle,
			 * for a circle we check from the center point with the distance of radius
			 * if it is the smallest x/y or the biggest x/y inside our list*/
			if (!(class_of_current.equals("Circle2D")))
					{
			smallx = getMinX(pts);
			smally = getMinY(pts);
			bigx = getMaxX(pts);
			bigy = getMaxY(pts);
					}
			/*	Below we perform the circle maximum x/y and minimum x/y check	*/
			else
			{
				_R = sc.get(i).getShape().area(); 
				_R = _R / Math.PI;
				_R = Math.sqrt(_R);
				
				smallx = pts[0].x() - _R;
				smally = pts[0].y() - _R;
				bigx = pts[0].x() + _R;
				bigy = pts[0].y() + _R;
			}
			
			/*test to see if the current x/y are smaller or bigger than our current maximum and minimum.*/
			if(smallx < min_x) min_x = smallx;
			if(smally < min_y) min_y = smally;
			if(bigx > max_x) max_x = bigx;
			if(bigy > max_y) max_y = bigy;
		}
		
		/*	Construct our bounding rectangle using our minimum x,y	*/
		
		Point2D a = new Point2D(min_x, min_y),
				b = new Point2D(max_x, max_y);
		Rect2D bbox = new Rect2D(a,b);
		return bbox;
	}

	@Override
	public String toString() {
		if (!(sc.isEmpty()))
		return sc.toString();
		else
			return "";
	}

	/*	Bounding box	*/
	private double getMinX(Point2D[] pts) {
		
		double min=0;
		if (pts.length==0)
			return 0;
		
			min = pts[0].x();
		if( pts.length == 1) return min;			
	for (int i=0; i<pts.length-1; i++)
	{
		if (pts[i].x() >= pts[i+1].x())
			min = pts[i+1].x();
	}
		return min;
	}
	
	private double getMinY(Point2D[] pts) {
		double min=0;
		if (pts.length==0)
			return 0;
		
			min = pts[0].y();
		if( pts.length == 1) return min;			
	for (int i=0; i<pts.length-1; i++)
	{
		if (pts[i].y() >= pts[i+1].y())
			min = pts[i+1].y();
	}
		return min;
	}
	
	private double getMaxX(Point2D[] pts) {
		double max=0;
		if (pts.length==0)
			return 0;
		
			max = pts[0].x();
		if( pts.length == 1) return max;			
	for (int i=0; i<pts.length-1; i++)
	{
		if (pts[i].x() <= pts[i+1].x())
			max = pts[i+1].x();
	}
		return max;
	}
	
	private double getMaxY(Point2D[] pts) {
		double max=0;
		if (pts.length==0)
			return 0;
		
			max = pts[0].y();
		if( pts.length == 1) return max;			
	for (int i=0; i<pts.length-1; i++)
	{
		if (pts[i].y() <= pts[i+1].y())
			max = pts[i+1].y();
	}
		return max;
	}

	public void loadSort(String string) {

	try {
		FileReader read = new FileReader(string);
		BufferedReader bufferedReader = new BufferedReader(read);
		String line;
		line = bufferedReader.readLine();
		read.close();	
		
		/*Sort our collection based on the integer loaded from the text file*/
		int flag = Integer.parseInt(line);
		Shape_Comp comp = new Shape_Comp(flag);
		this.sc.sort(comp);
	}
	
	catch (IOException e ) { 
		/*If an error occurred, continue without sorting.*/
	}
	}
	
	}