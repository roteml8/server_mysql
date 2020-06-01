package main.recommendation;

import java.util.Map;

public class Centroid {
	
	private final Map<String, Double> coordinates;

	public Centroid(Map<String, Double> coordinates) {
		super();
		this.coordinates = coordinates;
	}

	public Map<String, Double> getCoordinates() {
		return coordinates;
	}
	
	

}
