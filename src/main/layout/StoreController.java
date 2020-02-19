package main.layout;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.data.Store;
import main.infra.StoreService;

@Controller 
@RequestMapping(path="/database/stores") 
public class StoreController {
	
	@Autowired
	private StoreService storeService;

	
	  @GetMapping(path="/merchant")
	  public @ResponseBody Iterable<Store> getStoresByMerchant(@RequestParam Long merchantId) {
		try {
			return this.storeService.getByMerchant(merchantId);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	  }
	  
	  @GetMapping(path="/platform")
	  public @ResponseBody Iterable<Store> getStoresByPlatform(@RequestParam String platform) {
		  	return this.storeService.getByPlatform(platform);
	  }
	  
	  @GetMapping(path="/name")
	  public @ResponseBody Iterable<Store> getStoresByName(@RequestParam String name) {

		  return this.storeService.getByName(name);
		  }
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Store> getAllStores() {
	    return this.storeService.getAll();
	  }
	  
		@PostMapping(path="/add") // Map ONLY POST Requests
		  public @ResponseBody String addStore (@RequestParam Long merchantId, @RequestParam String name,
				  @RequestParam String platform	  
		    ) {
				return this.storeService.addNewStore(merchantId, name, platform);
		  }

}
