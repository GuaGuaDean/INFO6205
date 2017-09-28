/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {
    private int x = 0;
    private int y = 0;
    private int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private Random rand = new Random();

    public void move(int dx, int dy) {
    	x += dx;
    	y += dy;
    }

    public void randomWalk(int n) {
    	for (int i = 0; i < n; i++) {
    		int[] dir = dirs[rand.nextInt(dirs.length)];
    		x += dir[0];
    		y += dir[1];
    	}
    }

    public double distance() {
        return Math.sqrt(x*x + y*y);
    }
}
