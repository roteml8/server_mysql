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
	private StoreProductRepository storeProductRepository;

	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewMerchant (@RequestParam String name
	    ) {

	    Merchant m = new Merchant();
	    m.setMerchantName(name);
	    Merchant rv = merchantRepository.save(m);
	    return "Saved merchant successfully. Merchant ID is "+rv.getMerchantId();
	  }
	
	@PostMapping(path="/purchasenew") // Map ONLY POST Requests
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
					newProduct.setCategory(theProduct.getCategory());
					newProduct.setStore(theStore);
					this.storeProductRepository.save(newProduct);
					MerchantPurchase newPurchase = new MerchantPurchase();
					newPurchase.setAmount(amount);
					newPurchase.setMerchant(m.get());
					newPurchase.setProduct(theProduct);
					MerchantPurchase rv = this.purchasesRepository.save(newPurchase);
				    return "Products purchased successfully. Purchase ID is "+rv.getPurchaseId();

				}
			}
						}
	  }
	
	@PostMapping(path="/addinventory") // Map ONLY POST Requests
	  public @ResponseBody String buyMoreFromProduct (@RequestParam Long productId, @RequestParam Long merchantId,
			  @RequestParam int amount,
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
				this.productRepository.save(theProduct);
				Optional<Store> s = this.storeRepository.findById(storeId);
				if (!s.isPresent())
					return "Store does not exist in the system.";
				else
				{
					Store theStore = s.get();
					StoreProduct toBeAdded = this.storeProductRepository.findByNameAndStore(theProduct.getProductName(),theStore);
					toBeAdded.setQuantity(toBeAdded.getQuantity() + amount);
					this.storeProductRepository.save(toBeAdded);
					return "Product amount updated!";

			}
						}
	  }
	}
	


	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Merchant> getAllMerchants() {
	    // This returns a JSON or XML with the users
	    return merchantRepository.findAll();
	  }
	  


}
