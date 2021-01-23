package exams;
class NodeD {
private Object data;
private NodeD next, prev;
public NodeD(NodeD prev, NodeD next, Object data){
this.data = data;
this.prev = prev;
this.next = next;
}
public String toString(){return data.toString();}
public NodeD getNext() {return next;}
public NodeD getPrev() {return prev;}
public Object getData() {return data;}
public void setNext(NodeD n) {next = n;}
public void setPrev(NodeD p) {prev = p;}
}
public class LinkedListDouble {
private NodeD head, tail;
int size;
public LinkedListDouble(){
head = tail = null;
size = 0;
}
public NodeD getHead() {return head;}
public NodeD getTail() {return tail;}
public void setHead(NodeD h) {head = h;}
public void setTail(NodeD t) {tail = t;}
public void add(Object item){
if (head == null)
head = tail = new NodeD(null, null, item);
else{
NodeD n = new NodeD(tail, null, item);
tail.setNext(n);
tail = n;
}
size++;
}
public String toString(){
String s = "[";
if (head != null){
s = s + head.toString() + ", ";
for (NodeD n=head.getNext();n!=null; n=n.getNext()){
s = s + n.toString() + ", ";
}
s = s.substring(0, s.length()-2);
}
return s+"]";
}
public boolean contains(Object item){
boolean ans = false;
NodeD n = head;
while (n != null && !n.getData().equals(item)){
n = n.getNext();
}
if (n != null) ans = true;
return ans;
}
public int size() {return size;}



public static void reverse(LinkedListDouble list)
{
	NodeD n = list.getHead();
	LinkedListDouble cl = new LinkedListDouble();
	
	while (n!=null)
	{
		cl.add(n);
		n=n.getNext();
	}

	/*Make a copy of current list*/
	
	n = cl.getTail();
	list.setHead(list.getTail());
	
	n = n.getPrev();
	
	/*Rewrite current list using the tail of the copy*/
	
	while (n!=null)
	{
		list.add(n);
		n = n.getPrev();
	}
}

/**
 * Use this on integer or doubles only
 */
public void removeDup()
{
	NodeD temp;
	if (this.size > 0)
	{
		NodeD n = this.head;
		NodeD next = n.getNext();
		
		while(n!= null) {
		while(next != null)
		{
		if (n.getData() == next.getData())
		{
			next.getPrev().setNext(next.getNext());
		}
		
		next = next.getNext();
		}
		n = n.getNext();
		}
		if (this.getTail().getPrev().getData() == this.getTail().getData())
			this.tail.getPrev().setNext(null);
	}
}

}