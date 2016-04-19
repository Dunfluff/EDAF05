package spanning_USA;

public class City implements Comparable<City>{
	public String target;
	public String source;
	public int distance;
	
	public City(String source, String target, int distance) {
		this.distance = distance;
		this.source = source;
		this.target = target;
	}
	@Override
	public int compareTo(City c) {
		return distance - c.distance;
	}
	

	@Override
	public String toString() {
		return source + "-" + target + "-" + distance;
	}
}
