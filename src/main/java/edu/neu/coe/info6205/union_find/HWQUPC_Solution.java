package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;



public class HWQUPC_Solution {
		
	public static void main(String[] args) {
		System.out.print("Please input the number of sites: ");
		Scanner sc = new Scanner(System.in);		
		int n = sc.nextInt();	
		System.out.println("The result of n*log(v)/2 is: " + (int)n/2*Math.log(n));
		System.out.println("The average number of pair generated to combine " + n + " islated sites is " +count(n)/1000);
	}
	
	public static int count(int n) {
		Random rand = new Random();
		int res = 0;
		
		for (int i = 0; i < 1000; i++) {		
			WQUPC uf = new WQUPC(n);
			int no = 0;		
			while (uf.count() != 1) {
				int l = rand.nextInt(n);
				int r = rand.nextInt(n);
				if (!uf.connected(l,r)) {
					uf.union(l, r);
				}
				no++;
			}
			res += no;
		}
		return res;
	}
}
