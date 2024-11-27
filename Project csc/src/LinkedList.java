
public class LinkedList<T> implements List <T> {
	private Node<T> head; 
	public Node<T> current;
	private Node<T> tail;
	private int count;
	public int getSize() {
		return count;
	}
	public LinkedList() {
		head = current = tail = null;
		count=0;
	}
	public boolean empty() {
		return head == null;
	}
	public boolean last() {
		return current.next == null;
	}
	public boolean full() {
		return false;
	}
	public void findFirst() {
		current = head;
	}
	public Node<T> getHead(){
		return head;
	}
	public Node<T> getTail() {
		return tail;
	}
	public void findNext() {
		current = current.next;
	}
	public T retrieve() {
		return current.data;
	}
	public void update(T val) {
		current.data = val;
	}
	public void addNode(Node<T> node) {
		if(empty()) {
			current = head = tail = node;
			count++;
		}else {
			tail.next = node;
			tail=tail.next;
			count++;
		}
	}
	public void add(T val) {
		if(empty()) {
			current = head = tail = new Node<T> (val);
			count++;
		}else {
			tail.next = new Node<T>(val);
			tail=tail.next;
			count++;
		}
	}
	public void insert (T val) {
		Node<T> tmp;
		if (empty()) {
			current = head = tail = new Node<T> (val);
		}
		else if(current == tail) {
			tail.next = new Node<T> (val);
			tail = tail.next;
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
		count++;
	}
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
		count--;
	}
	public void display() {
		if(head==null)
			System.err.println("The list is empty");
		Node<T>p=head;
		while(p!=null) {
			System.err.println(p.data+ "  ");
			p=p.next;
		}
	}
}


