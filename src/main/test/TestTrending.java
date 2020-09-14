package main.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import main.Application;
import main.dao.rdb.MerchantRepository;
import main.dao.rdb.TrendRepository;
import main.data.Merchant;
import main.data.Trend;
import main.infra.MerchantService;
import main.infra.OrderService;
import main.infra.StoreService;
import main.recommendation.Recommendation;
import main.recommendation.RecommendationsService;
import main.recommendation.TrendForecast;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=default" })
public class TestTrending {
	
	
	@Autowired
	private MerchantService merchants;
	@Autowired
	private OrderService orders;
	@Autowired
	private StoreService stores;
	@Autowired
	private TrendForecast tf;
	@Autowired
	private TrendRepository trends;
	
	@Test
	public void testTredingProducts()
	{
		Long merchantId1 = merchants.addNewMerchant("rotem");
		Long merchantId2 = merchants.addNewMerchant("yaron");
		Long storeId1 = stores.addNewStore(merchantId1, "rotemsstore", "amazon");
		Long storeId2 = stores.addNewStore(merchantId2, "yaronsstore", "amazon");
		Long productId1 = merchants.addToStore(storeId1, "ring", "jewelry", 
				5, 7);
		Long productId2 = merchants.addToStore(storeId2, "necklace", "jewelry", 
				5, 7);
		Long productId3 = merchants.addToStore(storeId2, "earring", "jewelry", 
				5, 7);
		LocalDate today = LocalDate.now();
		Long orderId1 = orders.addNewOrderLocal(productId1, 50, today.minusDays(21),20);
		Long orderId2 = orders.addNewOrderLocal(productId1, 100, today.minusDays(14),20);
		Long orderId3 = orders.addNewOrderLocal(productId1, 200, today.minusDays(7),20);
		
		Long orderId4 = orders.addNewOrderLocal(productId2, 50, today.minusDays(20),20);
		Long orderId5 = orders.addNewOrderLocal(productId2, 100, today.minusDays(13),20);
		Long orderId6 = orders.addNewOrderLocal(productId2, 200, today.minusDays(6),20);

		Long orderId7 = orders.addNewOrderLocal(productId3, 15, today.minusDays(20),20);
		Long orderId8 = orders.addNewOrderLocal(productId3, 20, today.minusDays(13),20);
		Long orderId9 = orders.addNewOrderLocal(productId3, 40, today.minusDays(6),20);
		
		tf.setTrendingProducts();
		List<Trend> theTrends = (List<Trend>) trends.findAll();
		System.out.println("Trending Products are:");
		for (Trend t: theTrends)
		{
			System.out.println(t.getProductName());
		}
	}

}
