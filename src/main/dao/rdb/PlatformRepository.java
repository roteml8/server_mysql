package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.Platform;

public interface PlatformRepository extends CrudRepository<Platform, String> {

}
