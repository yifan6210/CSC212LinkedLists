package edu.smith.cs.csc212.lists;

import edu.smith.cs.csc212.lists.SinglyLinkedList.Node;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.*;

/**
 * A Doubly-Linked List is a list based on nodes that know of their successor and predecessor.
 * @author jfoley
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends ListADT<T> {
	/**
	 * This is a reference to the first node in this list.
	 */
	Node<T> start;
	/**
	 * This is a reference to the last node in this list.
	 */
	Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		if (size() == 1) {
			T front = start.value;
			start = end = null;
			return front;
		} else {
			T front =  start.value;
			start = start.after;
			start.before = null;
			return front;

		}
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if (this.size()==1) {
			return removeFront();
		}else {
			T last = end.value;
			end = end.before;
			end.after = null;
			return last;
		}
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		
		if(index == 0) {
			return removeFront();

		} else if (index == size()-1) {
			return removeBack();
		}else {
			T removed = getIndex(index);
			Node<T> beforeIndex = null;
			Node<T> afterIndex = null;
			int at = 0;
			for (Node<T> n = this.start; n != null; n = n.after ) {
				if (at++ == index) {
					beforeIndex = n.before;
					afterIndex = n.after;
					beforeIndex.after = afterIndex;
					afterIndex.before = beforeIndex;
				}
			}
			return removed;
		}
	}

	@Override
	public void addFront(T item) {
		if (this.start == null) {
			start = end = new Node<T> (item);
		} else {
			Node <T> second = start;
			start = new Node<T>(item);
			start.after = second;
			second.before = start;
			return;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {

		if(index < 0 || index > size()) {
			throw new BadIndexError(index);

		} else if (index == 0) {
			addFront(item);
			return;
		}else if (index== this.size()) {
			addBack(item);
			return;
		} else {

			Node<T> beforeIndex = null;

			int at = 0;
			for (Node<T> n = this.start; n != null; n= n.after) {
				if (at++ == index) {
					Node<T> added = new Node<T> (item);
					beforeIndex = n.before;
					beforeIndex.after = added;
					added.before = beforeIndex;
					n.before = added;
					added.after = n;
				}
			}
			return;
		}
	}
	

	@Override
	public T getFront() {
		checkNotEmpty();
		return start.value;

	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return end.value;
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	
	public void setIndex(int index, T value) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			if (at++ == index) {
				n.value = value;
				return ;
			}
	}
		throw new BadIndexError(index);
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}
}
