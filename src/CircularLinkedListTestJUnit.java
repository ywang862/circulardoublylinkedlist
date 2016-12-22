import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yufeng Wang
 * @version 1.0
 */
public class CircularLinkedListTestJUnit {
    private static final int TIMEOUT = 200;
    private CircularDoublyLinkedList<Integer> list;
    private CircularDoublyLinkedList<String> stringList;

    @Before
    public void setup() {
        list = new CircularDoublyLinkedList<>();
        stringList = new CircularDoublyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        // if size == 0
        list.addToFront(84);
        assertEquals(1, list.size());
        LinkedListNode<Integer> node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 84, node.getPrevious().getData());
        assertEquals((Integer) 84, node.getNext().getData());
        // if size == 1
        list.addToFront(12);
        assertEquals(2, list.size());
        node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 12, node.getData());
        assertEquals((Integer) 84, node.getPrevious().getData());
        assertEquals((Integer) 84, node.getNext().getData());
        assertEquals((Integer) 12, node.getPrevious().getNext().getData());
        assertEquals((Integer) 12, node.getNext().getPrevious().getData());
        // if size > 1
        list.addToFront(95);
        assertEquals(3, list.size());
        node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 95, node.getData());
        assertEquals((Integer) 84, node.getPrevious().getData());
        assertEquals((Integer) 12, node.getNext().getData());
        assertEquals((Integer) 95, node.getNext().getPrevious().getData());
        assertEquals((Integer) 95, node.getPrevious().getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        // if size == 0
        list.addToBack(84);
        assertEquals(1, list.size());
        LinkedListNode<Integer> node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 84, node.getPrevious().getData());
        assertEquals((Integer) 84, node.getNext().getData());
        // if size == 1
        list.addToBack(12);
        assertEquals(2, list.size());
        node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 12, node.getPrevious().getData());
        assertEquals((Integer) 12, node.getNext().getData());
        assertEquals((Integer) 84, node.getNext().getPrevious().getData());
        assertEquals((Integer) 84, node.getNext().getNext().getData());
        // if size >= 2
        list.addToBack(95);
        assertEquals(3, list.size());
        node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 95, node.getPrevious().getData());
        assertEquals((Integer) 12, node.getNext().getData());
        assertEquals((Integer) 95, node.getNext().getNext().getData());
        assertEquals((Integer) 95, node.getNext().getNext().getData());
        list.addToBack(48);
        assertEquals(4, list.size());
        node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertNotNull(node.getNext());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 48, node.getPrevious().getData());
        assertEquals((Integer) 95, node.getPrevious().getPrevious().getData());
        assertEquals((Integer) 48, node.getNext().getNext()
                .getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, 84);
        list.addAtIndex(1, 23);
        list.addAtIndex(1, 83);
        list.addAtIndex(0, 96);
        list.addAtIndex(4, 0);
        list.addAtIndex(2, 1);

        assertEquals(6, list.size());

        LinkedListNode<Integer> node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 96, node.getData());
        assertEquals((Integer) 0, node.getPrevious().getData());
        assertEquals((Integer) 84, node.getNext().getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 96, node.getPrevious().getData());
        assertEquals((Integer) 1, node.getNext().getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 1, node.getData());
        assertEquals((Integer) 84, node.getPrevious().getData());
        assertEquals((Integer) 83, node.getNext().getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 83, node.getData());
        assertEquals((Integer) 1, node.getPrevious().getData());
        assertEquals((Integer) 23, node.getNext().getData());
        node = node.getNext();
        assertNotNull(node.getNext());
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 23, node.getData());
        assertEquals((Integer) 83, node.getPrevious().getData());
        assertEquals((Integer) 0, node.getNext().getData());
        node = node.getNext();
        assertNotNull(node.getNext());
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 0, node.getData());
        assertEquals((Integer) 23, node.getPrevious().getData());
        assertEquals((Integer) 96, node.getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFront() {
        // if size == 0
        assertEquals(null, list.removeFromFront());
        // if size == 1
        list.addToFront(12);
        LinkedListNode<Integer> node = list.getHead();
        assertEquals((Integer) 12, list.removeFromFront());
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        // if size > 1
        list.addToFront(84);
        list.addToBack(23);
        list.addAtIndex(1, 83);
        list.addToFront(96);
        list.addAtIndex(3, 58);

        assertEquals(5, list.size());

        assertEquals((Integer) 96, list.removeFromFront());

        assertEquals(4, list.size());

        node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 23, node.getPrevious().getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 83, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 58, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 23, node.getData());
        assertNotNull(node.getNext());
        assertEquals((Integer) 84, node.getNext().getData());
    }

    @Test
    public void testStringList() {
        stringList.addAtIndex(0, "dog");
        stringList.addAtIndex(1, "cat");
        stringList.addAtIndex(1, "mouse");
        stringList.addAtIndex(1, "mouse");
        stringList.addToBack("back");
        stringList.addToFront("front");

        assertEquals(6, stringList.size());
        assertEquals("front", stringList.getHead().getData());
        String[] expected = {"front", "dog", "mouse", "mouse", "cat", "back"};
        assertArrayEquals(expected, stringList.toArray());

        assertEquals(2, stringList.removeFirstOccurrence("mouse"));
        assertEquals(5, stringList.size());
        assertEquals(true, stringList.removeAllOccurrences("mouse"));
        assertEquals(4, stringList.size());
        String[] expected1 = {"front", "dog", "cat", "back"};
        assertArrayEquals(expected1, stringList.toArray());
        assertEquals("front", stringList.removeAtIndex(0));
        assertEquals(3, stringList.size());
        assertEquals("dog", stringList.getHead().getData());
        assertEquals("back", stringList.removeAtIndex(2));
        assertEquals(2, stringList.size());
        assertEquals("cat", stringList.get(1));
        assertEquals(true, stringList.removeAllOccurrences("dog"));
        assertEquals(1, stringList.size());
        assertEquals("cat", stringList.getHead().getData());
        assertEquals("cat", stringList.removeFromBack());
        assertEquals(0, stringList.size());
        assertEquals(null, stringList.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBack() {
        // if size == 0
        assertEquals(null, list.removeFromBack());
        // if size == 1
        list.addToFront(12);
        LinkedListNode<Integer> node = list.getHead();
        assertEquals((Integer) 12, list.removeFromBack());
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        // if size > 1
        list.addToFront(84);
        list.addToBack(23);
        list.addAtIndex(1, 83);
        list.addToFront(96);
        list.addAtIndex(3, 58);
        list.addToBack(100);

        assertEquals(6, list.size());

        node = list.getHead();

        assertEquals((Integer) 100, list.removeFromBack());
        assertEquals((Integer) 23, node.getPrevious().getData());

        assertEquals(5, list.size());

        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 96, node.getData());
        assertEquals((Integer) 23, node.getPrevious().getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 96, node.getPrevious().getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 83, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 58, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 23, node.getData());
        assertNotNull(node.getNext());
        assertEquals((Integer) 96, node.getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        list.addAtIndex(0, 84);
        list.addAtIndex(1, 23);
        list.addAtIndex(1, 83);
        list.addAtIndex(0, 96);
        list.addAtIndex(3, 58);

        assertEquals(5, list.size());
        // remove from front of list
        assertEquals((Integer) 96, list.removeAtIndex(0));
        LinkedListNode<Integer> node = list.getHead();
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 23, node.getPrevious().getData());
        assertEquals((Integer) 83, node.getNext().getData());
        assertEquals(4, list.size());
        // remove from end of list
        assertEquals((Integer) 23, list.removeAtIndex(3));
        node = list.getHead();
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 58, node.getPrevious().getData());
        assertEquals((Integer) 84, node.getPrevious().getNext().getData());
        assertEquals(3, list.size());
        // remove from middle of list
        assertEquals((Integer) 83, list.removeAtIndex(1));
        node = list.getHead();
        assertEquals((Integer) 84, node.getData());
        assertEquals((Integer) 58, node.getNext().getData());
        assertEquals((Integer) 84, node.getNext().getPrevious().getData());
        assertEquals(2, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        // if size == 1
        list.addToFront(84);
        assertEquals((Integer) 84, list.get(0));
        // if size > 1
        list.addToBack(23);
        list.addAtIndex(1, 83);
        list.addToFront(96);
        list.addAtIndex(3, 58);

        assertEquals(5, list.size());
        assertEquals((Integer) 96, list.get(0));
        assertEquals((Integer) 84, list.get(1));
        assertEquals((Integer) 83, list.get(2));
        assertEquals((Integer) 58, list.get(3));
        assertEquals((Integer) 23, list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testArray() {
        list.addToFront(84);
        LinkedListNode<Integer> node = list.getHead();
        assertEquals((Integer) 84, node.getData());

        list.addToBack(23);
        list.addAtIndex(1, 83);
        list.addToFront(96);
        list.addAtIndex(3, 58);

        assertEquals(5, list.size());

        Integer[] expected = new Integer[] {96, 84, 83, 58, 23};
        assertArrayEquals(expected, list.toArray());
        node = list.getHead();
        assertEquals((Integer) 96, node.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEmpty() {
        Integer[] expected = new Integer[] {};
        assertArrayEquals(expected, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void removeFirstOccurrence() {
        list.addToFront(84);
        list.addToBack(23);
        list.addAtIndex(1, 83);
        list.addToFront(96);
        list.addAtIndex(3, 58);

        LinkedListNode<Integer> node = list.getHead();
        assertEquals(0, list.removeFirstOccurrence(96));
        node = list.getHead();
        assertEquals((Integer) 84, node.getData());
        assertEquals(4, list.size());

        assertEquals(1, list.removeFirstOccurrence(83));
        assertEquals(3, list.size());
        assertEquals(2, list.removeFirstOccurrence(23));
        assertEquals(2, list.size());
    }

    @Test(expected = NoSuchElementException.class, timeout = TIMEOUT)
    public void testRemoveNotInList() {
        list.addToFront(84);
        list.addToBack(23);
        list.addAtIndex(1, 83);
        list.addToFront(96);
        list.addAtIndex(3, 58);

        list.removeFirstOccurrence(42);
    }

    @Test(timeout = TIMEOUT)
    public void removeAllOccurrences() {
        list.addToBack(1);
        list.addToBack(1);
        list.addToBack(20);
        list.addToBack(1);
        list.addToBack(21);
        list.addToBack(1);
        list.addToBack(22);
        list.addToBack(1);
        list.addToBack(23);
        list.addToBack(1);

        assertEquals(10, list.size());
        LinkedListNode<Integer> node = list.getHead();
        assertEquals((Integer) 1, node.getData());

        assertEquals(false, list.removeAllOccurrences(0));
        assertEquals(10, list.size());

        assertEquals(true, list.removeAllOccurrences(1));
        node = list.getHead();
        assertEquals((Integer) 20, node.getData());
        assertEquals((Integer) 21, node.getNext().getData());
        assertEquals((Integer) 23, node.getPrevious().getData());
        assertEquals(4, list.size());

    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        Integer[] initialValues = new Integer[] {38, 43, 29, 59, 83};

        list = new CircularDoublyLinkedList<>(initialValues);

        assertEquals(5, list.size());

        LinkedListNode<Integer> node = list.getHead();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 38, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 43, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 29, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 59, node.getData());
        node = node.getNext();
        assertNotNull(node);
        assertNotNull(node.getPrevious());
        assertEquals((Integer) 83, node.getData());
        assertNotNull(node.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void isEmpty() {
        assertEquals(true, list.isEmpty());
        list.addToFront(84);
        assertEquals(false, list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void size() {
        assertEquals(0, list.size());
        list.addToFront(84);
        assertEquals(1, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void clear() {
        list.clear();
        assertEquals(0, list.size());
        list.addToFront(84);
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());

    }
}