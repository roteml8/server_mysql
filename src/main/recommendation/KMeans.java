package main.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class KMeans {
	
	private static final Random random = new Random();
	 
    public static Map<Centroid, List<MerchantProfile>> fit(List<MerchantProfile> merchants, 
      int k, 
      Distance distance, 
      int maxIterations) { 
        
    	List<Centroid> centroids = randomCentroids(merchants, k);
        Map<Centroid, List<MerchantProfile>> clusters = new HashMap<>();
        Map<Centroid, List<MerchantProfile>> lastState = new HashMap<>();
     
        // iterate for a pre-defined number of times
        for (int i = 0; i < maxIterations; i++) {
            boolean isLastIteration = i == maxIterations - 1;
     
            for (MerchantProfile merchant : merchants) {
                Centroid centroid = nearestCentroid(merchant, centroids, distance);
                assignToCluster(clusters, merchant, centroid);
            }
     
            // if the assignments do not change, then the algorithm terminates
            boolean shouldTerminate = isLastIteration || clusters.equals(lastState);
            lastState = clusters;
            if (shouldTerminate) { 
                break; 
            }
     
            // at the end of each iteration we should relocate the centroids
            centroids = relocateCentroids(clusters);
            clusters = new HashMap<>();
        }     
        return lastState;
    }
    
    private static List<Centroid> randomCentroids(List<MerchantProfile> merchants, int k) {
        List<Centroid> centroids = new ArrayList<>();
        Map<String, Double> maxs = new HashMap<>();
        Map<String, Double> mins = new HashMap<>();
     
        for (MerchantProfile merchant : merchants) {
        	merchant.getFeatures().forEach((key, value) -> {
                // compares the value with the current max and choose the bigger value between them
                maxs.compute(key, (k1, max) -> max == null || value > max ? value : max);
     
                // compare the value with the current min and choose the smaller value between them
                mins.compute(key, (k1, min) -> min == null || value < min ? value : min);
            });
        }
     
        Set<String> attributes = merchants.stream()
          .flatMap(e -> e.getFeatures().keySet().stream())
          .collect(Collectors.toSet());
        for (int i = 0; i < k; i++) {
            Map<String, Double> coordinates = new HashMap<>();
            for (String attribute : attributes) {
                double max = maxs.get(attribute);
                double min = mins.get(attribute);
                coordinates.put(attribute, random.nextDouble() * (max - min) + min);
            }
     
            centroids.add(new Centroid(coordinates));
        }
     
        return centroids;
    }
    
    private static Centroid nearestCentroid(MerchantProfile merchant, List<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;
     
        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(merchant.getFeatures(), centroid.getCoordinates());
     
            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }
     
        return nearest;
    }
    
    private static void assignToCluster(Map<Centroid, List<MerchantProfile>> clusters,  
    		MerchantProfile merchant, 
    		  Centroid centroid) {
    		    clusters.compute(centroid, (key, list) -> {
    		        if (list == null) {
    		            list = new ArrayList<>();
    		        }
    		 
    		        list.add(merchant);
    		        return list;
    		    });
    		}
    
    private static Centroid average(Centroid centroid, List<MerchantProfile> merchants) {
        if (merchants == null || merchants.isEmpty()) { 
            return centroid;
        }
     
        Map<String, Double> average = centroid.getCoordinates();
        merchants.stream().flatMap(e -> e.getFeatures().keySet().stream())
          .forEach(k -> average.put(k, 0.0));
             
        for (MerchantProfile merchant : merchants) {
        	merchant.getFeatures().forEach(
              (k, v) -> average.compute(k, (k1, currentValue) -> v + currentValue)
            );
        }
     
        average.forEach((k, v) -> average.put(k, v / merchants.size()));
     
        return new Centroid(average);
    }
    
    private static List<Centroid> relocateCentroids(Map<Centroid, List<MerchantProfile>> clusters) {
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

}
