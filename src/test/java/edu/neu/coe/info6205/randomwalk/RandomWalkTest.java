/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomWalkTest {

    /**
     */
    @Test
    public void testMove1() {
        RandomWalk rw = new RandomWalk();
        rw.move(1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(1, 0);
        assertEquals(rw.distance(), 2.0, 1.0E-7);
        rw.move(-1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(-1, 0);
        assertEquals(rw.distance(), 0.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove2() {
        RandomWalk rw = new RandomWalk();
        rw.move(0, 1);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(0, 1);
        assertEquals(rw.distance(), 2.0, 1.0E-7);
        rw.move(0, -1);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
        rw.move(0, -1);
        assertEquals(rw.distance(), 0.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testMove3() {
        RandomWalk rw = new RandomWalk();
        double root2 = Math.sqrt(2);
        rw.move(1, 1);
        assertEquals(rw.distance(), root2, 1.0E-7);
        rw.move(1, 1);
        assertEquals(rw.distance(), 2 * root2, 1.0E-7);
        rw.move(0, -2);
        assertEquals(rw.distance(), 2.0, 1.0E-7);
        rw.move(-2, 0);
        assertEquals(rw.distance(), 0.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testRandomWalk() {
        RandomWalk rw = new RandomWalk();
        rw.move(1, 0);
        assertEquals(rw.distance(), 1.0, 1.0E-7);
    }

    /**
     */
    @Test
    public void testRandomWalkDistance10Steps() {
        RandomWalk rw = new RandomWalk();
        System.out.println("Testing 10 steps random walk: ");
        double res = 0.0;
        for (int i = 0; i < 1000; i++) {
        	rw.randomWalk(10);
        	res += rw.distance();
        	rw.setX(0);
        	rw.setY(0);
        }
        res = res/1000;
        System.out.println("The average distance after 10 steps is: " + res);
    }
    
    /**
     */
    @Test
    public void testRandomWalkDistance100Steps() {
        RandomWalk rw = new RandomWalk();
        System.out.println("Testing 100 steps random walk: ");
        double res = 0.0;
        for (int i = 0; i < 1000; i++) {
        	rw.randomWalk(100);
        	res += rw.distance();
        	rw.setX(0);
        	rw.setY(0);
        }
        res = res/1000;
        System.out.println("The average distance after 100 steps is: " + res);
    }
    
    /**
     */
    @Test
    public void testRandomWalkDistance1000Steps() {
        RandomWalk rw = new RandomWalk();
        System.out.println("Testing 1000 steps random walk: ");
        double res = 0.0;
        for (int i = 0; i < 1000; i++) {
        	rw.randomWalk(1000);
        	res += rw.distance();
        	rw.setX(0);
        	rw.setY(0);
        }
        res = res/1000;
        System.out.println("The average distance after 1000 steps is: " + res);
    }
    
    /**
     */
    @Test
    public void testRandomWalkDistance10000Steps() {
        RandomWalk rw = new RandomWalk();
        System.out.println("Testing 10000 steps random walk: ");
        double res = 0.0;
        for (int i = 0; i < 1000; i++) {
        	rw.randomWalk(10000);
        	res += rw.distance();
        	rw.setX(0);
        	rw.setY(0);
        }
        res = res/1000;
        System.out.println("The average distance after 10000 steps is: " + res);
    }
}