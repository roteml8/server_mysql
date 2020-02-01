package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.MerchantPurchase;

@Repository
public interface MerchantPurchaseRepository extends CrudRepository<MerchantPurchase,Long> {

}
