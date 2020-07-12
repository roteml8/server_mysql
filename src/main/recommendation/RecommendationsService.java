package main.recommendation;

import main.dao.rdb.MerchantRepository;
import main.data.Merchant;
import main.data.MerchantProfile;
import main.data.Trend;
import main.recommendation.exceptions.MerchantProfileNotFoundInAnyClusterException;
import main.recommendation.exceptions.NoSuchMerchantProfileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationsService {
	
	//hyper-parameters for KMeans algorithm
    @Value("${recommendations.kmeans.k}")
    private int k;

    @Value("${recommendations.kmeans.maxIterations}")
    private int maxIterations;

    private EuclideanDistance distance = new EuclideanDistance();
    private MerchantRepository merchantRepository;
    private MerchantProfileService merchantProfileService;
    private TrendForecast trendForecast;


    @Autowired
    public RecommendationsService(MerchantRepository merchantRepository, MerchantProfileService merchantProfileService,
                                  TrendForecast trendForecast) {
        setMerchantRepository(merchantRepository);
        setTrendForecast(trendForecast);
        setMerchantProfileService(merchantProfileService);
    }


    /**
     Algorithm Steps:
     1. Load all merchants from DB
     2. Map them into a List of merchant profiles
     3. Use K-Means to cluster them
     4. Find in which group the merchant is at
     5. From this group of merchants filter only the ones with at least one matching category
     6. Find all trending products 
     7. Return Trending products of the other merchants and in which platform to sell them as recommendations
     */
    @Transactional(readOnly = true)
    public List<Recommendation> recommend(Merchant merchant){
    	
        List<Merchant> merchants = merchantRepository.findAll();
        List<MerchantProfile> merchantProfiles = mapMerchantsToMerchantProfiles(merchants);
        MerchantProfile merchantProfile = findProfileByMerchant(merchantProfiles, merchant);
        Map<Centroid, List<MerchantProfile>> centroids = KMeans.fit(merchantProfiles, k, distance, maxIterations);
     //   System.out.println(centroids.toString());
        List<MerchantProfile> merchantCluster = otherMerchantsInCluster(centroids, merchantProfile);
     //   System.out.println(merchantCluster.toString());
        List<Trend> trendsToRecommend = getTrendingProductsBeingSoldByMerchants(merchantProfile, merchantCluster);
        //System.out.println(trendsToRecommend);
        return trendsToRecommend.stream()
                .map(trend -> new Recommendation(merchant, trend.getProductName(), merchantProfile.getStore()))
                .collect(Collectors.toList());
    }

    // given a list of merchants, create their profiles based on their stores
    // this function assumes only one store per merchant 
    private List<MerchantProfile> mapMerchantsToMerchantProfiles(List<Merchant> merchants){
        return merchants.stream()
                .map(merchantProfileService::toMerchantProfile)
                .filter(merchantProfile -> merchantProfile.getStore() != null)
                .collect(Collectors.toList());
    }

    // given the clusters and a merchant profile, check within the merchants cluster for merchant with at least
    // one matching category and return the relevant merchants 
    private List<MerchantProfile> otherMerchantsInCluster(Map<Centroid, List<MerchantProfile>> centroids,
                                                          MerchantProfile merchantProfile){
    	
    	// get the cluster of the given merchant
        Optional<List<MerchantProfile>> optionalMerchantProfiles = centroids.values()
                .stream()
                .filter(merchantProfilesList -> merchantProfilesList.contains(merchantProfile))
                .findFirst();
        
        // filter merchants who don't have a matching category and the original merchant 
        if (optionalMerchantProfiles.isPresent()){
            return optionalMerchantProfiles.get()
                    .stream()
                    .filter(profile -> !profile.equals(merchantProfile))
                    .filter(profile -> profile.getMerchantCategories().stream().anyMatch(
                            category -> merchantProfile.getMerchantCategories().contains(category)))
                    .collect(Collectors.toList());

        }
        throw new MerchantProfileNotFoundInAnyClusterException(String.format("Merchant Profile: %s %s",
                merchantProfile.getMerchantId(), merchantProfile.getDescription()));
    }

    // given a merchant profile and the other merchants in their cluster, return the other merchants
    // trending products that don't trend on the merchants platform
    private List<Trend> getTrendingProductsBeingSoldByMerchants(MerchantProfile merchantProfile,
                                                                       List<MerchantProfile> merchantProfiles){

        trendForecast.setTrendingProducts();

        return merchantProfiles.stream()
                .map(MerchantProfile::getAllStoreProducts)
                .map(storeProducts -> trendForecast.findTrendsByProductsNotInPlatform(storeProducts,
                        merchantProfile.getStore().getPlatform()))
                .flatMap(List::stream)
                .filter(trend -> !trendForecast.isProductTrendingAtPlatform(trend.getProductName(),
                        merchantProfile.getStore().getPlatform()))
                .collect(Collectors.toList());
    }

    
    private MerchantProfile findProfileByMerchant(List<MerchantProfile> profiles, Merchant merchant){
        Optional<MerchantProfile> optionalProfile = profiles.stream()
            .filter(merchantProfile -> merchantProfile.getMerchantId().equals(merchant.getMerchantId()))
            .findAny();
        if(optionalProfile.isPresent()){
            return optionalProfile.get();
        }

        throw new NoSuchMerchantProfileException(String.format("Merchant: %s %s", merchant.getMerchantId(),
                merchant.getMerchantName()));
    }
    public void setMerchantRepository(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public void setTrendForecast(TrendForecast trendForecast) {
        this.trendForecast = trendForecast;
    }

    public void setMerchantProfileService(MerchantProfileService merchantProfileService) {
        this.merchantProfileService = merchantProfileService;
    }
}
