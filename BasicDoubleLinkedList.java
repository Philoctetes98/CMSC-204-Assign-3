/**Generic Class for Basic Double Linked List 
 * @author binya
 * @param <T> data type
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;





public class BasicDoubleLinkedList<T> implements Iterable<T>{

	protected Node firstNode, lastNode;	
	protected int size;
	protected DoubleIterator it;
	
	
	/** 
	 * No-arg constructor for creation of an empty list.
	 */
	
	BasicDoubleLinkedList(){
		firstNode = null;
		lastNode = null;
		size = 0;
	}
	

	
	
	
	// Adding functions
	
	
	
	/** 
	 * Add an element to the end of the list.
	 * @param data to be added in node form
	 * @return the list post-add
	 */
	
	BasicDoubleLinkedList<T> addToEnd(T data) { 
		
		if(lastNode == null) {
			firstNode = new Node(data);
			lastNode = firstNode;
		} else {
			Node temp = new Node(data, lastNode, null);
			lastNode.setNext(temp); //Formerly last node
			lastNode = temp;
		}
		
		size += 1;
	    return this;
	    
	}
	
	
	
	
	
	/** 
	 * Add an element to the end of the list.
	 * @param data to be added in node form
	 * @return the list post-add
	 */
	
	BasicDoubleLinkedList<T> addToFront(T data){
		
		if(firstNode == null) {
			firstNode = new Node(data);
			lastNode = firstNode;
		} else {
			Node temp = new Node(data, null, firstNode);
			firstNode.setPrevious(temp); //Formerly first node
			firstNode = temp;
		}
		
		size += 1;
	    return this;
		
	}
	 
	
	
	
	
	// Auxiliary functions
	
	
	
	/** 
	 * Get the first node of the list, if it has one.
	 * @return the list post-add
	 * @throw NoSuchElementException when there is no first element
	 */
	
	public T getFirst() throws NoSuchElementException{ 
		if(firstNode != null) {
			return firstNode.getData(); 
		} else {
			throw new NoSuchElementException();
		}
	}
	
	
	
	
	
	/** 
	 * Get the first node of the list, if it has one.
	 * @return the list post-add
	 * @throw NoSuchElementException when there is no last element
	 */
	
	public T getLast() {
		if(firstNode != null) {
			return lastNode.getData(); 
		} else {
			throw new NoSuchElementException();
		}
	}
	 
	
	
	
	
	/** 
	 * Get the size of the list.
	 * @return the size of the list
	 */
	
	public int getSize() { return size; }
	
	
	
	
	
	/** 
	 * Converts the data of the linked list into an arraylist.
	 * @return the arraylist of data from the linked list
	 */
	
	public ArrayList<T> toArrayList() { 
		
		ArrayList<T> list = (ArrayList<T>) new ArrayList<Object>();
		it = iterator();
		
		while(it.hasNext()) {
			it.next();
			list.add(it.current.getData());
		}
		
		
		return list;
		
	}
	
	
	
	
	
	/** 
	 * This instantiates the inner iterator class.
	 * @return a new DoubleIterator object
	 * @throw UnsupportedOperationException when nextIndex, previousIndex, set, add, or remove are used
	 * @throw NoSuchElementException when no such requested element is found by the iterator
	 */
	
	public DoubleIterator iterator() throws UnsupportedOperationException, NoSuchElementException{ return new DoubleIterator(); }
	
	
	
	
	
	
	// Removing functions
	
	/** 
	 * Removes the first node with the data of targetData. 
	 * @param targetData The data to find and remove
	 * @param c The comparator by which the nodes of the linked list should be compared to the target data
	 * @return the arraylist of data from the linked list post remove attempt
	 */
	
	BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> c){
		
		Node temp;
		
		if(size == 0) {
			return this;
		} else if(size == 1) {
			if(c.compare(firstNode.getData(), targetData) == 0) {
				firstNode = null;
				lastNode = null;
				size--;
			}
			return this;
		}
		

		
		temp = firstNode;
		

		while(temp != null) {
			
						
			if(c.compare(temp.getData(), targetData) == 0) {
				
				if(size == 1) {
					size = 0;
					firstNode = null;
					lastNode = null;
					size--;
					break;
				} 
				
				if(temp.equals(firstNode)) {
					firstNode = firstNode.getNext();
					firstNode.setPrevious(null);
				} else if (temp.equals(lastNode)) {
					lastNode = lastNode.getPrevious();
					lastNode.setNext(null);
				} else {
					temp.getPrevious().setNext(temp.getNext());
					temp.getNext().setPrevious(temp.getPrevious());
				}
				
				size--;
				break;
				
			}
			
			temp = temp.getNext();
			
			
		}
		
		
		return this;
		
	}
	
	
	
	
	
	/** 
	 * Checks whether an instance of target is in the linked list using a provided comparator. 
	 * @param target The data to find within the linked list
	 * @param c The comparator by which the nodes of the linked list should be compared to the target data
	 * @return true if the linked list contains the element
	 */
	
	public boolean contains(T target, Comparator<T> c) {
		
		if(size == 0) {
			return false;
		}
		
		Node temp = firstNode;

		while(temp != null) {
			if(c.compare(temp.getData(), target) == 0) {
				return true;
			}
			temp = temp.getNext();
		}
		
		return false;
		
	}
	
	
	
	
	/**
	 * Retrieve the first element the linked list, also delinking it.
	 * @return first element in the linked list, null if none found
	 */
	
	public T retrieveFirstElement() {
		
		T t;
		
		if(firstNode != null) {
			t = firstNode.getData();
		} else {
			t = null;
		}
		
		if(size > 1) {
			firstNode = firstNode.getNext();
			firstNode.setPrevious(null);
		} else {
			firstNode = null;
			lastNode = null;
		}
		
		size = size == 0 ? 0 : size - 1;
		
		return t;
		
	}
	
	
	
	
	
	/*
	 * Retrieve the last element the linked list, also delinking it.
	 * @return last element in the linked list, null if none found
	 */
	
	public T retrieveLastElement() {
		
		
		T t;
		
		if(lastNode != null) {
			t = lastNode.getData();
		} else {
			t = null;
		}
		
		lastNode = lastNode.getPrevious();
		size = size == 0 ? 0 : size - 1;
		
		return t;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Custom iterator class for the bidirectional linked list.
	 */
	
	protected class DoubleIterator implements ListIterator<T> {

		Node current;
		boolean atEnd, atStart = false;
		
		DoubleIterator(){
			current = null;
		}
		
		
		// Supported methods
		
		/**
		 * Check if the iterator can proceed to the next value.
		 * @return true if can iterate to next element
		 */
		
		@Override
		public boolean hasNext() {
			return current != lastNode && !atEnd;
		}

		
		
		
		
		/**
		 * Check if the iterator can proceed to the previous value.
		 * @return true if can iterate to previous element
		 */
		
		@Override
		public boolean hasPrevious() {
			return !(current == null  && !atEnd);
		}

		
		
		
		
		/**
		 * Iterate to the iterator's next value.
		 * @throw NoSuchElementException when the iterator is at the end of the list or the list is empty
		 * @return the data of the iterator's reference after iteration
		 */
		
		@Override
		public T next() throws NoSuchElementException{
			
			
			if(size == 0) { // The list is empty
				throw new NoSuchElementException();
			} 
			
			if(atEnd) {
				throw new NoSuchElementException();
			}
			
			if(current == null) { // Before first (null)
				current = firstNode;
				return current.getData();
			}

			if(current.hasNext()) { // Before another element
				current = current.getNext();
				return current.getData();
			} else {
				throw new NoSuchElementException();
			}
			
			
		}

		
		
		
		
		/**
		 * Iterate to the iterator's previous value.
		 * @throw NoSuchElementException when the iterator is at the start of the list or the list is empty
		 * @return the data of the iterator's reference after iteration
		 */
		
		@Override
		public T previous() throws NoSuchElementException{
			
			T temp;

			if(size == 0) {
				throw new NoSuchElementException();
			}
			
			if(current == null && atEnd) { // Before first (null)
				current = lastNode;
				return current.getData();
			} else if (current == null && !atEnd) {
				throw new NoSuchElementException();
			}
			
			if(current.hasPrevious()) { // Before another element
				atEnd = false;
				current = current.getPrevious();
				return current.getNext().getData();
			} else {
				if(current != null) {
					temp = current.getData();
					current = null;
					return temp;
				}	
			}
			

			throw new RuntimeException();
		}
		
		
		
		// Unsupported methods
		
		@Override
		public int nextIndex() throws UnsupportedOperationException { throw new UnsupportedOperationException(); }

		@Override
		public int previousIndex() throws UnsupportedOperationException { throw new UnsupportedOperationException(); }
		
		@Override
		public void set(Object arg0) throws UnsupportedOperationException { throw new UnsupportedOperationException(); }

		@Override
		public void add(Object e) throws UnsupportedOperationException { throw new UnsupportedOperationException(); }

		@Override
		public void remove() throws UnsupportedOperationException { throw new UnsupportedOperationException(); }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class Node {
		
		private T data;
		private Node previous, next;
		
		/**
		 * Constructor that creates a node linked to passed nodes.
		 * @param d data of the node
		 * @param previous reference to previous node
		 * @param next reference to next node
		 */
		
		Node(T d, Node previous, Node next){
			data = d;
			this.previous = previous;
			this.next = next;
		}
		
		/**
		 * Constructor for an unlinked node.
		 * @param d data of the node
		 */
		
		Node(T d){
			data = d;
			previous = null;
			next = null;
		}
		
		
		
		// Data operations
		
		
		
		/**
		 * Set (replace) the data of the node.
		 */
		
		public void setData(T d) { this.data = d; }
		
		
		/**
		 * Get the data of the node.
		 * @return data data of node
		 */		
		
		public T getData() { return data; } 
		
		
		
		
		
		// Next operations
		
		
		
		/**
		 * Set the reference to the next node.
		 */		
		
		public void setNext(Node n) { this.next = n; }
		
		
		/**
		 * Get the reference of the next node.
		 * @return next the next reference, null if empty
		 */	
		
		public Node getNext() { return next; }
		
		
		/**
		 * Check if the node has a next reference.
		 * @return true if node has next reference
		 */	
		
		public boolean hasNext() { return (next == null) ? false : true; }		
		
		
		
		
		
		// Previous operations
		
		
		/**
		 * Set the reference to the previous node.
		 */	
		
		public void setPrevious(Node n) { this.previous = n; }
		
		
		/**
		 * Get the reference of the previous node.
		 * @return previous the previous reference, null if empty
		 */	
		
		public Node getPrevious() { return previous; }
		
		
		/**
		 * Check if the node has a previous reference.
		 * @return true if node has previous reference
		 */	
		
		public boolean hasPrevious() { return (previous == null) ? false : true; }
		
		
	}

	
}
