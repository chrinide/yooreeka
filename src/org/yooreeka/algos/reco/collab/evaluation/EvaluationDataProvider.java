package org.yooreeka.algos.reco.collab.evaluation;

import java.util.List;

import org.yooreeka.algos.reco.collab.model.Rating;

/**
 * Interface to access previously generated evaluation data.  
 */
public interface EvaluationDataProvider {
    List<Rating> loadTrainingRatings(int testSize, int testSequence);
    List<Rating> loadTestRatings(int testSize, int testSequence);
}
