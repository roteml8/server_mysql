package main.dao.rdb;

import main.recommendation.Recommendation;
import org.springframework.data.repository.CrudRepository;

public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {
}
