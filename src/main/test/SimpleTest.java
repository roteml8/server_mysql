package main.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import main.dao.rdb.MerchantRepository;
import main.data.Merchant;
import main.infra.MerchantService;
import main.infra.OrderService;
import main.infra.StoreService;
import main.recommendation.Recommendation;
import main.recommendation.RecommendationsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=default" })
public class SimpleTest {
	
	@Autowired
	private MerchantService merchants;
	@Autowired
	private OrderService orders;
	@Autowired
	private StoreService stores;
	@Autowired
	private RecommendationsService recommendation;
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Test
	public void testRecommendations()
	{
		Long merchantId = merchants.addNewMerchant("rotem");
		Long storeId = stores.addNewStore(merchantId, "rotemsstore", "ebay");
		Long productId = merchants.addToStore(storeId, "ring", "jewelry", 
				5, 7);
		Long orderId = orders.addNewOrder(productId, 4,
						11, 2019, 2, 
						20);
		List<Merchant> allMerchants = merchantRepository.findAll();
		for (Merchant m: allMerchants)
		{
			List<Recommendation> recommendations = this.recommendation.recommend(m);
			for (Recommendation r: recommendations)
				System.out.println(r.toString());
		}
		
		
	}

}
