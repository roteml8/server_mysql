package main.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import main.dao.rdb.MerchantRepository;
import main.data.Merchant;
import main.infra.MerchantService;
import main.infra.OrderService;
import main.infra.StoreService;
import main.recommendation.Recommendation;
import main.recommendation.RecommendationsService;

public class RecommendationTest {
	
	private String pathToCsv = "merchants.csv";
	private int numberOfMerchants = 10;
	private int numberOfProductsToStore = 5;
	private int numberOfOrdersToProduct = 3;
	
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
	
	public void readDataFromFile()
	{
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(new File(this.pathToCsv)));
			String row = "";
			for (int i=1; i<= numberOfMerchants; i++)
				{
					row = csvReader.readLine();
					String name = row;
					Long merchantId = merchants.addNewMerchant(name);
					row = csvReader.readLine();
					String[] storeData = row.split(",");
					Long storeId = stores.addNewStore(merchantId, storeData[0], storeData[1]);
					for (int k=1; k<=numberOfProductsToStore; k++)
					{
						row = csvReader.readLine();
						String[] productData = row.split(",");
						Long productId = merchants.addToStore(storeId, productData[0], productData[1], 
								Integer.parseInt(productData[2]), Integer.parseInt(productData[3]));
						LocalDate today = LocalDate.now();

						for (int j=1; j<=numberOfOrdersToProduct; j++)
						{

//							Long orderId = orders.addNewOrder(productId, Integer.parseInt(orderData[0]),
//									Integer.parseInt(orderData[1]), Integer.parseInt(orderData[2]), Integer.parseInt(orderData[3]), 
//									Integer.parseInt(orderData[4]));
							row = csvReader.readLine();
							String[] orderData = row.split(",");
							Long orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(orderData[0]), today.minusDays(21),Integer.parseInt(orderData[1]));
							Long orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(orderData[0]), today.minusDays(14),Integer.parseInt(orderData[1]));
							Long orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(orderData[0]), today.minusDays(7),Integer.parseInt(orderData[1]));
						}
				}
			    
			}		
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testRecommendations()
	{
		readDataFromFile();
		List<Merchant> allMerchants = merchantRepository.findAll();
		for (Merchant m: allMerchants)
		{
			List<Recommendation> recommendations = this.recommendation.recommend(m);
			for (Recommendation r: recommendations)
				System.out.println(r.toString());
		}
		
		
	}

	
	
}
	

