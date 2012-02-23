package org.yooreeka.examples.classification.fraud.credit;

import org.yooreeka.algos.taxis.core.TrainingSet;
import org.yooreeka.algos.taxis.core.intf.Concept;
import org.yooreeka.algos.taxis.ensemble.ClassifierEnsemble;
import org.yooreeka.examples.classification.fraud.credit.data.UserDataset;
import org.yooreeka.examples.classification.fraud.credit.data.users.User;
import org.yooreeka.examples.classification.fraud.credit.util.BootstrapTrainingSetBuilder;
import org.yooreeka.examples.classification.fraud.credit.util.UserInstanceBuilder;


public class BaggingCreditClassifier extends ClassifierEnsemble {

    private UserInstanceBuilder instanceBuilder;
    private BootstrapTrainingSetBuilder bootstrapTSetBuilder;
    
    public BaggingCreditClassifier(UserDataset ds) {

        super(BaggingCreditClassifier.class.getSimpleName());
        
        /* Creating instance builder for this classifier */
        instanceBuilder = new UserInstanceBuilder(false);
        

        /* Creating original training set that will be used to generate 
         * bootstrap sets */
        TrainingSet originalTSet = instanceBuilder.createTrainingSet(ds); 
        
        bootstrapTSetBuilder = new BootstrapTrainingSetBuilder(originalTSet);          
    }
    
    public TrainingSet getBootstrapSet() {
    	return bootstrapTSetBuilder.buildBootstrapSet();
    }
        
    public UserInstanceBuilder getInstanceBuilder() {
        return instanceBuilder;
    }
    
    public Concept classify(User user) {

        if (verbose) {
            System.out.println("User:\n  >> "+user.toString());
        }
        
        return classify(instanceBuilder.createInstance(user));
    }
    
}
