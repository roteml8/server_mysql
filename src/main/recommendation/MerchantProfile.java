package main.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;
import main.data.BuyerOrder;
import main.data.Store;
import main.data.StoreProduct;

public class MerchantProfile {
	
	@Autowired
	private StoreProductRepository storeProductRepository;
	@Autowired
	private BuyerOrderRepository orderRepository;
	private Long merchantId;
	private double avgPrice; 
	private double avgBuyerAge;
	private final Map<String, Double> features;
	private String description;
	
	public MerchantProfile(Store store, Long merchantId)
	{
		this.setMerchantId(merchantId);
		int priceCounter = 0, priceSum = 0;
		ArrayList<StoreProduct> allStoreProducts = this.storeProductRepository.findByStore(store);
		for (StoreProduct p: allStoreProducts)
		{
			priceSum+= p.getPrice();
			priceCounter++;
		}
		this.avgPrice = priceSum/priceCounter;
		ArrayList<BuyerOrder> allStoreOrders = this.orderRepository.findByStore(store);
		int ageCounter = 0, ageSum = 0;
		for (BuyerOrder o: allStoreOrders)
		{
			ageSum += o.getBuyerAge();
			ageCounter++;
		}
		this.avgBuyerAge = ageSum/ageCounter;
		this.features = new HashMap<>();
		this.features.put("AveragePrice", this.avgPrice);
		this.features.put("AverageBuyerAge", this.avgBuyerAge);
		this.description = "MerchantID: "+this.merchantId+" Average Product Price: "+this.avgPrice+" Average Buyer Age: "+this.avgBuyerAge;

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
	
}
