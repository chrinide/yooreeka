package iweb2.ch3.shell;

import iweb2.ch3.collaborative.data.MovieLensData;
import iweb2.ch3.collaborative.data.MovieLensDataset;
import iweb2.ch3.collaborative.evaluation.RMSEEstimator;
import iweb2.ch3.collaborative.recommender.MovieLensDelphi;


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
    	for (int i=0; i<10; i++) {
    		delphi.setSimilarityThreshold(0.1d+i*0.05d);
    		rmseEstimator.compareRMSEs(delphi);
    	}
    }
}
