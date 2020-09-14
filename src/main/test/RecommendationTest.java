package main.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import main.Application;
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
public class RecommendationTest {
	
	private String pathToCsv = "‪D:\\integrationcourse\\workspace\\serverrelationaldb\\merchants.csv";
	private int numberOfMerchants = 3;
	private int numberOfProductsToStore = 3;
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
	@Test
	public void testRecommendations()
	{
		//readDataFromFile();
		String[] m = {"rotem","rotem's store","ebay","heart ring","jewelry","50","100",
				"25","25","50","30","100","28","long necklace","jewelry","100","150","10","20","7","22","15","29",
				"silver earring", "jewelry","75","130","15","26","28","24","42","27"};
		

		Long merchantId = merchants.addNewMerchant(m[0]);
		Long storeId = stores.addNewStore(merchantId, m[1], m[2]);
			Long productId = merchants.addToStore(storeId, m[3], m[4], 
					Integer.parseInt(m[5]), Integer.parseInt(m[6]));
		LocalDate today = LocalDate.now();

		Long orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m[7]), today.minusDays(21),Integer.parseInt(m[8]));
		Long orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m[9]), today.minusDays(14),Integer.parseInt(m[10]));
		Long orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m[11]), today.minusDays(7),Integer.parseInt(m[12]));
		Long productId2 = merchants.addToStore(storeId, m[13], m[14], 
				Integer.parseInt(m[15]), Integer.parseInt(m[16]));	
		Long orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m[17]), today.minusDays(21),Integer.parseInt(m[18]));
		Long orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m[19]), today.minusDays(14),Integer.parseInt(m[20]));
		Long orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m[21]), today.minusDays(7),Integer.parseInt(m[22]));
		Long productId3 = merchants.addToStore(storeId, m[23], m[23], 
				Integer.parseInt(m[25]), Integer.parseInt(m[26]));	
		Long orderId7 = orders.addNewOrderLocal(productId3, Integer.parseInt(m[27]), today.minusDays(21),Integer.parseInt(m[28]));
		Long orderId8 = orders.addNewOrderLocal(productId3, Integer.parseInt(m[29]), today.minusDays(14),Integer.parseInt(m[30]));
		Long orderId9 = orders.addNewOrderLocal(productId3, Integer.parseInt(m[31]), today.minusDays(7),Integer.parseInt(m[32]));
	

		String[] m2 = {"yaron","yaron's jewelry","amazon","colorful ring","jewelry","25","50","25","25","50","38","100","28",
			"star necklace","jewelry","100","75","10","20","7","22","15","29",
			"purple bracelet","jewelry","100","100","15","26","28","24","42","27"};
		
		 merchantId = merchants.addNewMerchant(m2[0]);
		 storeId = stores.addNewStore(merchantId, m2[1], m2[2]);
			 productId = merchants.addToStore(storeId, m2[3], m2[4], 
					Integer.parseInt(m2[5]), Integer.parseInt(m2[6]));

		 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m2[7]), today.minusDays(21),Integer.parseInt(m2[8]));
		 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m2[9]), today.minusDays(14),Integer.parseInt(m2[10]));
		 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m2[11]), today.minusDays(7),Integer.parseInt(m2[12]));
		 productId2 = merchants.addToStore(storeId, m2[13], m2[14], 
				Integer.parseInt(m2[15]), Integer.parseInt(m2[16]));	
		 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m2[17]), today.minusDays(21),Integer.parseInt(m2[18]));
		 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m2[19]), today.minusDays(14),Integer.parseInt(m2[20]));
		 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m2[21]), today.minusDays(7),Integer.parseInt(m2[22]));
		 productId3 = merchants.addToStore(storeId, m2[23], m2[24], 
				Integer.parseInt(m2[25]), Integer.parseInt(m2[26]));	
		 orderId7 = orders.addNewOrderLocal(productId3, Integer.parseInt(m2[27]), today.minusDays(21),Integer.parseInt(m2[28]));
		 orderId8 = orders.addNewOrderLocal(productId3, Integer.parseInt(m2[29]), today.minusDays(14),Integer.parseInt(m2[30]));
		 orderId9 = orders.addNewOrderLocal(productId3, Integer.parseInt(m2[31]), today.minusDays(7),Integer.parseInt(m2[32]));
	
		String[] m3 = {"ohad","ohad's jewelry","ebay","circle necklace","jewelry","100","45","25","27","50","35","100","29",
					"star necklace","jewelry","100","70","10","23","7","21","15","28",
					"purple bracelet","jewelry","100","95","15","25","28","25","42","26"};
		
		
		 merchantId = merchants.addNewMerchant(m3[0]);
		 storeId = stores.addNewStore(merchantId, m3[1], m3[2]);
			 productId = merchants.addToStore(storeId, m3[3], m3[4], 
					Integer.parseInt(m3[5]), Integer.parseInt(m3[6]));

		 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m3[7]), today.minusDays(21),Integer.parseInt(m3[8]));
		 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m3[9]), today.minusDays(14),Integer.parseInt(m3[10]));
		 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m3[11]), today.minusDays(7),Integer.parseInt(m3[12]));
		 productId2 = merchants.addToStore(storeId, m3[13], m3[14], 
				Integer.parseInt(m3[15]), Integer.parseInt(m3[16]));	
		 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m3[17]), today.minusDays(21),Integer.parseInt(m3[18]));
		 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m3[19]), today.minusDays(14),Integer.parseInt(m3[20]));
		 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m3[21]), today.minusDays(7),Integer.parseInt(m3[22]));
		 productId3 = merchants.addToStore(storeId, m3[23], m3[24], 
				Integer.parseInt(m3[25]), Integer.parseInt(m3[26]));	
		 orderId7 = orders.addNewOrderLocal(productId3, Integer.parseInt(m3[27]), today.minusDays(21),Integer.parseInt(m3[28]));
		 orderId8 = orders.addNewOrderLocal(productId3, Integer.parseInt(m3[29]), today.minusDays(14),Integer.parseInt(m3[30]));
		 orderId9 = orders.addNewOrderLocal(productId3, Integer.parseInt(m3[31]), today.minusDays(7),Integer.parseInt(m3[32]));
		
		String[] m4 = {"matan","matan textile","ebay","mini black dress","fashion","100","50","25","30","50","35","100","37",
					"orange skirt","fashion","100","30","10","36","7","38","15","40",
					"pink tshirt","fashion","100","25","15","39","28","30","42","29"};
		

		 merchantId = merchants.addNewMerchant(m4[0]);
		 storeId = stores.addNewStore(merchantId, m4[1], m4[2]);
			 productId = merchants.addToStore(storeId, m4[3], m4[4], 
					Integer.parseInt(m4[5]), Integer.parseInt(m4[6]));

		 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m4[7]), today.minusDays(21),Integer.parseInt(m4[8]));
		 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m4[9]), today.minusDays(14),Integer.parseInt(m4[10]));
		 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m4[11]), today.minusDays(7),Integer.parseInt(m4[12]));
		 productId2 = merchants.addToStore(storeId, m4[13], m4[14], 
				Integer.parseInt(m4[15]), Integer.parseInt(m4[16]));	
		 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m4[17]), today.minusDays(21),Integer.parseInt(m4[18]));
		 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m4[19]), today.minusDays(14),Integer.parseInt(m4[20]));
		 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m4[21]), today.minusDays(7),Integer.parseInt(m4[22]));
		 productId3 = merchants.addToStore(storeId, m4[23], m4[24], 
				Integer.parseInt(m4[25]), Integer.parseInt(m4[26]));	
		 orderId7 = orders.addNewOrderLocal(productId3, Integer.parseInt(m4[27]), today.minusDays(21),Integer.parseInt(m4[28]));
		 orderId8 = orders.addNewOrderLocal(productId3, Integer.parseInt(m4[29]), today.minusDays(14),Integer.parseInt(m4[30]));
		 orderId9 = orders.addNewOrderLocal(productId3, Integer.parseInt(m4[31]), today.minusDays(7),Integer.parseInt(m4[32]));
		
		String[] m5 = {"miriam","miriams fashion house","amazon","mini black dress","fashion","100","55","25","28","50","31","100","30",
					"lavender face cream","cosmetics","100","33","10","27","20","32","30","34",
					"white tshirt","fashion","100","28","15","36","28","30","42","31"};
		
		 merchantId = merchants.addNewMerchant(m5[0]);
		 storeId = stores.addNewStore(merchantId, m5[1], m5[2]);
			 productId = merchants.addToStore(storeId, m5[3], m5[4], 
					Integer.parseInt(m5[5]), Integer.parseInt(m5[6]));

		 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m5[7]), today.minusDays(21),Integer.parseInt(m5[8]));
		 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m5[9]), today.minusDays(14),Integer.parseInt(m5[10]));
		 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m5[11]), today.minusDays(7),Integer.parseInt(m5[12]));
		 productId2 = merchants.addToStore(storeId, m5[13], m5[14], 
				Integer.parseInt(m5[15]), Integer.parseInt(m5[16]));	
		 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m5[17]), today.minusDays(21),Integer.parseInt(m5[18]));
		 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m5[19]), today.minusDays(14),Integer.parseInt(m5[20]));
		 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m5[21]), today.minusDays(7),Integer.parseInt(m5[22]));
		 productId3 = merchants.addToStore(storeId, m5[23], m5[24], 
				Integer.parseInt(m5[25]), Integer.parseInt(m5[26]));	
		 orderId7 = orders.addNewOrderLocal(productId3, Integer.parseInt(m5[27]), today.minusDays(21),Integer.parseInt(m5[28]));
		 orderId8 = orders.addNewOrderLocal(productId3, Integer.parseInt(m5[29]), today.minusDays(14),Integer.parseInt(m5[30]));
		 orderId9 = orders.addNewOrderLocal(productId3, Integer.parseInt(m5[31]), today.minusDays(7),Integer.parseInt(m5[32]));
			
			String[] m6 = {"adi","adis cosmetics","ebay","rose water","cosmetics","100","105","25","32","50","31","100","30",
						"face sunscreen","cosmetics","100","85","15","33","28","34","42","32"};
			
			merchantId = merchants.addNewMerchant(m6[0]);
			 storeId = stores.addNewStore(merchantId, m6[1], m6[2]);
				 productId = merchants.addToStore(storeId, m6[3], m6[4], 
						Integer.parseInt(m6[5]), Integer.parseInt(m6[6]));

			 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m6[7]), today.minusDays(21),Integer.parseInt(m6[8]));
			 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m6[9]), today.minusDays(14),Integer.parseInt(m6[10]));
			 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m6[11]), today.minusDays(7),Integer.parseInt(m6[12]));
			 productId2 = merchants.addToStore(storeId, m6[13], m6[14], 
					Integer.parseInt(m6[15]), Integer.parseInt(m6[16]));	
			 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m6[17]), today.minusDays(21),Integer.parseInt(m6[18]));
			 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m6[19]), today.minusDays(14),Integer.parseInt(m6[20]));
			 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m6[21]), today.minusDays(7),Integer.parseInt(m6[22]));

				String[] m7 = {"hadar","hadars cosmetics","ebay","hairspray","cosmetics","100","57","25","32","50","33","100","34",
						"face sunscreen","cosmetics","100","37","15","36","28","37","42","38"};
				
				merchantId = merchants.addNewMerchant(m7[0]);
				 storeId = stores.addNewStore(merchantId, m7[1], m7[2]);
					 productId = merchants.addToStore(storeId, m7[3], m7[4], 
							Integer.parseInt(m7[5]), Integer.parseInt(m7[6]));

				 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m7[7]), today.minusDays(21),Integer.parseInt(m7[8]));
				 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m7[9]), today.minusDays(14),Integer.parseInt(m7[10]));
				 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m7[11]), today.minusDays(7),Integer.parseInt(m7[12]));
				 productId2 = merchants.addToStore(storeId, m7[13], m7[14], 
						Integer.parseInt(m7[15]), Integer.parseInt(m7[16]));	
				 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m7[17]), today.minusDays(21),Integer.parseInt(m7[18]));
				 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m7[19]), today.minusDays(14),Integer.parseInt(m7[20]));
				 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m7[21]), today.minusDays(7),Integer.parseInt(m7[22]));
				
					String[] m8 = {"avi","toys and us","amazon","blue ball","toys","100","30","25","37","50","38","100","39",
							"green spinner","toys","100","20","15","37","28","38","42","39"};
					
					merchantId = merchants.addNewMerchant(m8[0]);
					 storeId = stores.addNewStore(merchantId, m8[1], m8[2]);
						 productId = merchants.addToStore(storeId, m8[3], m8[4], 
								Integer.parseInt(m8[5]), Integer.parseInt(m8[6]));

					 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m8[7]), today.minusDays(21),Integer.parseInt(m8[8]));
					 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m8[9]), today.minusDays(14),Integer.parseInt(m8[10]));
					 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m8[11]), today.minusDays(7),Integer.parseInt(m8[12]));
					 productId2 = merchants.addToStore(storeId, m8[13], m8[14], 
							Integer.parseInt(m8[15]), Integer.parseInt(m8[16]));	
					 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m8[17]), today.minusDays(21),Integer.parseInt(m8[18]));
					 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m8[19]), today.minusDays(14),Integer.parseInt(m8[20]));
					 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m8[21]), today.minusDays(7),Integer.parseInt(m8[22]));
					 
						String[] m9 = {"sigal","your toys","ebay","wooden kitchen","toys","100","80","25","50","50","60","100","70",
								"kid bicycle","toys","100","60","15","50","28","60","42","70"};
					
						merchantId = merchants.addNewMerchant(m9[0]);
						 storeId = stores.addNewStore(merchantId, m9[1], m9[2]);
							 productId = merchants.addToStore(storeId, m9[3], m9[4], 
									Integer.parseInt(m9[5]), Integer.parseInt(m9[6]));

						 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m9[7]), today.minusDays(21),Integer.parseInt(m9[8]));
						 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m9[9]), today.minusDays(14),Integer.parseInt(m9[10]));
						 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m9[11]), today.minusDays(7),Integer.parseInt(m9[12]));
						 productId2 = merchants.addToStore(storeId, m9[13], m9[14], 
								Integer.parseInt(m9[15]), Integer.parseInt(m9[16]));	
						 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m9[17]), today.minusDays(21),Integer.parseInt(m9[18]));
						 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m9[19]), today.minusDays(14),Integer.parseInt(m9[20]));
						 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m9[21]), today.minusDays(7),Integer.parseInt(m9[22]));
						 
						 
							String[] m10 = {"orly","my toys","ali express","pink ball","toys","100","45","25","32","50","33","100","34",
									"smiley doll","toys","100","25","15","36","28","37","42","38"};
				
							merchantId = merchants.addNewMerchant(m10[0]);
							 storeId = stores.addNewStore(merchantId, m10[1], m10[2]);
								 productId = merchants.addToStore(storeId, m10[3], m10[4], 
										Integer.parseInt(m10[5]), Integer.parseInt(m10[6]));

							 orderId1 = orders.addNewOrderLocal(productId, Integer.parseInt(m10[7]), today.minusDays(21),Integer.parseInt(m10[8]));
							 orderId2 = orders.addNewOrderLocal(productId, Integer.parseInt(m10[9]), today.minusDays(14),Integer.parseInt(m10[10]));
							 orderId3 = orders.addNewOrderLocal(productId, Integer.parseInt(m10[11]), today.minusDays(7),Integer.parseInt(m10[12]));
							 productId2 = merchants.addToStore(storeId, m10[13], m10[14], 
									Integer.parseInt(m10[15]), Integer.parseInt(m10[16]));	
							 orderId4 = orders.addNewOrderLocal(productId2, Integer.parseInt(m10[17]), today.minusDays(21),Integer.parseInt(m10[18]));
							 orderId5 = orders.addNewOrderLocal(productId2, Integer.parseInt(m10[19]), today.minusDays(14),Integer.parseInt(m10[20]));
							 orderId6 = orders.addNewOrderLocal(productId2, Integer.parseInt(m10[21]), today.minusDays(7),Integer.parseInt(m10[22]));
							
							
							
		List<Merchant> allMerchants = merchantRepository.findAll();
		List<Recommendation> recommendations = new ArrayList<>();
		for (Merchant merchant: allMerchants)
		{
			List<Recommendation> rs = this.recommendation.recommend(merchant);
			recommendations.addAll(rs);

		}
		for (Recommendation r: recommendations)
		{
			Merchant toMerchant = r.getToMerchant();
			System.out.println("||||||||||||Recommendation:|||||||||||");
			System.out.println("to Merchant: "+toMerchant.getMerchantName()+", id: "+toMerchant.getMerchantId());
			System.out.println("Product: "+r.getProductName());
			System.out.println("at Store: "+r.getAtStore().getStoreName()+" on platform: "+r.getAtStore().getPlatform().getPlatformName());
		}
		
		
	}

	
	
}
	

