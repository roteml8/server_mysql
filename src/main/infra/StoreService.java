package main.infra;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.MerchantRepository;
import main.dao.rdb.PlatformRepository;
import main.dao.rdb.StoreRepository;
import main.data.Merchant;
import main.data.Platform;
import main.data.Store;

@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private MerchantRepository merchantRepository;
	
	public Long addNewStore (Long merchantId, String name, String platform)
	{
		Optional<Merchant> m = this.merchantRepository.findById(merchantId);
		if (!m.isPresent())
			return (long) 0;
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
			return rv.getStoreId();

			}
	}
	
	public Iterable<Store> getAll()
	{
		return this.storeRepository.findAll();
	}
	
	public Iterable<Store> getByMerchant(Long merchantId) throws Exception
	{
		  Optional<Merchant> m = this.merchantRepository.findById(merchantId);
		  if (m.isPresent()) {
			  Merchant theMerchant = m.get();
			  return this.storeRepository.findByMerchant(theMerchant);
		  }
		  else
			  throw new Exception("Merchant ID does not exist in the system.");
	}
	
	public Iterable<Store> getByPlatform(String platform)
	{
		  Optional<Platform> p = this.platformRepository.findById(platform);
		  if (p.isPresent()) {
			  Platform thePlatform = p.get();
			  return this.storeRepository.findByPlatform(thePlatform);
		  }
		  return new ArrayList<>();
	}
	
	public Iterable<Store> getByName (String name)
	{
		 return this.storeRepository.findByStoreName(name);
	}

	

}
