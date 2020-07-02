package main.recommendation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;
import main.data.Merchant;
import main.data.Store;

@Service

public class MerchantProfileService {
	
	@Autowired
	private StoreProductRepository storeProductRepository;
	@Autowired
	private BuyerOrderRepository orderRepository;
	
	//public Map<String, Double> generateFeatures(Merchant m, Store s)
	{
		
	}

}
