package LinkedList;
public class LinkedList <X> implements LinkedListInterface<X>
{
  private Node <X> first, last;
  
  public LinkedList() {
    this.first = this.last = null;
  }

  public Node<X> getFirst() {
    return this.first;
  }

  public Node<X> getLast() {
    return this.last;
  }

  public Node<X> getNodeAt(int index) throws Exception {
    if (this.isEmpty())
      throw new Exception("List is empty");
    if (index > this.getSize())
      throw new Exception("index passed outside of list's bounds");
      
    Node<X> element = this.first;
    for (int c = 0; c != index; c++ ) {
      element = element.getNext();
    }
    return element;
  }

  public int getIndexOf(X info) throws Exception {
    if (this.isEmpty())
      return -2;

    int index = 0;
    for (Node<X> current = this.first; !current.getInfo().equals(info); current = current.getNext()) {
      if (current.getNext() == null)
        return -1;

      index ++;
    }

    return index;
  }

  public int getSize() {
    int nodeAmount = 0;
    Node<X> current;
    for (current = this.first; current != this.last.getNext(); current = current.getNext()) nodeAmount++;

    return nodeAmount;
  }

  public int getNodeAmount(Node<X> node) {
    int nodeAmount = 0;
    Node<X> current;
    for(current=this.first; current != this.last.getNext(); current = current.getNext())
      if (current.equals(node))
        nodeAmount++;

    return nodeAmount;
  }

  public int getNodeAmount(X info) throws Exception {
  int nodeAmount = 0;
    Node<X> current;
    for(current=this.first; current != this.last.getNext(); current = current.getNext())
      if (current.getInfo().equals(info))
        nodeAmount++;
        
    return nodeAmount;
  }

  public void pushFirst(X info) throws Exception {
    if (info == null) 
      throw new Exception("Information passed must not be null");
    
    this.first = new Node <X> (info, this.first);

    if (this.last==null)
      this.last = this.first;
  }

  public void pushLast(X info) {
    X toInsert = info;

    if (this.last==null) {
      this.last = new Node<X>(toInsert);
      this.first = this.last;
    }
    else {
      this.last.setNext(new Node<X>(toInsert));
      this.last = this.last.getNext();
    }
  }

  public void pushAfter(Node<X> node, X info) throws Exception {
    if (node == null) 
      throw new Exception("Node passed must not be null");
    
    if (info == null) 
      throw new Exception("Information passed must not be null");
    
    node.setNext(new Node<X>(info, node.getNext()));
    
    if (node == this.last)
      this.last = node.getNext();
  }

  public void popFirst() throws Exception {
    if (this.isEmpty()) 
      throw new Exception ("List is empty");

    this.first = this.first.getNext();
  }

  public void popLast() throws Exception {
    if (this.isEmpty()) 
      throw new Exception ("List is empty");
    if (this.first.equals(this.last)) {
      this.first = this.last = null;
      return;
    }
    else {
      Node<X> current;
      for (current = this.first; current.getNext() != this.last; current = current.getNext());

      current.setNext(null);
      this.last = current;
    }
  }

  public void popIndexOf(X info) {

  }

  public void popNodeAt(int index) throws Exception {
    if (this.isEmpty()) 
    throw new Exception("List is empty");    
    int size = this.getSize();
    if (index > size)
    throw new Exception("Index cannot be bigger than the list size (" + size +")");
    
    if (this.first.equals(this.last)) {
      this.first = this.last = null;
      return;
    }
    if (index == 0) {
      this.popFirst();
      return;
    }
    if (this.getNodeAt(index).equals(this.last)) {
      this.popLast();
      return; 
    }
    
    Node<X> previous = null, current = this.first, next = current.getNext();
    while(!current.equals(this.getNodeAt(index))) {
      previous = current;
      current = next;
      next = current.getNext();
    }
    previous.setNext(next);
    current = null;
  }

  public boolean isEmpty() {
    return this.first == null;
  }

  @Override
  public String toString() {
    String ret =  "[  ";
    Node<X> current = this.first;

    try {
      while (current != null) {
        ret += current.getInfo() + ",  ";
        current=current.getNext();
      }
    } 
    catch (Exception e) { System.out.println(e.getMessage()); }

    return ret + "]";
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj){
    if(obj==null) return false;
    if(this==obj) return true;

    if (this.getClass() != obj.getClass()) return false;

    LinkedList<X> l = (LinkedList<X>) obj;
    if (!this.first.equals(l.first)) return false;
    if (!this.last.equals(l.last)) return false;
    
    return true;
  }
}