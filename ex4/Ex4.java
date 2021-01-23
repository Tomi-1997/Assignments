package ex4;

import java.awt.Color;
import java.util.Scanner;

import ex4.geometry.*;


	public class Ex4 implements Ex4_GUI{
	
	Shape_Collection _SH = new Shape_Collection();
	
	public Ex4() {
		
	}
	@Override
	public void init(GUI_Shape_Collection g) {
		_SH.load("test_save.txt");
	}
	
	@Override
	public GUI_Shape_Collection getShape_Collection() {
		return _SH;
	}

	@Override
	public void show() {	
	}
	
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args)
	{
		StdDraw.setPenRadius(0.022);
		StdDraw.setPenColor(Color.magenta.darker());
		StdDraw.clear(Color.black);
		StdDraw.setCanvasSize(800, 400);
		
		String stop = "";
		int i = 1000;
		int c = 0;
		
		while (stop.isEmpty())
		{
			/*Draw a new traingle*/
		double[] x_arr = { Math.random(),Math.random(),Math.random()};
		double[] y_arr = { Math.random(),Math.random(),Math.random()};
		
			StdDraw.clear(Color.black);
			
			if (c < 1000) {
			StdDraw.filledPolygon(x_arr, y_arr);
			c++;
			}
			
			else
			{
				double[] x_arr2 = { Math.random(),Math.random(),Math.random()};
				double[] y_arr2 = { Math.random(),Math.random(),Math.random()};
				StdDraw.filledPolygon(x_arr, y_arr);
				StdDraw.filledPolygon(x_arr2, y_arr2);
				c = c + 2;
			}
			
			/*Wait i milliseconds*/
			wait(i);
			
			/*Clear board and pick a new colour*/
			
			StdDraw.setPenColor(Color.getHSBColor((int)(Math.random()*255), 
													(int)(Math.random()*255), 
													 (int)(Math.random()*255)));
		
			/*Gradually make i smaller */
			if(i > 200)
			i = i - 70;
			
			else if (i > 200)
				i = i - 10;
			
			else if (i>1)
				i--;
			
			else if (i>0){
				i = 1/1000;

			}
		}
			StdDraw.clear(Color.black);	
	}
	
	public static void wait(int i)
	{
		try
		{
		    Thread.sleep(i);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	//end
	}