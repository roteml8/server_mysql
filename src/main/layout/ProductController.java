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
import main.data.Product;
import main.data.ProductCategory;

@Controller 
@RequestMapping(path="/database/products") 
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

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
	

}
