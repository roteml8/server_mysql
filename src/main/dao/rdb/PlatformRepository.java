package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Platform;
@Repository
public interface PlatformRepository extends CrudRepository<Platform, String> {

}
