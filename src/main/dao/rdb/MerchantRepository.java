package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.Merchant;


public interface MerchantRepository extends CrudRepository<Merchant,String> {

}
