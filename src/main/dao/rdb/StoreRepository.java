package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Merchant;
import main.data.Platform;
import main.data.Store;
@Repository

public interface StoreRepository extends CrudRepository<Store,Long> {
	
	public ArrayList<Store> findByMerchant(Merchant m);
	public ArrayList<Store> findByStoreNameIgnoreCaseContaining (String name);
	public ArrayList<Store> findByPlatform(Platform p);
	public Iterable<Store> findByStoreName(String name);

}
