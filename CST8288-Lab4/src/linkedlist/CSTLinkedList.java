package linkedlist;

import java.util.ArrayList;
import java.util.List;

public class CSTLinkedList<R> implements CSTList<R> {
	private Node<R> head;
	private Node<R> tail;
	private int size;

	public CSTLinkedList() {}

	/*
	 * Creates linked list with list[0] as head
	 */
	public CSTLinkedList(List<R> list) {
		for (R listItem : list)
			addLast(listItem);
	}

	/**
	 * return the node before the given index. very similar to
	 * {@link CSTList#get(int)}. can be used in other methods like insert and remove
	 * to get the node before index.
	 * 
	 * @param index - index of the node to find the node before it
	 * @return the node before the index of the given index. if index is zero or
	 *         size, null.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	private Node<R> getNodeBefore(int index) {
		isIndexValid(index);
		Node<R> n = head;

		//Either return head or iterate through linkedList to find node
		if (index == 1)
			return head;
		else {
			for (int i = 0; i < index - 1; i++) {
				n = n.next;
			}
		}
		return n;
	}

	/**
	 * if index is not between zero and list size throw an exception. can be used in
	 * any method that takes an argument of int index to error check.
	 * 
	 * @param index - index to check
	 * @throws IndexOutOfBoundsException if index is not between zero and list size
	 */
	private void isIndexValid(int index) throws IndexOutOfBoundsException {
		if (index + 1 > size)
			throw new IndexOutOfBoundsException();
		else if (index < 0)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public String toString() {
		Node<R> n = head;
		String returnString = null;

		while (n != null) {
			returnString += n.toString() + "\n";
			n = n.next;
		}
		return returnString;
	}

	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<>();
		list.add("first");
		list.add("second");
		list.add("third");
		list.add("fourth");

		CSTLinkedList linkedList = new CSTLinkedList(list);
		System.out.println(linkedList.toString());
		
		for (int i=0; i < linkedList.size; i++)
			System.out.println(linkedList.get(i));
	}

	/**
	 * add the the given element to the beginning of the list. can simply call the
	 * methods {@link CSTList#insert(Object, int)}.
	 * 
	 * @param r - the new element to be added
	 */
	// @Override
	public void addFirst(Object r) {
		Node<R> newNode = new Node<>((R) r);

		if (head == null) {
			head = tail = newNode;
			head.next = tail;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}

	/**
	 * add the the given element to the end of the list can simply call the method
	 * {@link CSTList#insert(Object, int)}.
	 * 
	 * @param r - the new element to be added
	 */
	// @Override
	public void addLast(Object r) {
		Node<R> newNode = new Node<>((R) r);

		if (head == null) {
			head = tail = newNode;
			head.next = tail;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	/**
	 * add the the given element to the at provided index. the old index will be
	 * pushed forward.
	 * 
	 * @param r     - the new element to be added
	 * @param index - index of position to be inserted
	 */
	@Override
	public void insert(Object r, int index) {
		isIndexValid(index);
		Node<R> newNode = new Node<>((R) r);

		// if inserting at head
		if (index == 0 || size == 0) {
			addFirst(r);
			return; // return now to avoid incrementing size twice
		}
		// if inserting at tail
		if (index + 1 == size) {
			addLast(r);
			return;
		}
		// all other cases
		// join newNode with second half
		newNode.next = getNodeBefore(index + 1);

		// join newNode with first half
		getNodeBefore(index).next = newNode;
		size++;
	}

	/**
	 * remove the first element. can simply call the method
	 * {@link CSTList#remove(int)}.
	 * 
	 * @return removed element
	 */
	@Override
	public R removeFirst() {
		return remove(0);
	}

	/**
	 * remove the last element. can simply call the method
	 * {@link CSTList#remove(int)}.
	 * 
	 * @return removed element
	 */
	@Override
	public R removeLast() {
		return remove(size - 1);
	}

	/**
	 * remove the element at the given index
	 * 
	 * @return element as the given index after removal.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R remove(int index) {
		isIndexValid(index);
		R returnedElement;

		if (size == 0) // if empty list, return null
			return null;
		if (index == 0) { // if removing at index 0
			returnedElement = head.element;
			head = head.next;
			size--;
			return returnedElement;
		}

		// all other cases
		Node<R> n = head;
		for (int i = 0; i < index; i++)
			n = n.next;
		returnedElement = n.element;

		// Getting nodes before and after index being removed
		Node<R> firstHalf = getNodeBefore(index);

		if (index == size - 1) { // assign tail if we are removing at last index
			tail = firstHalf;
			tail.next = null;
		} else // else join second half
			firstHalf.next = getNodeBefore(index + 1);

		size--;
		return returnedElement;
	}

	/**
	 * @return the first element in the list. can simply call the method
	 *         {@link CSTList#get(int)}.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R getFirst() {
		return head.element;
	}

	/**
	 * @return the last element in the list. can simply call the method
	 *         {@link CSTList#get(int)}.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R getLast() {
		return tail.element;
	}

	/**
	 * @return the element as given index,
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R get(int index) {
		isIndexValid(index); // will throw error if not valid
		Node<R> n = head;
		Object returnObject = null;

		if (isEmpty())
			return null;
		
		else if (index == 0)
			return getFirst();

		else if (index + 1 == size)
			return getLast();

		else {
			for (int i = 0; i < index; i++) {
				returnObject = n.next.element;
				n = n.next;
			}
		}
		return (R) returnObject;
	}

	/**
	 * @return true of list is empty
	 */
	@Override
	public boolean isEmpty() {
		return head == null ? true : false;
	}

	/**
	 * @return current size of list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * clear the current list by setting head and tail to null and size to zero.
	 */
	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	public class Node<T> {

		private Node<T> next;
		private T element;

		private Node(T t) {
			element = t;
		}

		private Node(T t, Node<T> next) {
			element = t;
			this.next = next;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((element == null) ? 0 : element.hashCode());
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node<T> other = (Node) obj;
			if (element == null) {
				if (other.element != null)
					return false;
			} else if (!element.equals(other.element))
				return false;
			if (next == null) {
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next))
				return false;
			return true;
		}

		@Override
		public String toString() {
			if (next != null)
				return "Node [next=" + next.element + ", element=" + element + "]";
			else
				return "Node [next=" + next + ", element=" + element + "]";
		}

	}

}
