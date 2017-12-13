import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ChromosomeTest {

	@Test
	public void testConstructor() {
		String[] places = {"US", "UK", "CN", "IN", "RS"};
		Chromosome cs1 = new Chromosome(5, 0.1, 0.5, places, null);
		//for (String place: cs1.nodes) System.out.println(place);
		assertTrue(places.length == cs1.nodes.length);
	}

	@Test
	public void testMutate() {
		String[] places = {"US", "UK", "CN", "IN", "RS"};
		Chromosome cs2 = new Chromosome(5, 1.0, 0.5, places, null);
		places = new String[5];
		for (int i = 0; i < 5; i++) { places[i] = cs2.nodes[i]; }
		cs2.mutate();
		for (String place: places) System.out.print(place + " ");
		System.out.println();
		for (String place: cs2.nodes) System.out.print(place + " ");
		System.out.println();
		String[] firstReverse = {"",""};
		String[] secondReverse = {"", ""};
		for (int i = 0; i < places.length; i++) {
			if (places[i].equals(cs2.nodes[i])) continue;
			else {
				firstReverse[0] = places[i];
				firstReverse[1] = cs2.nodes[i];
				break;
			}
		}
		for (int i = places.length-1; i >= 0; i--) {
			if (places[i].equals(cs2.nodes[i])) continue;
			else {
				secondReverse[0] = places[i];
				secondReverse[1] = cs2.nodes[i];
				break;
			}
		}
		assertTrue(firstReverse[0].equals(secondReverse[1]));
		assertTrue(secondReverse[1].equals(firstReverse[0]));
	}

	@Test
	public void testCrossover() {
		String[] places = {"US", "UK", "CN", "IN", "RS"};
		
		Chromosome cs1 = new Chromosome(5, 0.1, 1.0, places, null);
		Chromosome cs2 = new Chromosome(5, 0.1, 1.0, places, null);
		
		for (int i = 0; i < places.length; i++) {
			cs1.nodes[i] = places[i];
			cs2.nodes[i] = places[4-i];
		}
		
		int pos = cs1.testCrossOver(cs2);
		
		//for (String place: cs1.nodes) System.out.print(place + " ");
		//System.out.println();
		//for (String place: cs2.nodes) System.out.print(place + " ");
		//System.out.println();
		
		//Set<String> set = new HashSet<>();
		for (int i = 0; i < pos; i++) {
		    assertEquals(cs1.nodes[i], places[i]);
		}
		
		for (int i = pos; i < places.length; i++) {
			assertEquals(cs2.nodes[i], places[i]);
		}
		

	}

	@Test
	public void testSortDistance() {
		String[] input = {"US", "UK", "CN", "IN", "RS"};
		int[][] dist = {{0, 1, 1000, 1000, 1000},
						{1000, 0, 1, 1000, 1000},
						{1000, 1000, 0, 1, 1000},
						{1000, 1000, 1000, 0, 1},
						{1, 1000, 1000, 1000, 0}
		};
		
		List<Chromosome> list = new ArrayList<>();
		// create 40 chromosome
		for (int i = 0; i < 5; i++) {
			list.add(new Chromosome(5, 0.05, 1.0, input, dist));
			//System.out.println(list.get(i).calcDistance());
		}
		
		Collections.sort(list, new Comparator<Chromosome>(){
			@Override
			public int compare(Chromosome o1, Chromosome o2) {
			return o1.calcDistance() - o2.calcDistance();
			}	
		});
		
//		for (int i = 0; i < 5; i++) {
//			System.out.println(list.get(i).calcDistance());
//		}
		
		for (int i = 1; i < list.size(); i++) {
			assertTrue(list.get(i-1).calcDistance() <= list.get(i).calcDistance());
		}
	}
	
	@Test
	public void testEncode() {
		String[] places = {"US", "UK", "CN", "IN", "RS"};
		Chromosome cs2 = new Chromosome(5, 1.0, 0.5, places, null);
		cs2.nodes = places;
		String[] bits = cs2.encodeChromo();
		assertTrue(bits[0].equals("000"));
		assertTrue(bits[1].equals("001"));
		assertTrue(bits[2].equals("010"));
		assertTrue(bits[3].equals("011"));
		assertTrue(bits[4].equals("100"));
	}
	
	@Test
	public void testDecode() {
		String[] places = {"US", "UK", "CN", "IN", "RS"};
		Chromosome cs2 = new Chromosome(5, 1.0, 0.5, places, null);
		cs2.nodes = places;
		String[] bits = cs2.encodeChromo();
		String[] placesFromDecode = cs2.decodeChromo(bits);
		assert(placesFromDecode[0].equals(places[0]));
		assert(placesFromDecode[1].equals(places[1]));
		assert(placesFromDecode[2].equals(places[2]));
		assert(placesFromDecode[3].equals(places[3]));
		assert(placesFromDecode[4].equals(places[4]));
		assert(placesFromDecode[0].equals(places[0]));
	}
}
