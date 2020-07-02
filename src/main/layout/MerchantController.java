package main.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.data.Merchant;
import main.infra.MerchantService;


@Controller 
@RequestMapping(path="/database/merchants") 
public class MerchantController {
	

	@Autowired
	private MerchantService merchantService;

	
	@PostMapping(path="/add") 
	  public @ResponseBody String addNewMerchant (@RequestParam String name
	    ) {

			Long theId = this.merchantService.addNewMerchant(name);
			return "Merchant saved successfully. ID is: "+theId;
	  }
	
	@PostMapping(path="/purchasenew") 
	  public @ResponseBody String purchaseProducts (@RequestParam Long productId, @RequestParam Long merchantId,
			  @RequestParam int amount,
			  @RequestParam int price,
			  @RequestParam Long storeId	  
	    ) {

		return this.merchantService.addToStoreFromSupplier(productId, merchantId, amount, price, storeId); 
	  }
	
	@PostMapping(path="/addnew") 
	  public @ResponseBody Long addProducts (
			  @RequestParam String productName,
			  @RequestParam String productCategory,
			  @RequestParam int amount,
			  @RequestParam int price,
			  @RequestParam Long storeId	  
	    ) {

		return this.merchantService.addToStore(storeId,productName,productCategory, amount, price); 
	  }
	
	@PostMapping(path="/addinventory") // Map ONLY POST Requests
	  public @ResponseBody String buyMoreFromProduct (@RequestParam Long productId, @RequestParam Long merchantId,
			  @RequestParam int amount,
			  @RequestParam Long storeId	  
	    ) {
		return this.merchantService.addToInventoryFromSupplier(productId, merchantId, amount, storeId);
	}
	


	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Merchant> getAllMerchants() {
	    return this.merchantService.getAll();
	  }
	  


}
