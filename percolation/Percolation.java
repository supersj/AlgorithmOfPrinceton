
/**
 * Created by Superj on 2016/10/9.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[]            gridOpen;
    private int                  dimension;
    private int                  percol;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF tf;
    public Percolation(int n) {    // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        dimension = n;

        int total = n * n + 2;
        tf                  = new WeightedQuickUnionUF(total);
        uf                  = new WeightedQuickUnionUF(total);
        gridOpen            = new boolean[total];
        gridOpen[0]         = true;
    }

    public static void main(String[] args) { }    // test client (optional)

    public void open(int i, int j) {             // open site (row i, column j) if it is not open already
        validate(i, j);
        int index = xyTo1d(i, j);
        int up    = xyTo1d(i - 1, j);
        int down  = xyTo1d(i + 1, j);
        int left  = xyTo1d(i, j - 1);
        int right = xyTo1d(i, j + 1);

        gridOpen[index] = true;

        if ((i == 1) || (i == dimension)) {
            if ((j == 1) && gridOpen[right]) {
                uf.union(index, right);
                tf.union(index, right);
            }

            if ((j == dimension) && gridOpen[left]) {
                uf.union(index, left);
                tf.union(index, left);
            }

            if (i == 1) {
                uf.union(0, index);
            }

            if (i == dimension) {
                if ((j == 1) && gridOpen[right]) {
                    uf.union(index, right);
                    tf.union(index, right);
                } else if ((j == dimension) && gridOpen[left]) {
                    uf.union(index, left);
                    tf.union(index, left);
                } else if (j != 1 && j != dimension) {
                    if (gridOpen[left]) {
                        uf.union(index, left);
                        tf.union(index, left);
                    }
                    if (gridOpen[right]) {
                        uf.union(index, right);
                        tf.union(index, right);
                    }
                }
                tf.union(dimension * dimension + 1, index);
            }

            if ((i == 1) && gridOpen[down]) {
                uf.union(index, down);
                tf.union(index, down);
            }

            if ((i == dimension) && gridOpen[up]) {
                uf.union(index, up);
                tf.union(index, up);
            }
            boolean u = uf.connected(0, index);
            boolean t = tf.connected(index, dimension * dimension + 1);
            if (u && t) {
                percol = 1;
            }
            return;
        }

        if ((j == 1) || (j == dimension)) {
            if (gridOpen[up]) {
                uf.union(index, up);
                tf.union(index, up);
            }

            if (gridOpen[down]) {
                uf.union(index, down);
                tf.union(index, down);
            }

            if ((j == 1) && gridOpen[right]) {
                uf.union(index, right);
                tf.union(index, right);
                boolean u = uf.connected(0, index);
                boolean t = tf.connected(index, dimension * dimension + 1);
                if (u && t) {
                    percol = 1;
                }
                return;
            }

            if ((j == dimension) && gridOpen[left]) {
                uf.union(index, left);
                tf.union(index, left);
            }
            boolean u = uf.connected(0, index);
            boolean t = tf.connected(index, dimension * dimension + 1);
            if (u && t) {
                percol = 1;
            }
            return;
        }

        if (gridOpen[up]) {
            uf.union(index, up);
            tf.union(index, up);
        }

        if (gridOpen[down]) {
            uf.union(index, down);
            tf.union(index, down);
        }

        if (gridOpen[left]) {
            uf.union(index, left);
            tf.union(index, left);
        }

        if (gridOpen[right]) {
            uf.union(index, right);
            tf.union(index, right);
        }
        boolean u = uf.connected(0, index);
        boolean t = tf.connected(index, dimension * dimension + 1);
        if (u && t) {
            percol = 1;
        }
    }

    public boolean percolates() {
        return percol == 1;
    }    // does the system percolate?

    private void validate(int i, int j) {
        if ((i <= 0) || (i > dimension) || (j <= 0) || (j > dimension)) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
    }

    private int xyTo1d(int i, int j) {
        return (i - 1) * dimension + j;
    }

    public boolean isFull(int i, int j) {
        validate(i, j);

        return uf.connected(0, xyTo1d(i, j));
    }    // is site (row i, column j) full?

    public boolean isOpen(int i, int j) {
        validate(i, j);

        return gridOpen[xyTo1d(i, j)];
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
