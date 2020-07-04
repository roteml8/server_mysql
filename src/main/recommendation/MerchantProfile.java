package main.recommendation;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;
import main.data.BuyerOrder;
import main.data.ProductCategory;
import main.data.Store;
import main.data.StoreProduct;

import javax.annotation.PostConstruct;

public class MerchantProfile {
	
	@Autowired
	private StoreProductRepository storeProductRepository;
	@Autowired
	private BuyerOrderRepository orderRepository;
	
	private Long merchantId;
	private double avgPrice; 
	private double avgBuyerAge;
	private List<ProductCategory> merchantCategories;
	private final Map<String, Double> features;
	private String description;
	private List<StoreProduct> allStoreProducts;
	private Store store;


	public MerchantProfile(Store store, Long merchantId)
	{
		this.setMerchantId(merchantId);
		setStore(store);
		this.features = new HashMap<>();
		this.merchantCategories = new ArrayList<>();
	}

	@PostConstruct
	public void init(){
		int priceCounter = 0, priceSum = 0;
		allStoreProducts = this.storeProductRepository.findByStore(store);
		// collect store products categories and calculate average price of product
		for (StoreProduct p: allStoreProducts)
		{
			priceSum+= p.getPrice();
			priceCounter++;
			if (!this.merchantCategories.contains(p.getCategory()))
				this.merchantCategories.add(p.getCategory());
		}
		this.avgPrice = priceSum/priceCounter;

		// calculate average age of store buyer
		ArrayList<BuyerOrder> allStoreOrders = this.orderRepository.findByStore(store);
		int ageCounter = 0, ageSum = 0;
		for (BuyerOrder o: allStoreOrders)
		{
			ageSum += o.getBuyerAge();
			ageCounter++;
		}
		this.avgBuyerAge = ageSum/ageCounter;
		// numerical features
		this.features.put("AveragePrice", this.avgPrice);
		this.features.put("AverageBuyerAge", this.avgBuyerAge);
		this.description = "MerchantID: "+this.merchantId+" Average Product Price: "+this.avgPrice+" Average Buyer Age: "+this.avgBuyerAge+
				"Merchant Products Categories: "+this.merchantCategories;
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
