package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.Store;

public interface StoreRepository extends CrudRepository<Store,Long> {

}
