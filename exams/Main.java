package exams;
import ex4.geometry.Point2D;
public class Main {
public static void main(String[] args)
{

LinkedListDouble l = new LinkedListDouble();
l.add(1);
l.add(2);
l.add(1);
l.add(1);
l.add('z');
l.add('z');

System.out.println(l);
l.removeDup();
System.out.println(l);
	
}
}
