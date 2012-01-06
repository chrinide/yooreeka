package org.yooreeka.reco.collaborative.evaluation;

import java.util.Collection;

import org.yooreeka.reco.collaborative.data.MovieLensDataset;
import org.yooreeka.reco.collaborative.model.Dataset;
import org.yooreeka.reco.collaborative.model.Item;
import org.yooreeka.reco.collaborative.model.Rating;
import org.yooreeka.reco.collaborative.model.User;
import org.yooreeka.reco.collaborative.recommender.Recommender;

/**
 * Calculates Root Mean Squared Error for the recommender. 
 */
public class RMSEEstimator {

    private boolean verbose = true;
    
    public RMSEEstimator() {
        // empty
    }
    
    /**
     * Calculates Root Mean Squared Error for the recommender. Uses 
     * test rating values returned by recommender's dataset.
     * 
     * @param delphi recommender.
     * @return root mean squared error value.
     */
    public double calculateRMSE(Recommender delphi) {
        
        MovieLensDataset ds = (MovieLensDataset)delphi.getDataset(); 
        Collection<Rating> testRatings = ds.getTestRatings();
        
        return calculateRMSE(delphi, testRatings);
    }

    /**
     * Calculates Root Mean Squared Error for the recommender. 
     * 
     * @param delphi recommender to evaluate.
     * @param testRatings ratings that will be used to calculate the error.  
     * @return root mean squared error.
     */
    public double calculateRMSE(Recommender delphi, Collection<Rating> testRatings) {
        
        double sum = 0.0;
        
        Dataset ds = delphi.getDataset();

        int totalSamples = testRatings.size();
        
        if( verbose ) {
            System.out.println("Calculating RMSE ...");
            System.out.println("Training ratings count: " + ds.getRatingsCount());
            System.out.println("Test ratings count: " + testRatings.size());
        }
        
        for(Rating r : testRatings ) {
            User user = ds.getUser(r.getUserId());
            Item item = ds.getItem(r.getItemId());
            double predictedItemRating = delphi.predictRating(user, item);

            if( predictedItemRating > 5.0 ) {
                predictedItemRating = 5.0;
                //System.out.println("Predicted item rating: " + predictedItemRating);
            }
//            if( verbose ) {
//                System.out.println(
//                        "user: " + r.getUserId() + 
//                        ", item: " + r.getItemId() +
//                        ", actual rating: " + r.getRating() + 
//                        ", predicted: " + String.valueOf(predictedItemRating));
//            }

            sum += Math.pow((predictedItemRating - r.getRating()), 2);
            
        }
        double rmse = Math.sqrt(sum / totalSamples); 

        if( verbose ) {
            System.out.println("RMSE:" + rmse);
        }
        return rmse;
        
    }
    
    public void compareRMSEs(Recommender delphi) {
        
        MovieLensDataset ds = (MovieLensDataset)delphi.getDataset(); 
        Collection<Rating> testRatings = ds.getTestRatings();
        
        compareRMSEs(delphi, testRatings);
    }

    
    public void compareRMSEs(Recommender delphi, Collection<Rating> testRatings) {
    
        double sum = 0.0;
        double sumAvgItem = 0.0;
        double sumAvgUser = 0.0;
        
        Dataset ds = delphi.getDataset();

        int totalSamples = testRatings.size();
        
        if( verbose ) {
            System.out.println("Calculating RMSE ...");
            System.out.println("Training ratings count: " + ds.getRatingsCount());
            System.out.println("Test ratings count: " + testRatings.size());
        }
        
        for(Rating r : testRatings ) {
            User user = ds.getUser(r.getUserId());
            Item item = ds.getItem(r.getItemId());
            double predictedItemRating = delphi.predictRating(user, item);
            double predictedAvgItemRating = delphi.predictBasedOnItemAverage(item);
            double predictedAvgUserRating = delphi.predictBasedOnUserAverage(user);
            
            if( predictedItemRating > 5.0 ) {
                predictedItemRating = 5.0;
                //System.out.println("Predicted item rating: " + predictedItemRating);
            }
//            if( verbose ) {
//                System.out.println(
//                        "user: " + r.getUserId() + 
//                        ", item: " + r.getItemId() +
//                        ", actual rating: " + r.getRating() + 
//                        ", predicted: " + String.valueOf(predictedItemRating));
//            }

            sum += Math.pow((predictedItemRating - r.getRating()), 2);
            sumAvgItem += Math.pow((predictedAvgItemRating - r.getRating()), 2);
            sumAvgUser += Math.pow((predictedAvgUserRating - r.getRating()), 2);
            
        }
        
        double rmse = Math.sqrt(sum / totalSamples); 
        double rmseAvgItem = Math.sqrt(sumAvgItem / totalSamples); 
        double rmseAvgUser = Math.sqrt(sumAvgUser / totalSamples); 

        System.out.println("RMSE:" + rmse);
        System.out.println("RMSE (based on avg. Item rating):" + rmseAvgItem);
        System.out.println("RMSE (based on avg. User rating):" + rmseAvgUser);
    }
    
    public boolean isVerbose() {
        return verbose;
    }


    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
