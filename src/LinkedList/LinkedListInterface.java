package LinkedList;

public interface LinkedListInterface<X> {
    
    /* Adds information passed (X info) in the first node of the list */
    public void pushFirst(X info) throws Exception;
    
    /* Adds information passed (X info) in the last node of the list */
    public void pushLast(X info) throws Exception;

    /* Deletes the first node of the list */
    public void popFirst() throws Exception;
    
    /* Deletes the last node of the list */
    public void popLast() throws Exception;
    
    /* Returns the first node of the list */
    public void pushAfter(Node<X> node, X info) throws Exception;

}
