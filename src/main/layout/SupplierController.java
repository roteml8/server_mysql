package main.layout;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path="/database/suppliers") 
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewSupplier (@RequestParam String id, @RequestParam String name
	      , @RequestParam String password) {

	    Supplier s = new Supplier();
	    s.setSupplierId(id);
	    s.setSupplierName(name);
	    s.setSupplierPassword(password);
	    supplierRepository.save(s);
	    return "Saved supplier successfully.";
	  }
	
	@PostMapping(path="/addproduct") // Map ONLY POST Requests
	  public @ResponseBody String addProducts(@RequestParam String supplierId, @RequestParam String name
	      , @RequestParam String productId, @RequestParam String category,@RequestParam double price, 
	      @RequestParam int quantity) {
		
		
		Optional<Supplier> s = this.supplierRepository.findById(supplierId);
		if (s.isPresent()) {
			Product p = new Product();
			Optional<ProductCategory> c = this.categoryRepository.findById(category);
			if (!c.isPresent()) {
				ProductCategory newCategory = new ProductCategory(category);
				this.categoryRepository.save(newCategory);
				p.setCategory(newCategory);
			}
			else
			{
				p.setCategory(c.get());
			}
			Supplier theSupplier = s.get();
			p.setSupplier(theSupplier);
			p.setProductId(productId);
			p.setProductName(name);
			p.setProductPrice(price);
			p.setQuantity(quantity);
		    productRepository.save(p);
		    List<Product> supProducts = theSupplier.getProducts();
		    supProducts.add(p);
		    return "Saved products successfully.";

		}
		else
			return "no supplier with specified id.";

	  }
	
	
	

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Supplier> getAllSuppliers() {
	    // This returns a JSON or XML with the users
	    return supplierRepository.findAll();
	  }
	

}
