package main.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import main.dao.rdb.MerchantPurchaseRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.StoreProductRepository;
import main.dao.rdb.StoreRepository;
import main.data.MerchantPurchase;
import main.data.Product;
import main.data.Store;
import main.data.StoreProduct;
import main.infra.MerchantService;
import main.infra.ProductService;
import main.infra.StoreService;
import main.infra.SupplierService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=default" })
public class MerchantUploadTest {
	
	@Autowired
	private MerchantService merchants;
	@Autowired
	private StoreService stores;
	@Autowired
	private ProductService products;
	@Autowired
	private SupplierService suppliers;
	@Autowired
	private StoreProductRepository storeProducts;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private MerchantPurchaseRepository purchaseRepository;
	
	
	@Test
	public void testUploadToStoreFromSupplierByMerchant() throws Exception
	{
		Long supplierId = suppliers.addNewSupplier("rotem");
		String productName = "heart rings";
		String productCategory = "jewelry";
		int productPrice = 15;
		int productQuantity = 50;
		Long productId = suppliers.addToCatalog(supplierId, productName, productCategory, productPrice, productQuantity);
		Long merchantId = merchants.addNewMerchant("yaron");
		String storeName =  "yarons jewelry";
		Long storeId = stores.addNewStore(merchantId, storeName, "ebay");
		int storeAmount = 20;
		int storePrice = 20;
		String purchaseId = merchants.addToStoreFromSupplier(productId, merchantId, storeAmount, storePrice, storeId);
		Iterable<MerchantPurchase> allPurchases = purchaseRepository.findAll();
		assertThat(allPurchases).hasSize(1);
		//ArrayList<Store> storesByName = (ArrayList<Store>) stores.getByName(storeName);
		Optional<Store> theStore = storeRepository.findById(storeId);
		ArrayList<StoreProduct> allStoreProducts = storeProducts.findByStore(theStore.get());
		StoreProduct theProduct = allStoreProducts.get(0);
		assertThat(theProduct.getName()).asString().isEqualTo(productName);
		assertThat(theProduct.getQuantity()).isEqualTo(storeAmount);
		assertThat(theProduct.getPrice()).isEqualTo(storePrice);
		Optional<Product> catalogProduct = productRepository.findById(productId);
		assertThat(catalogProduct.isPresent());
		Product rv = catalogProduct.get();
		assertThat(rv.getQuantity()).isEqualTo(productQuantity-storeAmount);
		
		
		
		

	}

}
