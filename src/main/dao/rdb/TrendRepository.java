package main.dao.rdb;

import main.data.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Trend;

import java.util.Collection;
import java.util.List;

@Repository
public interface TrendRepository extends CrudRepository<Trend,Long>  {
    List<Trend> findAllByProductNameInAndPlatform_PlatformNameIsNotLikeOrderByForecastDateDesc(
            Collection<String> productName, String platform_platformName);

    List<Trend> findAllByProductNameAndPlatform_PlatformNameIsLike(String productName, String platform_platformName);
}
