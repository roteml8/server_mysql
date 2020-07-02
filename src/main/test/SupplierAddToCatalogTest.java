package main.test;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import main.dao.rdb.CategoryRepository;
import main.dao.rdb.MerchantPurchaseRepository;
import main.dao.rdb.MerchantRepository;
import main.dao.rdb.PlatformRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.StoreProductRepository;
import main.dao.rdb.StoreRepository;
import main.dao.rdb.SupplierRepository;
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
public class SupplierAddToCatalogTest {
	
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
	private MerchantRepository merchantRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private MerchantPurchaseRepository purchaseRepository;
	
	@Test
	public void testUploadToCatalogBySupplier() throws Exception
	{
		Long supplierId = suppliers.addNewSupplier("rotem");
		String productName = "heart rings";
		String productCategory = "jewelry";
		int productPrice = 15;
		int productQuantity = 50;
		Long productId = suppliers.addToCatalog(supplierId, productName, productCategory, productPrice, productQuantity);
		Iterable<Product> allProducts = products.getAll();
		assertThat(allProducts).isNotEmpty();
		assertThat(allProducts).hasSize(1);
		ArrayList<Product> allBySupplier = (ArrayList<Product>) products.getBySupplier(supplierId);
		assertThat(allBySupplier).isNotEmpty();
		Product p = new Product();		
		Product theProduct = allBySupplier.get(0);
		String rvName = theProduct.getProductName();
		assertThat(rvName).asString().isEqualTo(productName);
	}
	
	

}
