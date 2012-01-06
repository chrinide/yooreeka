package org.yooreeka.reco.collaborative.evaluation;

import java.util.List;

import org.yooreeka.reco.collaborative.model.Rating;

/**
 * Interface to access previously generated evaluation data.  
 */
public interface EvaluationDataProvider {
    List<Rating> loadTrainingRatings(int testSize, int testSequence);
    List<Rating> loadTestRatings(int testSize, int testSequence);
}
