
class Node<T> {
    public Node<T> next;
	public T data;

     public Node(T key) {
    	 this.data = key;
         this.next = null;
     }
}
