package main.infra;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.MerchantPurchaseRepository;
import main.dao.rdb.MerchantRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.StoreProductRepository;
import main.dao.rdb.StoreRepository;
import main.data.Merchant;
import main.data.MerchantPurchase;
import main.data.Product;
import main.data.Store;
import main.data.StoreProduct;

@Service
public class MerchantService {
	
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
	
	public Long addNewMerchant (String merchantName)
	{
	    Merchant m = new Merchant();
	    m.setMerchantName(merchantName);
	    Merchant rv = merchantRepository.save(m);
	    Long newId = rv.getMerchantId();
	    return newId; 
	}
	
	public String addToStoreFromSupplier (Long productId, Long merchantId, int amount, int price, Long storeId)
	{
		Optional<Product> p = this.productRepository.findById(productId);
		if (!p.isPresent())
			return "Product does not exist in the system.";
		else {
			Product theProduct = p.get();
			if (theProduct.getQuantity() < amount)
				return "Inventory contains only "+theProduct.getQuantity()+"items";
			else {
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
					theProduct.setQuantity(theProduct.getQuantity()-amount);
					this.productRepository.save(theProduct);
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
	
	public String addToInventoryFromSupplier (Long productId, Long merchantId, int amount, Long storeId)
	{

		Optional<Product> p = this.productRepository.findById(productId);
		if (!p.isPresent())
			return "Product does not exist in the system.";
		else {
			Product theProduct = p.get();
			if (theProduct.getQuantity() < amount)
				return "Inventory contains only "+theProduct.getQuantity()+"items";
			else {

				Optional<Store> s = this.storeRepository.findById(storeId);
				if (!s.isPresent())
					return "Store does not exist in the system.";
				else
				{
					Store theStore = s.get();
					StoreProduct toBeAdded = this.storeProductRepository.findByNameAndStore(theProduct.getProductName(),theStore);
					theProduct.setQuantity(theProduct.getQuantity()-amount);
					this.productRepository.save(theProduct);
					toBeAdded.setQuantity(toBeAdded.getQuantity() + amount);
					this.storeProductRepository.save(toBeAdded);
					return "Product amount updated!";

			}
		}
	  }
	}
	
	public Iterable<Merchant> getAll()
	{
		return this.merchantRepository.findAll();
	}
	
	

}
