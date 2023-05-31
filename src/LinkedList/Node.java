package LinkedList;
public class Node <N>{
	private N info; 
	private Node <N> next, prev;
// new Node (new Flight(0, "105"), new Node(new Flight(1, "304), null));
	public Node (Node<N> prev, N info, Node<N> next) {
		this.info = info;
		this.next = next;
		this.prev = prev;
	}

	public Node (N info, Node <N> next) {
		this.info = info;
		this.next = next;
	}

	public Node (N info) {
		this.info = info;
		this.next = null;
		this.prev = null;
	}

	public N getInfo() throws Exception {
		if (this.info == null)
			throw new Exception("Node is empty");
		return this.info;
	}

	public Node<N> getNext() {
		return this.next;
	}

	public Node<N> getPrev() {
		return this.prev;
	}

	public void setInfo(N info) {
		this.info = info;
	}

	public void setNext(Node <N> next) {
		this.next = next;
	}

	public void setPrev(Node<N> prev) {
		this.prev = prev;
	}

	@Override 
	public String toString() {
		String message = "";
		try {
			message += getInfo() + " -> " + null;
			if (getNext() != null)
				message = getInfo() + " -> " + getNext().getInfo();
			if (getPrev() != null) 
				message = getPrev().getInfo() + " -> " + message;
		}
		catch (Exception e) { System.out.println(e.getMessage()); }
			
		
		return message;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		
		if (this.getClass() != obj.getClass()) return false;

		Node<N> n = (Node<N>)obj;
		if (this.prev != n.prev) return false;
		if (this.info != n.info) return false;
		if (this.next != n.next) return false;
		
		return true;
	}
}

