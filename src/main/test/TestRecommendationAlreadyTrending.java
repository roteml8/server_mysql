package main.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import main.Application;
import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.MerchantRepository;
import main.dao.rdb.PlatformRepository;
import main.dao.rdb.TrendRepository;
import main.data.Merchant;
import main.infra.MerchantService;
import main.infra.OrderService;
import main.infra.StoreService;
import main.recommendation.Recommendation;
import main.recommendation.RecommendationsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRecommendationAlreadyTrending {
	
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
		Long productId2 = merchants.addToStore(storeId2, "ring", "jewelry", 
				5, 7);
		LocalDate today = LocalDate.now();
		Long orderId1 = orders.addNewOrderLocal(productId1, 50, today.minusDays(21),20);
		Long orderId2 = orders.addNewOrderLocal(productId1, 100, today.minusDays(14),20);
		Long orderId3 = orders.addNewOrderLocal(productId1, 200, today.minusDays(7),20);
		
		Long orderId4 = orders.addNewOrderLocal(productId2, 50, today.minusDays(21),20);
		Long orderId5 = orders.addNewOrderLocal(productId2, 100, today.minusDays(14),20);
		Long orderId6 = orders.addNewOrderLocal(productId2, 200, today.minusDays(7),20);
		
		Merchant m2 = merchantRepository.findById(merchantId2).get();
		List<Recommendation> recommendations = this.recommendation.recommend(m2);
		assertThat(recommendations).isEmpty();
	}

}
