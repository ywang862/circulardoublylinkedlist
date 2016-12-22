/**
 * Your implementation of a CircularDoublyLinkedList
 *
 * @author Yufeng Wang
 * @version 1.0
 */
public class CircularDoublyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Creates an empty circular doubly-linked list.
     */
    public CircularDoublyLinkedList() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Creates a circular doubly-linked list with
     * {@code data} added to the list in order.
     * @param data The data to be added to the LinkedList.
     * @throws java.lang.IllegalArgumentException if {@code data} is null or any
     * item in {@code data} is null.
     */
    public CircularDoublyLinkedList(T[] data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        int len = data.length;
        size = len;
        LinkedListNode<T> a = new LinkedListNode<T>(data[0],null,null);
        head = a;
        LinkedListNode<T> cur = a;
        cur.setPrevious(a);
        cur.setNext(a);
        for (int i = 1; i < len; i++) {
            if (data[i] == null) {
                throw new java.lang.IllegalArgumentException("data cannot be null");
            }
            LinkedListNode<T> b = new LinkedListNode<>(data[i], null,null);
            cur.setNext(b);
            b.setPrevious(cur);
            cur = b;
        }
        cur.setNext(a);
        a.setPrevious(cur);
    }

    @Override
    public void addAtIndex(int index, T data) {
        if ((index > size) || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("out of bound");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == (size)) {
            addToBack(data);
        } else {
            LinkedListNode<T> cur = head;
            size++;
            LinkedListNode<T> a = new LinkedListNode<T>(data, null, null);
            for (int i = 0; i < index; i++) {
                cur = cur.getNext();
            }
            LinkedListNode<T> b = cur.getPrevious();
            b.setNext(a);
            a.setPrevious(b);
            cur.setPrevious(a);
            a.setNext(cur);

        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("out of bound");
        }
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == (size - 1)) {
            return head.getPrevious().getData();
        }
        LinkedListNode<T> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur.getData();
    }

    @Override
    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("out of bound");
        }
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == (size - 1)) {
            return removeFromBack();
        }
        LinkedListNode<T> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        cur.getPrevious().setNext(cur.getNext());
        cur.getNext().setPrevious(cur.getPrevious());
        size--;
        return cur.getData();
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        size++;
        LinkedListNode<T> a = new LinkedListNode<>(data, null, null);

        if (head == null) {
            a.setPrevious(a);
            a.setNext(a);
            head = a;
        } else {
            LinkedListNode<T> c = head;
            LinkedListNode<T> b = head.getPrevious();
            a.setPrevious(b);
            b.setNext(a);
            a.setNext(c);
            c.setPrevious(a);
            head = a;
        }


    }


    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        size++;
        LinkedListNode<T> a = new LinkedListNode<>(data, null, null);
        if (head == null) {
            head = a;
            a.setPrevious(a);
            a.setNext(a);
        } else {
            a.setNext(head);

            a.setPrevious(head.getPrevious());
            head.getPrevious().setNext(a);
            head.setPrevious(a);
        }
    }


    @Override
    public T removeFromFront() {
        if (head == null) {
            return null;
        }
        LinkedListNode<T> a = head.getNext();
        LinkedListNode<T> b = head;
        size--;
        if (head.getPrevious() == head) {
            head = null;
            return a.getData();
        } else {
            a.setPrevious(head.getPrevious());
            head.getPrevious().setNext(a);
            head = a;
            return b.getData();
        }
    }

    @Override
    public T removeFromBack() {
        if (head == null) {
            return null;
        }
        LinkedListNode<T> a = head.getPrevious();

        size--;
        if (head.getPrevious() == head) {
            head = null;
            return a.getData();
        } else {
            head.setPrevious(a.getPrevious());
            a.getPrevious().setNext(head);
            return a.getData();
        }
    }

    @Override
    public int removeFirstOccurrence(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        if (head == null) {
            throw new java.util.NoSuchElementException("No such element");
        }
        int num = 0;
        LinkedListNode<T> cur = head;
        for (int i = 0; i < size; i++) {
            if (cur.getData().equals(data)) {
                if (num == 0) {
                    removeFromFront();
                    return 0;
                }
                cur.getPrevious().setNext(cur.getNext());
                cur.getNext().setPrevious(cur.getPrevious());
                size--;

                return num;
            }
            cur = cur.getNext();
            num++;
        }
        throw new java.util.NoSuchElementException("No such element");

    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        if (head == null) {
            throw new java.util.NoSuchElementException("No such element");
        }
        int len = size;
        LinkedListNode<T> cur = head;
        boolean existence = false;
        for (int i = 0; i < len; i++) {
            if (cur.getData().equals(data)) {
                if (cur == head) {
                    removeFromFront();
                } else {
                    LinkedListNode<T> nxt = cur.getNext();
                    cur.getPrevious().setNext(nxt);
                    nxt.setPrevious(cur.getPrevious());
                    size--;
                }
                existence = true;
            }
            cur = cur.getNext();
        }
        return existence;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        LinkedListNode<T> cur = head;
        int a = 0;
        while (a < size) {
            array[a] = cur.getData();
            cur = cur.getNext();
            a++;
        }
        return array;


    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    /* DO NOT MODIFY THIS METHOD */
    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }
}
