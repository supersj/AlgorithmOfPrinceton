/**
 * Created by Superj on 2016/10/14.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        int times = Integer.parseInt(args[0]);
        for (int i = 0; i < times; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
