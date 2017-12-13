import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

	public class Chromosome {
		// The chromo
		String[] nodes = null;
		Map<String, Integer> placeToIndex;
		Map<Integer, String> indexToPlace;
		static int[][] distances = null;
		static Random rand = new Random();
		StringBuilder chromo; 
		public StringBuffer decodeChromo;
		public double score;
		public int total;
		public double crossRate;
		public double mutRate;
		
		// Constructor that generates a random
		public Chromosome(int chromoLen, double mutRate, double crossRate, String[] nodeInput, int[][] distInput) {
			nodes = new String[nodeInput.length];
			placeToIndex = new HashMap<>();
			indexToPlace = new HashMap<>();
			for (int i = 0; i < nodeInput.length; i++) {
				nodes[i] = new String(nodeInput[i]);
				placeToIndex.put(nodes[i], i);
				indexToPlace.put(i, nodes[i]);
			}
			distances = distInput;
			chromo = new StringBuilder(chromoLen * 4);
			decodeChromo = new StringBuffer(chromoLen * 4);
			this.mutRate = mutRate;
			this.crossRate = crossRate;
			
			// Shuffle the chromo string
			for(int y = nodes.length; y > 0; y--) {
				int index = rand.nextInt(y);
				String tmp = nodes[index];
				nodes[index] = nodes[y-1];
				nodes[y-1] = tmp;
			}
			
		}

		public Chromosome(Chromosome copy) {
			nodes = new String[copy.nodes.length];
			placeToIndex = copy.placeToIndex;
			for (int i = 0; i < copy.nodes.length; i++) {
				nodes[i] = copy.nodes[i];
			}
			distances = copy.distances;
			this.mutRate = copy.mutRate;
			this.crossRate = copy.crossRate;
		}

		// convert place to binary string based on its
		public final String[] encodeChromo() {
			String[] bits = new String[nodes.length];
			for (int i = 0; i < nodes.length; i++) {
				
				// find position in total order
				int index = placeToIndex.get(nodes[i]);
				String toBits = Integer.toBinaryString(index);
				
				// add leading zeros to make a fixed-length binary string
				int len = Integer.toBinaryString(nodes.length).length();
				int leadingZeros = len - Integer.toBinaryString(index).length();
				for (int j = 0; j < leadingZeros; j++) {
					toBits = "0" + toBits;
				}
				bits[i] = toBits;
			}
			return bits;
		}
		
		// Decode the string
		public final String[] decodeChromo(String[] bits) {	
			String[] places = new String[bits.length];
			for (int i = 0; i < bits.length; i++) {
				int toInteger = Integer.parseInt(bits[i], 2);
				places[i] = String.valueOf(indexToPlace.get(toInteger));
			}
			return places;
		}
		
		// Crossover bits
		public final void crossover(Chromosome other) {
			int len = this.nodes.length;
			
			// Should we cross over?
			if (rand.nextDouble() > crossRate) return;
			
			// Generate a random position
			int pos = rand.nextInt(len);
			//System.out.println(pos);
			
			Set<String> secondHalf = new HashSet<>();
			for (int x = pos; x < len; x++) {
				secondHalf.add(this.nodes[x]);
			}
			
			String[] temp = new String[len];
			
			// copy 2nd half to temp
			int right = pos; int left = 0;
			for (int i = 0; i < len; i++) {
				if (secondHalf.contains(other.nodes[(i+pos)%len])) {
					temp[right++] = other.nodes[(i+pos)%len];
				} else {
					temp[left++] = other.nodes[(i+pos)%len];
				}
			}
			
			// write back to one's 2nd half
			for (int i = pos; i < len; i++) {
				other.nodes[i] = this.nodes[i];
				this.nodes[i] = temp[i];
			}
							
			for (int i = 0; i < pos; i++) {
				other.nodes[i] = temp[i];
			}
		}
		
		public final int testCrossOver(Chromosome other) {
			int len = this.nodes.length;
			
			// Should we cross over?
			if (rand.nextDouble() > crossRate) return -1;
			
			// Generate a random position
			int pos = rand.nextInt(len);
			//System.out.println(pos);
			
			Set<String> secondHalf = new HashSet<>();
			for (int x = pos; x < len; x++) {
				secondHalf.add(this.nodes[x]);
			}
			
			String[] temp = new String[len];
			
			// copy 2nd half to temp
			int right = pos; int left = 0;
			for (int i = 0; i < len; i++) {
				if (secondHalf.contains(other.nodes[(i+pos)%len])) {
					temp[right++] = other.nodes[(i+pos)%len];
				} else {
					temp[left++] = other.nodes[(i+pos)%len];
				}
			}
			
			// write back to one's 2nd half
			for (int i = pos; i < len; i++) {
				other.nodes[i] = this.nodes[i];
				this.nodes[i] = temp[i];
			}
							
			for (int i = 0; i < pos; i++) {
				other.nodes[i] = temp[i];
			}
			
			return pos;
		}
			
		// Mutation - swap two elements' position
		public final void mutate() {	
			// shall we mutate?
			if (rand.nextDouble() <= mutRate) {
				//System.out.println("We have a mutation");
				int swapsNo = rand.nextInt(nodes.length/2);
				for (int i = swapsNo; i > 0; i--) {
					int first = rand.nextInt(nodes.length);
					int second = rand.nextInt(nodes.length);
					String tmp = nodes[first];
					nodes[first] = nodes[second];
					nodes[second] = tmp;
				}
			}

		}
							
		// Add up the contents of the decoded chromo
		public final int calcDistance() { 
			int tot = 0;
			for (int i = 1; i < nodes.length; i++) {
				tot += distances[placeToIndex.get(nodes[i-1])][placeToIndex.get(nodes[i])];
			}
			return tot + distances[placeToIndex.get(nodes[nodes.length-1])][placeToIndex.get(nodes[0])];
		}

		public final boolean isValid() { 
		
			String[] bits = {"",""};
			
			// Decode our chromo
			String[] decodedStrings = decodeChromo(bits);
			
			boolean num = true;
			for (int x=0;x < decodedStrings.length;x++) {
				String ch = decodedStrings[x];

				// Did we follow the num-oper-num-oper-num patter
				//if (num == !Character.isDigit(ch)) return false;
				
				// Don't allow divide by zero
				//if (x>0 && ch=='0' && decodedString.charAt(x-1)=='/') return false;
				
				//num = !num;
			}
			
			// Can't end in an operator
			//if (!Character.isDigit(decodedString.charAt(decodedString.length()-1))) return false;
			
			return true;
		}
	}

