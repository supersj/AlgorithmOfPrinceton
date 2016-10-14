/**
 * Created by Superj on 2016/10/10.
 */


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 *
 */
public class PercolationStats {
    private int dimension;
    private final double con = 1.96;
    private double[] count;
    private final int ptrails;
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new  java.lang.IllegalArgumentException();
        }
        dimension = n;
        ptrails = trials;
        count = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                p.open(x, y);
            }
            count[i] = statistic(p) / (n * n);
        }
    }    // perform trials independent experiments on an n-by-n grid
    private double statistic(final Percolation p) {
        double numbers = 0;
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                if (p.isOpen(i, j)) {
                    numbers++;
                }
            }
        }
        return numbers;
    }
    public double mean() {
        return StdStats.mean(count);
    }                 // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(count);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean() - con * stddev() / java.lang.StrictMath.sqrt(ptrails);
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + con * stddev() / java.lang.StrictMath.sqrt(ptrails);
    }                  // high endpoint of 95% confidence interval

    public static void main(String[] args) {

    }    // test client (described below)
}