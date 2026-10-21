
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;
	
	
	public LinkedList() {
		head = current = null;
	}
	
	
	public void findFirst() {
		current = head;
	}
	
	
	public void findNext() {
		current = current.getNext();
	}
	
	
	public T retrieve() {
		return current.getData();
	}
	
	
	public void update(T data) {
		current.setData(data);
	}
	
	
	public void insert(T data) {
		if(empty())
			current = head = new Node<T>(data);
		
		Node<T> tmp = current.getNext();
		current.setNext(new Node<T>(data));
		current = current.getNext();
		current.setNext(tmp);
	}
	
	
	
	public void remove() {
		if(current == head)
			head = head.getNext();
		else {
			Node<T> tmp = head;
			while(tmp.getNext() != current)
				tmp = tmp.getNext();
			tmp.setNext(current.getNext());
		}
		
		if(current.getNext() == null)
			current = head;
		else
			current = current.getNext();
	}
	
	
	public boolean empty() {
		return head == null;
	}
	
	
	public boolean last() {
		return current.getNext() == null;
	}
}
