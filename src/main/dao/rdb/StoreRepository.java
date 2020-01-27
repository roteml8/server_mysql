package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import main.data.Merchant;
import main.data.Platform;
import main.data.Store;

public interface StoreRepository extends CrudRepository<Store,Long> {
	
	public ArrayList<Store> findByMerchant(Merchant m);
	public ArrayList<Store> findByStoreNameIgnoreCaseContaining (String name);
	public ArrayList<Store> findByPlatform(Platform p);

}
