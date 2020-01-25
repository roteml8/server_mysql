package main.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.rdb.CategoryRepository;
import main.data.ProductCategory;

@Controller 
@RequestMapping(path="/database/categories") 
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewCategory (@RequestParam String name
	      ) {

	    ProductCategory c = new ProductCategory();
	    c.setCategoryName(name);
	    categoryRepository.save(c);
	    return "Saved category successfully.";
	  }

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<ProductCategory> getAllCategories() {
	    // This returns a JSON or XML with the users
	    return categoryRepository.findAll();
	  }
	
	

}
