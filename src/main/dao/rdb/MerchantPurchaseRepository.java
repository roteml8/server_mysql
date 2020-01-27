package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.MerchantPurchase;

public interface MerchantPurchaseRepository extends CrudRepository<MerchantPurchase,Long> {

}
