package main.layout;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.rdb.CategoryRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.SupplierRepository;
import main.data.Product;
import main.data.ProductCategory;
import main.data.Supplier;

@Controller 
@RequestMapping(path="/database/products") 
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SupplierRepository supplierRepository;

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Product> getAllProducts() {
	    // This returns a JSON or XML with the users
	    return productRepository.findAll();
	  }
	  
	  @GetMapping(path="/category")
	  public @ResponseBody Iterable<Product> getProductsByCategory(@RequestParam String category
		      ){
		  
		Optional<ProductCategory> c = this.categoryRepository.findById(category);
		if (!c.isPresent()) {
			return new ArrayList<Product>();
		}
		else {
			ProductCategory theCategory = c.get();
		    return productRepository.findByCategory(theCategory);
		}
	  }
	  
	  @GetMapping(path="/supplier")
	  public @ResponseBody Iterable<Product> getProductsBySupplier(@RequestParam Long supplierId
		      ){
		  
		Optional<Supplier> s = this.supplierRepository.findById(supplierId);
		if (!s.isPresent()) {
			return new ArrayList<Product>();
		}
		else {
			Supplier theSupplier = s.get();
			return this.productRepository.findBySupplier(theSupplier);
		}
	  }
	  
	  @GetMapping(path="/name")
	  public @ResponseBody Iterable<Product> getProductsByName(@RequestParam String name
		      ){
		  return this.productRepository.findByProductNameIgnoreCaseContaining(name);
		  

	  }
	  
	  @GetMapping(path="/price")
	  public @ResponseBody Iterable<Product> getProductsByPrice(@RequestParam double price
		      ){
		  return this.productRepository.findByProductPriceLessThan(price);	  

	  }
	  
	  @GetMapping(path="/quantity")
	  public @ResponseBody Iterable<Product> getProductsByQuantity(@RequestParam int quantity
		      ){
		  return this.productRepository.findByQuantityGreaterThan(quantity); 

	  }
	
	

}
