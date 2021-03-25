/**
 * Implements a generic sorted double list using a provided Comparator. 
 * It extends BasicDoubleLinkedList class. 
 * @author binya
 */

import java.util.Comparator;





public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	private static Comparator comparator;
	
	SortedDoubleLinkedList(Comparator c){
		super();
		comparator = c;
	}
	
	
	
	// These methods are unused as the add() method automatically sorts any added element.
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	@Override SortedDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException { throw new UnsupportedOperationException(); }
	
	/**
	 * This operation is invalid for a sorted list.
	 */
	@Override SortedDoubleLinkedList<T> addToEnd(T data)   throws UnsupportedOperationException { throw new UnsupportedOperationException(); }
	
	
	
	/**
	 * Add a node to the linked list based on the order of comparison the passed comparator provides.
	 * @param data The data to insert into the list
	 * @param c The comparator by which the nodes of the linked list should be compared to the prospective data
	 * @return 
	 */	
	
	public SortedDoubleLinkedList<T> add(T data){
		
		
		it = iterator();
		Node t;
		
		if(size > 0) {
			
			if(size == 1) {
				
				if(comparator.compare(data, firstNode.getData()) <= 0) {
					
					t = new Node(data, null, firstNode);
					firstNode.setPrevious(t);
					firstNode = t;
					
				} else {
					
					t = new Node(data, firstNode, null);
					firstNode.setNext(t);
					lastNode = t;
					
				}
				
				size++;
				return this;
				
			}
			
			
			it.next();
			
			
			while(it.current != null) {
				
				// Try to find the first node that has data larger or equal to the passed data (we will call this the target node).
				if(comparator.compare(data, it.current.getData()) <= 0) {
					
					// If the target node has a previous node, relink the previous node's reference to the new node.
					if(it.current.hasPrevious()) {
						
						t = new Node(data, it.current.getPrevious(), it.current);
						it.current.getPrevious().setNext(t);
						size++;
						
					} else {
						
						// If it doesn't, make the new node and set it as the first.
						t = new Node(data, null, it.current);
						firstNode = t;
						size++;
						
					}
					
					// Regardless, the spot before the target node will be set to the new node.
					it.current.setPrevious(t);
					size++;
					return this;
					
				}
				
				// If the iterator gets to the last node (so no data smaller is found), the element belongs at the back.
				if(!it.hasNext()) {
					
					Node temp = new Node(data, lastNode, null);
					lastNode.setNext(temp);
					lastNode = temp;
					size++;
					return this;
					
				}
				
				it.next();
				
			}
			

			
		} else {
			
			// If the size is zero, the first node is null.
			firstNode = new Node(data);
			lastNode = firstNode;
			size++;
			return this;
			
		}
		
		return null;
		
		
	}
	
	
	
	
}
