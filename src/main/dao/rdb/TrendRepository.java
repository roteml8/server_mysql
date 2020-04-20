package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Trend;

@Repository
public interface TrendRepository extends CrudRepository<Trend,Long>  {

}
