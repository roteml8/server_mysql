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
import main.infra.ProductService;

@Controller 
@RequestMapping(path="/database/products") 
public class ProductController {
	
	@Autowired
	private ProductService productService;

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Product> getAllProducts() {
	  
	    return productService.getAll();
	  }
	  
	  @GetMapping(path="/category")
	  public @ResponseBody Iterable<Product> getProductsByCategory(@RequestParam String category
		      ){
		  
		  return productService.getByCategory(category);
	  }
	  
	  @GetMapping(path="/supplier")
	  public @ResponseBody Iterable<Product> getProductsBySupplier(@RequestParam Long supplierId
		      ){
		  
		  		try {
		  			return productService.getBySupplier(supplierId);
		  		}
		  		catch (Exception e)
		  		{
		  			e.printStackTrace();
		  			return new ArrayList<Product>();
		  		}
	  }
	  
	  @GetMapping(path="/name")
	  public @ResponseBody Iterable<Product> getProductsByName(@RequestParam String name
		      ){
		  return productService.getByName(name);

	  }
	  
	  @GetMapping(path="/price")
	  public @ResponseBody Iterable<Product> getProductsByPrice(@RequestParam double price
		      ){
		  return productService.getByPrice(price);

	  }
	  
	  @GetMapping(path="/quantity")
	  public @ResponseBody Iterable<Product> getProductsByQuantity(@RequestParam int quantity
		      ){
		  return productService.getByQuantity(quantity);

	  }
	
	

}
