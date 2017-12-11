import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileReader;


public class Runner {
	
	final static Logger logger = Logger.getLogger("GALogger");
	
	public static void main (String[] args) {
		
		Random rand = new Random();
		// hard-coded data
//		String[] input = {"US", "UK", "CN", "IN", "RS"};
//		int[][] dist = {{0, 1, 1000, 1000, 1000},
//						{1000, 0, 1, 1000, 1000},
//						{1000, 1000, 0, 1, 1000},
//						{1000, 1000, 1000, 0, 1},
//						{1, 1000, 1000, 1000, 0}
//		};
		
		// data read from file
		
		int[][] dist = new int[48][48];
		BufferedReader br = null;
		String[][] TSP48 = new String[48][48];
		int index = 0;
		String[] input = new String[48];
		for (int i = 1; i <= 48; i++) {
			input[i-1] = String.valueOf(i);
		}
		
		try {
			br = new BufferedReader(new FileReader(new File("att48_d.txt")));
			String str = null;
			while ((str=br.readLine())!=null) {
				TSP48[index] = str.trim().split("( )+");
				for (int i = 0; i < TSP48[index].length; i++) {
					dist[index][i] = Integer.parseInt(TSP48[index][i]);
				}
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Chromosome> list = new ArrayList<>();
		// create 40 chromosome
		for (int i = 0; i < 40; i++) {
			list.add(new Chromosome(5, 0.15, 1.0, input, dist));
		}
		
		int gen = 0;
		int score = Integer.MAX_VALUE;
		int cur = score;
		int originalSize = list.size();
		
		while (true) {
		
			// mutate
			for (Chromosome ch: list) {
				ch.mutate();
			}
		
			//System.out.println("We finished the mutate");
			// crossover
			for (int i = 0; i < originalSize/2; i++) {
				Chromosome first = new Chromosome(list.get(i));
				Chromosome second = new Chromosome(list.get(i+originalSize/2));
				first.crossover(second);
				list.add(first);
				list.add(second);
			}
			//System.out.println("We finished the crossover");
			// sort
			Collections.sort(list, new Comparator<Chromosome>(){
				@Override
				public int compare(Chromosome o1, Chromosome o2) {
				return o1.calcDistance() - o2.calcDistance();
				}	
			});
			
			cur = list.get(0).calcDistance();
			if (cur < score || gen % 10000 == 0) {
				logger.info("The minimum score is: " + list.get(0).calcDistance());
				score = cur;
			}
		
			// eliminate
			for (int i = list.size(); i >= originalSize; i--) {
				list.remove(list.size()-1);
			}
			
			// shuffle
			for(int y = list.size()-1; y > 0; y--) {
				int p = rand.nextInt(y);
				Chromosome tmp = list.get(y);
				list.set(y,list.get(p));
				list.set(p, tmp);
			}
			gen++;
			if (gen % 1000 == 0) {
				logger.info("This is the " + gen + "th generation");
			}
		}
	
	}
}