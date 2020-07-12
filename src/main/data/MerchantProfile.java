package main.data;

import java.util.*;

public class MerchantProfile {
	private Long merchantId;
	private double avgPrice; 
	private double avgBuyerAge;
	private List<ProductCategory> merchantCategories;
	private final Map<String, Double> features;
	private String description;
	private List<StoreProduct> allStoreProducts;
	private Store store;

	public MerchantProfile() {
		this.features = new HashMap<>();
		this.merchantCategories = new ArrayList<>();
	}

	public MerchantProfile(Store store, Long merchantId) {
		this();
		setMerchantId(merchantId);
		setStore(store);
	}

	public MerchantProfile(Long merchantId, double avgPrice, double avgBuyerAge,
						   List<ProductCategory> merchantCategories, List<StoreProduct> allStoreProducts, Store store) {
		this(store, merchantId);
		this.avgPrice = avgPrice;
		this.avgBuyerAge = avgBuyerAge;
		this.merchantCategories = merchantCategories;
		this.allStoreProducts = allStoreProducts;
		features.put("AveragePrice", avgPrice);
		features.put("AverageBuyerAge", avgBuyerAge);
		this.description = "MerchantID: "+this.merchantId+" Average Product Price: "+this.avgPrice
				+" Average Buyer Age: "+this.avgBuyerAge+ "Merchant Products Categories: "+this.merchantCategories;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public double getAvgBuyerAge() {
		return avgBuyerAge;
	}

	public void setAvgBuyerAge(double avgBuyerAge) {
		this.avgBuyerAge = avgBuyerAge;
	}

	public Map<String, Double> getFeatures() {
		return features;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MerchantProfile that = (MerchantProfile) o;
		return Objects.equals(merchantId, that.merchantId);
	}


	@Override
	public int hashCode() {
		return Objects.hash(merchantId);
	}

	public List<StoreProduct> getAllStoreProducts() {
		return allStoreProducts;
	}

	public List<ProductCategory> getMerchantCategories() {
		return merchantCategories;
	}

	public void setMerchantCategories(List<ProductCategory> merchantCategories) {
		this.merchantCategories = merchantCategories;
	}
	
	
}
