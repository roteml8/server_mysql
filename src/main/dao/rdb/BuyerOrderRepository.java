package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.BuyerOrder;
import main.data.Store;
import main.data.StoreProduct;


@Repository
public interface BuyerOrderRepository extends CrudRepository<BuyerOrder,Long>  {
	
	public ArrayList<BuyerOrder> findByStore(Store store);
	public ArrayList<BuyerOrder> findByProduct(StoreProduct product);


}
