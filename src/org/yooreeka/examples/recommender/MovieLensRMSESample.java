package org.yooreeka.examples.recommender;

import org.yooreeka.reco.collaborative.data.MovieLensData;
import org.yooreeka.reco.collaborative.data.MovieLensDataset;
import org.yooreeka.reco.collaborative.evaluation.RMSEEstimator;
import org.yooreeka.reco.collaborative.recommender.MovieLensDelphi;

public class MovieLensRMSESample {

    
    public static void main(String[] args) throws Exception {
    	
    	int testSize = Integer.parseInt(args[0]);
    	
    	MovieLensDataset ds = MovieLensData.createDataset(testSize);

    	// Create an instance of our recommender
    	MovieLensDelphi delphi = new MovieLensDelphi(ds);
    	        
    	// Create an instance of the RMSE estimator
    	RMSEEstimator rmseEstimator = new RMSEEstimator();
    	        
    	// Calculate the RMSE
    	// rmseEstimator.calculateRMSE(delphi);

    	// Compare RMSEs
    	for (int i=0; i<25; i++) {
    		delphi.setSimilarityThreshold(0.05d+i*0.01d);
    		rmseEstimator.compareRMSEs(delphi);
    	}
    }
}
