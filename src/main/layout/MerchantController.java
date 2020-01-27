package main.layout;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.rdb.MerchantPurchaseRepository;
import main.dao.rdb.MerchantRepository;
import main.dao.rdb.PlatformRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.StoreProductRepository;
import main.dao.rdb.StoreRepository;
import main.data.Merchant;
import main.data.MerchantPurchase;
import main.data.Platform;
import main.data.Product;
import main.data.Store;
import main.data.StoreProduct;


@Controller 
@RequestMapping(path="/database/merchants") 
public class MerchantController {
	
	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private MerchantPurchaseRepository purchasesRepository;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private StoreProductRepository storeProductRepository;

	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewMerchant (@RequestParam String name
	    ) {

	    Merchant m = new Merchant();
	    m.setMerchantName(name);
	    merchantRepository.save(m);
	    return "Saved merchant successfully.";
	  }
	
	@PostMapping(path="/purchase") // Map ONLY POST Requests
	  public @ResponseBody String purchaseProducts (@RequestParam Long productId, @RequestParam Long merchantId,
			  @RequestParam int amount,
			  @RequestParam int price,
			  @RequestParam Long storeId	  
	    ) {

		Optional<Product> p = this.productRepository.findById(productId);
		if (!p.isPresent())
			return "Product does not exist in the system.";
		else {
			Product theProduct = p.get();
			if (theProduct.getQuantity() < amount)
				return "Inventory contains only "+theProduct.getQuantity()+"items";
			else {
				theProduct.setQuantity(theProduct.getQuantity()-amount);
				Optional<Store> s = this.storeRepository.findById(storeId);
				if (!s.isPresent())
					return "Store does not exist in the system.";
				else
				{
					Store theStore = s.get();
					Optional<Merchant> m= this.merchantRepository.findById(merchantId);
					if (!m.isPresent())
						return "merchant does not exist in system";
					if (theStore.getMerchant().getMerchantId() != m.get().getMerchantId())
						return "Merchant is not this store's owner";
					StoreProduct newProduct = new StoreProduct();
					newProduct.setPrice(price);
					newProduct.setQuantity(amount);
					newProduct.setName(theProduct.getProductName());
					this.storeProductRepository.save(newProduct);
					theStore.getStoreProducts().add(newProduct);
					MerchantPurchase newPurchase = new MerchantPurchase();
					newPurchase.setAmount(amount);
					newPurchase.setMerchant(m.get());
					newPurchase.setProduct(theProduct);
					this.purchasesRepository.save(newPurchase);
				    return "Products purchased successfully.";

				}
			}
						}
	  }
	
	@PostMapping(path="/addstore") // Map ONLY POST Requests
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
				this.storeRepository.save(newStore);
				return "store saved successfully";



						}
	  }

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Merchant> getAllMerchants() {
	    // This returns a JSON or XML with the users
	    return merchantRepository.findAll();
	  }
	  
	  @GetMapping(path="/stores")
	  public @ResponseBody Iterable<Store> getAllStores() {
	    // This returns a JSON or XML with the users
	    return storeRepository.findAll();
	  }

}
