package main.layout;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.rdb.MerchantRepository;
import main.dao.rdb.PlatformRepository;
import main.dao.rdb.StoreRepository;
import main.data.Merchant;
import main.data.Platform;
import main.data.Store;

@Controller 
@RequestMapping(path="/database/stores") 
public class StoreController {
	
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private MerchantRepository merchantRepository;
	
	  @GetMapping(path="/merchant")
	  public @ResponseBody Iterable<Store> getStoresByMerchant(@RequestParam Long merchantId) {
		  Optional<Merchant> m = this.merchantRepository.findById(merchantId);
		  if (m.isPresent()) {
			  Merchant theMerchant = m.get();
			  return this.storeRepository.findByMerchant(theMerchant);
		  }
		  return new ArrayList<>();
	  }
	  
	  @GetMapping(path="/platform")
	  public @ResponseBody Iterable<Store> getStoresByPlatform(@RequestParam String platform) {
		  Optional<Platform> p = this.platformRepository.findById(platform);
		  if (p.isPresent()) {
			  Platform thePlatform = p.get();
			  return this.storeRepository.findByPlatform(thePlatform);
		  }
		  return new ArrayList<>();
	  }
	  
	  @GetMapping(path="/name")
	  public @ResponseBody Iterable<Store> getStoresByName(@RequestParam String name) {

		  return this.storeRepository.findByStoreNameIgnoreCaseContaining(name);
	  }
	  
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Store> getAllStores() {
	    return storeRepository.findAll();
	  }
	  
		@PostMapping(path="/add") // Map ONLY POST Requests
		  public @ResponseBody String addStore (@RequestParam Long merchantId, @RequestParam String name,
				  @RequestParam String platform	  
		    ) {
				Optional<Merchant> m = this.merchantRepository.findById(merchantId);
				if (!m.isPresent())
					return "Merchant does not exist in th system";
				else {
					Merchant theMerchant = m.get();
					Store newStore = new Store();
					if (!this.platformRepository.findById(platform).isPresent())
					{
						Platform p = new Platform();
						p.setPlatformName(platform);
						this.platformRepository.save(p);
					}
					newStore.setPlatform(this.platformRepository.findById(platform).get());
					newStore.setStoreName(name);
					newStore.setMerchant(theMerchant);
					Store rv  =this.storeRepository.save(newStore);
					return "store saved successfully! Store ID "+rv.getStoreId();

							}
		  }

}
