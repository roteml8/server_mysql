package main.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import main.dao.rdb.ProductRepository;
import main.data.Product;
import main.infra.ProductService;
import main.infra.SupplierService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=default" })
public class MerchantSerachTest {
	
	@Autowired
	private ProductService products;
	@Autowired
	private SupplierService suppliers;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testSearchCatalogByMerchant() throws Exception
	{
		Long supplierId = suppliers.addNewSupplier("rotem");
		String[] names = {"heart ring", "star necklace", "round ring"};
		String productCategory = "jewelry";
		int[] prices = {100,70,49};
		int[] quantities = {100, 200, 500};
		Long productId;
		for (int i=0; i<names.length; i++)
			productId = suppliers.addToCatalog(supplierId, names[i], productCategory, prices[i], quantities[i]);
		Long supplierId2 = suppliers.addNewSupplier("yaron");
		String[] names2 = {"pink dress", "black tie", "yellow tshirt", "orange mini skirt"};
		String productCategory2 = "fashion";
		int[] prices2 = {150,40,30,80};
		int[] quantities2 = {100, 200, 500, 50};
		for (int i=0; i<names2.length; i++)
			productId = suppliers.addToCatalog(supplierId2, names2[i], productCategory2, prices2[i], quantities2[i]);
		ArrayList<Product> productsByName = (ArrayList<Product>) products.getByName("ring");
		assertThat(productsByName).hasSize(2);
		Product ring1 = productsByName.get(0);
		Product ring2 = productsByName.get(1);
		String[] ringsProductNames = {ring1.getProductName(), ring2.getProductName()};
		assertThat(ringsProductNames).contains(names[0],names[2]);
		ArrayList<Product> productsByPrice = (ArrayList<Product>) products.getByPrice(50);
		assertThat(productsByPrice).hasSize(3);
		Product p1 = productsByPrice.get(0);
		Product p2 = productsByPrice.get(1);
		Product p3 = productsByPrice.get(2);
		String[] productNames = {p1.getProductName(), p2.getProductName(), p3.getProductName()};
		assertThat(productNames).contains(names[2],names2[1],names2[2]);
		ArrayList<Product> productsByQuantity = (ArrayList<Product>) products.getByQuantity(200);
		assertThat(productsByQuantity).hasSize(2);
		p1 = productsByQuantity.get(0);
		p2 = productsByQuantity.get(1);
		String[] productNames2 = {p1.getProductName(), p2.getProductName()};
		assertThat(productNames2).contains(names[2],names2[2]);
		ArrayList<Product> productsByCategory = (ArrayList<Product>) products.getByCategory("jewelry");
		assertThat(productsByCategory).hasSize(3);
		p1 = productsByCategory.get(0);
		p2 = productsByCategory.get(1);
		p3 = productsByCategory.get(2);
		String[] productNames3 = {p1.getProductName(), p2.getProductName(), p3.getProductName()};
		assertThat(productNames3).contains(names[0], names[1], names[2]);
		ArrayList<Product> productsBySupplier = (ArrayList<Product>) products.getBySupplier(supplierId);
		assertThat(productsBySupplier).hasSize(3);
		p1 = productsBySupplier.get(0);
		p2 = productsBySupplier.get(1);
		p3 = productsBySupplier.get(2);
		String[] productNames4 = {p1.getProductName(), p2.getProductName(), p3.getProductName()};
		assertThat(productNames4).contains(names[0], names[1], names[2]);




	}

}
