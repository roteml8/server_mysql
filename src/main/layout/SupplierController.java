package main.layout;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import main.data.Supplier;
import main.infra.SupplierService;

@Controller 
@RequestMapping(path="/database/suppliers") 
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewSupplier (@RequestParam String name
	      ) {
				Long newId = this.supplierService.addNewSupplier(name);

	    return "Saved supplier successfully. Supplier ID " + newId;
	  }
	
	@PostMapping(path="/addproduct") // Map ONLY POST Requests
	  public @ResponseBody String addProducts(@RequestParam Long supplierId, @RequestParam String name
	      ,  @RequestParam String category,@RequestParam double price, 
	      @RequestParam int quantity) {
		
			return this.supplierService.addToCatalog(supplierId, name, category, price, quantity);
	  }
	

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Supplier> getAllSuppliers() {
	    // This returns a JSON or XML with the users
	    return this.supplierService.getAll();
	  }
	

}
