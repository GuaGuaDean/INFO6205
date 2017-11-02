package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        int size = to - from;
        if (size < cutoff) Arrays.sort(array, from, to);
        else {
            CompletableFuture<int[]> parsort1 = parsort(array, from, (from+to)/2); // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, (from+to)/2, to); // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.
                    thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        // TODO implement me
                        int i = 0;
                		int j = 0;
                		int k = 0;
                		while (k < result.length) {
                			if (i >= xs1.length && j < xs2.length) {
                				result[k++] = xs2[j++];
                			} else if (j >= xs2.length && i <= xs1.length) {
                		    	result[k++] = xs1[i++];
                		    } else if (xs1[i] < xs2[j]) {
                		    	result[k++] = xs1[i++];
                		    } else {
                		    	result[k++] = xs2[j++];
                		    }
                		}
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
            	for (int i = 0; i < result.length; i++) {
            		array[from+i] = result[i];
            	}
            }); // TODO implement me
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    for (int i = from; i < to; i++) {
                    	result[i-from] = array[i];
                    }
                    sort(result, 0, result.length);    
                    return result;
                }
        );
    }
}
