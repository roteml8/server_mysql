package main.recommendation;

import java.util.ArrayList;
import java.util.List;

import main.dao.rdb.StoreRepository;
import main.data.*;
import main.data.MerchantProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;

@Service
public class MerchantProfileService {
	private StoreProductRepository storeProductRepository;
	private BuyerOrderRepository orderRepository;
	private StoreRepository storeRepository;

	@Autowired
	public MerchantProfileService(StoreProductRepository storeProductRepository, BuyerOrderRepository orderRepository,
								  StoreRepository storeRepository) {
		this.storeProductRepository = storeProductRepository;
		this.orderRepository = orderRepository;
		this.storeRepository = storeRepository;
	}

	public MerchantProfile toMerchantProfile(Merchant merchant){
	    List<Store> stores = storeRepository.findByMerchant(merchant);
	    Store store = null;
	    if(!stores.isEmpty()){
	        store = stores.get(0);
		}
		List<StoreProduct> allStoreProducts = this.storeProductRepository.findByStore(store);
		int priceCounter = 0, priceSum = 0;
		List<ProductCategory> categories = new ArrayList<>();
		for (StoreProduct p: allStoreProducts)
		{
			priceSum+= p.getPrice();
			priceCounter++;
			if (!categories.contains(p.getCategory()))
				categories.add(p.getCategory());
		}
		double avgPrice = priceSum/priceCounter;

		ArrayList<BuyerOrder> allStoreOrders = this.orderRepository.findByStore(store);
		int ageCounter = 0, ageSum = 0;
		for (BuyerOrder o: allStoreOrders)
		{
			ageSum += o.getBuyerAge();
			ageCounter++;
		}
		double avgBuyerAge = ageSum/ageCounter;

		return new MerchantProfile(merchant.getMerchantId(), avgPrice, avgBuyerAge, categories, allStoreProducts,
				store);
	}
}
