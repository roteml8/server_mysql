package main.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.MerchantRepository;
import main.dao.rdb.PlatformRepository;
import main.dao.rdb.TrendRepository;
import main.data.BuyerOrder;
import main.data.Merchant;
import main.data.Platform;
import main.data.Trend;
import main.infra.MerchantService;
import main.infra.OrderService;
import main.infra.StoreService;
import main.recommendation.Recommendation;
import main.recommendation.RecommendationsService;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleTestRecommendation {
	
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
	@Autowired
	private TrendRepository trends;
	@Autowired
	private BuyerOrderRepository orderRepository;
	@Autowired
	private PlatformRepository platforms;
	
	
	@Test
	public void testRecommendations()
	{
		Long merchantId1 = merchants.addNewMerchant("rotem");
		Long merchantId2 = merchants.addNewMerchant("yaron");
		Long storeId1 = stores.addNewStore(merchantId1, "rotemsstore", "ebay");
		Long storeId2 = stores.addNewStore(merchantId2, "yaronsstore", "amazon");
		Long productId1 = merchants.addToStore(storeId1, "ring", "jewelry", 
				5, 7);
		Long productId2 = merchants.addToStore(storeId2, "necklace", "jewelry", 
				5, 7);
		LocalDate today = LocalDate.now();
		Long orderId1 = orders.addNewOrderLocal(productId1, 50, today.minusDays(21),20);
		Long orderId2 = orders.addNewOrderLocal(productId1, 100, today.minusDays(14),20);
		Long orderId3 = orders.addNewOrderLocal(productId1, 200, today.minusDays(7),20);
		
		Long orderId4 = orders.addNewOrderLocal(productId2, 50, today.minusDays(21),20);

//		BuyerOrder o11 = new BuyerOrder();
//		o11.setBuyerAge(20);
//		o11.set
//		orderRepository.save(arg0)
//		Trend t1 = new Trend();
//		t1.setForecastDate(LocalDate.now());
//		Merchant m1 = merchantRepository.findById(merchantId1).get();
//		t1.setMerchant(m1);
//		Platform p1 = platforms.findById("ebay").get();
//		t1.setPlatform(p1);
//		t1.setProductName("ring");
//		trends.save(t1);
//		List<Merchant> allMerchants = merchantRepository.findAll();
//		for (Merchant m: allMerchants)
//		{
//			List<Recommendation> recommendations = this.recommendation.recommend(m);
//			if (recommendations.isEmpty())
//				System.out.println("no recommendations");
//			for (Recommendation r: recommendations)
//				System.out.println(r.toString());
//		}
		Merchant m2 = merchantRepository.findById(merchantId2).get();
		List<Recommendation> recommendations = this.recommendation.recommend(m2);
		if (recommendations.isEmpty())
			System.out.println("no recommendations");
		for (Recommendation r: recommendations)
			System.out.println(r.toString());
		
	}

}
