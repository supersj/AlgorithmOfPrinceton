/**
 * Created by Superj on 2016/10/14.
 */
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] elems = (Item[]) new Object[0];
    private int lenElems = 0;
    public RandomizedQueue() {

    }                // construct an empty randomized queue
    public boolean isEmpty() {
        return lenElems == 0;
    }                // is the queue empty?
    public int size() {
        return lenElems;
    }                       // return the number of items on the queue

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        int arrLength = elems.length;
        int numElemToCopy;
        if (arrLength > max) {
            numElemToCopy = max;
        } else {
            numElemToCopy = arrLength;
        }
        System.arraycopy(elems, 0, temp, 0, numElemToCopy);
        elems = temp;
    }
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        final int size = elems.length;
        if (size == 0) {
            resize(1);
        } else if (size <= lenElems) {
            resize(2 * size);
        }
        elems[lenElems++] = item;
    }
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int rand = StdRandom.uniform(lenElems);
        Item obj = elems[rand];
        elems[rand] = elems[--lenElems];
        elems[lenElems] = null;
        int arrLength = elems.length;
        if (lenElems > 0 && lenElems == arrLength / 4) {
            resize(arrLength / 2);
        }
        return obj;
    }
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item obj = null;
        while (obj == null) {
            int rand = StdRandom.uniform(lenElems);
            obj = elems[rand];
        }
        return obj;
    }
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>(elems);
    }

    private static class RandomizedQueueIterator<Item>
            implements Iterator<Item> {
        private RandomizedQueue<Item> newElems = new RandomizedQueue<Item>();

        public RandomizedQueueIterator(final Item[] items) {
            for (Item o : items) {
                if (o == null) {
                    break;
                }
                newElems.enqueue(o);
            }
        }

        @Override
        public boolean hasNext() {
            return !newElems.isEmpty();
        }

        @Override
        public Item next() {
            if (newElems.isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return newElems.dequeue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) { }   // unit testing
}