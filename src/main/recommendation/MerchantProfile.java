package main.recommendation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;
import main.dao.rdb.StoreRepository;
import main.data.BuyerOrder;
import main.data.Merchant;
import main.data.ProductCategory;
import main.data.Store;
import main.data.StoreProduct;

public class MerchantProfile {
	
	@Autowired
	private StoreProductRepository storeProductRepository;
	@Autowired
	private BuyerOrderRepository orderRepository;
	
	private double avgPrice; 
	private double avgBuyerAge;
	
	public MerchantProfile(Store store)
	{
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

		
		
	}
	

}
