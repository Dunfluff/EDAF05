package spanning_USA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Spanning_USA {
	static HashMap<String, LinkedList<City>> cities = new HashMap<String, LinkedList<City>>();

	public static void readFile(String filename) {
		File file = new File(filename);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scan.hasNext()) {
			String line = scan.nextLine();
			if (!line.contains("--")) {
				cities.put(line.trim(), new LinkedList<City>());
//				System.out.println("test");

			} else {
				String[] newLines = line.split("--| \\[");
				String firstCity = newLines[0];
				String secondCity = newLines[1];
				int distance = Integer.parseInt(newLines[2].substring(0, newLines[2].length() - 1));
//				System.out.println(firstCity+secondCity+distance);
				cities.get(firstCity).add(new City(firstCity, secondCity, distance));
				cities.get(secondCity).add(new City(secondCity, firstCity, distance));
			}
		}
		scan.close();
	}

	public static int prim() {
		int totalDistance = 0;
		HashSet<String> visited = new HashSet<String>();
		String initialCity = cities.keySet().iterator().next();
		visited.add(initialCity);
		PriorityQueue<City> pcity = new PriorityQueue<City>();
		pcity.addAll(cities.get(initialCity));
		int nbrCities = 0;
		while (!pcity.isEmpty()) {
			City e = pcity.remove();
			if (!visited.contains(e.target)) {
				totalDistance += e.distance;
//				System.out.println(e.toString());
				visited.add(e.target);
				pcity.addAll(cities.get(e.target));
			}
		}
		return totalDistance;
	}

	public static void main(String[] args) {

		readFile("USA-highway-miles.txt");
		long start = System.currentTimeMillis();
		System.out.println(prim());
		System.out.println(System.currentTimeMillis() - start);
	}
}
