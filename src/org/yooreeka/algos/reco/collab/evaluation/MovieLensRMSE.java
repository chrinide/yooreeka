package org.yooreeka.algos.reco.collab.evaluation;

import java.io.File;
import java.util.List;

import org.yooreeka.algos.reco.collab.data.MovieLensDataset;
import org.yooreeka.algos.reco.collab.model.Dataset;
import org.yooreeka.algos.reco.collab.model.Rating;
import org.yooreeka.algos.reco.collab.model.RecommendationType;
import org.yooreeka.algos.reco.collab.recommender.Delphi;
import org.yooreeka.config.YooreekaConfigurator;

/**
 * 
 * @deprecated use <code>MovieLensDelphiRMSE</code> instead.
 */
public class MovieLensRMSE {

    public MovieLensRMSE() {
    }

    public MovieLensDataset createTrainingDataset(int n) {
        String dataDir = YooreekaConfigurator.getProperty("iweb2.movielens.data.dir");
        
        File users = new File(dataDir, MovieLensDataset.USERS_FILENAME);
        File items = new File(dataDir, MovieLensDataset.ITEMS_FILENAME);
        File ratings = new File(dataDir, "u"+n+".base");

        return new MovieLensDataset(users, items, ratings);
    }
    
    public List<Rating> createTestRatings(int n) {
        String dataDir = YooreekaConfigurator.getProperty("iweb2.movielens.data.dir");
        
        File ratings = new File(dataDir, "u"+n+".test");

        return MovieLensDataset.loadRatings(ratings);
    }
    
    public double[] calculate() {

        double similarityThreshold = 0.50;

        int N = 5;
        
        double[] rmse = new double[N];

        RMSEEstimator rmseEstimator = new RMSEEstimator();
        
        for(int i = 1; i <= N; i++) {
        
            Dataset ds = createTrainingDataset(i);
            
            Delphi delphi = new Delphi(ds, RecommendationType.ITEM_BASED);
            delphi.setSimilarityThreshold(similarityThreshold);
            
            List<Rating> testRatings = createTestRatings(i);
            
            double rmseValue = rmseEstimator.calculateRMSE(delphi, testRatings);
            System.out.println(i + ": rmse = " + rmseValue);

            rmse[i-1] = rmseValue;
        }
        
        return rmse;
    }
    
    
    
    public static void main(String[] args) {
        MovieLensRMSE rmse = new MovieLensRMSE();
        rmse.calculate();
    }
}
