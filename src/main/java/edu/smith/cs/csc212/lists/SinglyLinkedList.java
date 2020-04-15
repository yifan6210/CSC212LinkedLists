package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Singly-Linked List is a list that has only knowledge of its very first
 * element. Elements after that are chained, ending with a null node.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();	
		T front = this.start.value;
		this.start = this.start.next;
		return front;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if (this.start.next == null) {	
			return removeFront();	
		}else {			
			Node<T> secondtolast = null;
			for (Node<T> current = this.start; current.next!=null; current = current.next) {
				secondtolast = current;
			}
			assert(secondtolast.next.next == null);
			T lastValue = secondtolast.next.value;
			secondtolast.next = null;
			return lastValue;
			
		}
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		
		if(index == 0) {
			return removeFront();
			
		} else if (index == this.size()-1) {
			return removeBack();
		}else {
			T removed = getIndex(index);
			Node<T> last = null;
			for (Node<T> current = this.start; current.value!=removed; current = current.next) {
				last = current;
			}
			assert(last.next.value == removed);
			last.next = last.next.next;
			return removed;

		}
	}
	

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		if (isEmpty()== true){
			addFront(item);
			return;
		}else {
			Node<T> last = null;
			for (Node<T> current = this.start; current!=null; current = current.next) {
				last = current;
			}
		
			assert(last.next == null);
			last.next = new Node<T>(item, null);
			return;
			
		}
	}

	@Override
	public void addIndex(int index, T item) {
		if(index < 0 || index > this.size()) {
			throw new BadIndexError(index);
			
		} else if (index == 0) {
			addFront(item);
			return;
		}else if (index== this.size()) {
			addBack(item);
			return;
		} else {
			int counter = 0;

			for(Node<T> current = this.start;current != null; current = current.next) {
				
				if (counter == index-1) {
				Node<T> added =  new Node<T> (item);
				added.next = current.next;	
				current.next = added;
				}
				counter ++;
			}

			return;
		}
	}
		

	@Override
	public T getFront() {
		checkNotEmpty();
		T front = this.start.value;
		return front;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		Node<T> last = null;
		for (Node<T> current = this.start; current!=null; current = current.next) {
			last = current;
		}
		return last.value;
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	// simply 
	public void setIndex(int index, T value) {
		checkNotEmpty();
		if(index < 0 || index >= this.size()) {
			throw new BadIndexError(index);
		} else {
			int at = 0;
			for (Node<T> n = this.start; n != null; n = n.next) {
				if (at++ == index) {
					n.value = value;
					return;
				}

			}
		}
	}
	

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with a friend.
		 * @param value - the value to put in it.
		 * @param next - the friend of this node.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
		
		public T value() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Alternate constructor; create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.next = null;
		}
	}

}
