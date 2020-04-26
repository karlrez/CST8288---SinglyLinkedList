package test.linkedlist;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import linkedlist.CSTLinkedList;

class CSTListTest<R> {
	CSTLinkedList<R> linkedList;
	String testString = "test";
	int listSize;
	final static String FIRST = "first";
	final static String SECOND = "second";
	final static String THIRD = "third";
	final static String FOURTH = "fourth";

	/*
	 * Creating an instance of CSTLinkedList and populating with 4 Strings.
	 * Also getting list size before we call test methods.
	 */
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<R> list = new ArrayList<>();
		
		list.add((R) FIRST);
		list.add((R) SECOND);
		list.add((R) THIRD);
		list.add((R) FOURTH);

		linkedList = new CSTLinkedList<>(list);
		listSize = linkedList.size();
	}

	@Test
	public void testAddFirst() {
		// calling addFirst() when size is 4
		linkedList.addFirst(testString);
		assertEquals(testString, linkedList.getFirst()); // string added should be in first position
		assertEquals(listSize + 1, linkedList.size()); // checking list size incremented by 1

		// calling addFirst() on null list
		linkedList.clear();
		linkedList.addFirst(testString);
		assertEquals(testString, linkedList.getFirst());

		// calling addFirst() when size is 1
		linkedList.addFirst(testString);
		assertEquals(testString, linkedList.getFirst());
	}

	@Test
	public void testAddLast() {
		// calling addLast() when size is 4
		linkedList.addLast(testString);
		assertEquals(testString, linkedList.getLast()); // testString should be in last index
		assertEquals(listSize + 1, linkedList.size()); // size should be incremented by 1

		// calling addLast() on null list
		linkedList.clear();
		linkedList.addLast(testString);
		assertEquals(testString, linkedList.getLast());

		// calling addLast() when size is 1
		linkedList.addLast(testString);
		assertEquals(testString, linkedList.getLast());
	}
	
	@Test
	public void testRemoveFirst() {
		Object removedElement;
		
		// calling removeFirst() when size is 4
		removedElement = linkedList.removeFirst();
		assertEquals(FIRST, removedElement); // check we removed the correct element
		assertEquals(SECOND, linkedList.getFirst()); // second value should be moved to index 0
		assertEquals(listSize - 1, linkedList.size()); // size should minus 1
		
		// calling removeFirst on null list
		linkedList.clear();
		assertThrows(IndexOutOfBoundsException.class, () -> linkedList.removeFirst());
		
		// calling removeFirst when size is 1
		linkedList.addLast(testString);
		removedElement = linkedList.removeFirst();
		assertEquals(testString, removedElement);
	}
	
	@Test
	public void testRemoveLast() {
		Object removedElement;
		
		// calling removeLast() when size is 4
		removedElement = linkedList.removeLast();
		assertEquals(FOURTH, removedElement); // check we removed the correct element
		assertEquals(THIRD, linkedList.getLast()); // check last index is correct
		assertEquals(listSize-1, linkedList.size());
		
		// calling removeLast on null list
		linkedList.clear();
		assertThrows(IndexOutOfBoundsException.class, () -> linkedList.removeLast());
		
		// calling removeLast when size is 1
		linkedList.addLast(testString);
		removedElement = linkedList.removeLast();
		assertEquals(testString, removedElement);
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(linkedList.isEmpty() == false); // @beforeEach initialized linkedList
		linkedList.clear();
		assertTrue(linkedList.isEmpty() == true);
	}
	
	@Test
	public void testSize() {
		assertEquals(listSize, linkedList.size()); // @beforeEach initialized linkedList to 4 nodes
		
		//adding 3 to list
		listSize += 3;
		linkedList.addFirst(testString);
		linkedList.addLast(testString);
		linkedList.insert(testString, 1);
		assertEquals(listSize, linkedList.size());
		
		//removing 3 from list
		listSize -=3;
		linkedList.removeFirst();
		linkedList.removeLast();
		linkedList.remove(1);
		assertEquals(listSize, linkedList.size());
		
		//null list
		linkedList.clear();
		assertEquals(0, linkedList.size());
	}
	
	@Test
	public void testClear() {
		linkedList.clear();
		assertEquals(0, linkedList.size());
	}
	
}
