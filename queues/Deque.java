/**
 * Created by Superj on 2016/10/14.
 */
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node before;
    }
    private int length;
    private Node first, last;

    public Deque() {
        length = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
       return length == 0;
    }
    public int size() {
       return length;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            first.next = null;
            first.before = null;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.before = first;
            first.before = null;
        }
        length++;
    }
    public void  addLast (Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.before = null;
            first = last;
        } else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            oldlast.next = last;
            last.before = oldlast;
            last.next = null;
        }
        length++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        length--;
        if (length == 0) {
            last = first;
        } else {
            first.before = null;
        }
        return item;
    }
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        last = last.before;
        length--;
        if (length == 0) {
            first = last;
        } else {
           last.next = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {

    }
}
