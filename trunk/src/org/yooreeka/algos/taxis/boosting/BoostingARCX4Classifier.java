package org.yooreeka.algos.taxis.boosting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.yooreeka.algos.taxis.core.TrainingSet;
import org.yooreeka.algos.taxis.core.intf.Classifier;
import org.yooreeka.algos.taxis.core.intf.Concept;
import org.yooreeka.algos.taxis.core.intf.Instance;
import org.yooreeka.algos.taxis.ensemble.ClassifierEnsemble;
import org.yooreeka.algos.taxis.ensemble.ConceptMajorityVoter;

public abstract class BoostingARCX4Classifier extends ClassifierEnsemble {

    private TrainingSet originalTSet;
    
    private int classifierPopulation = 2;
    
    public BoostingARCX4Classifier(String name, TrainingSet tSet) {
        super(name);
        this.originalTSet = tSet;
    }
    
    @Override
	public Concept classify(Instance instance) {
    	
        ConceptMajorityVoter voter = new ConceptMajorityVoter(instance);
        
        for( Classifier baseClassifier : baseClassifiers ) {
            
            Concept c = baseClassifier.classify(instance);
            
            voter.addVote(c);
        }
        
        if( verbose ) {
            voter.print();
        }
        
        return voter.getWinner();
    }

    public abstract Classifier getClassifierForTraining(TrainingSet set);    
    
    @Override
	public boolean train() {

        baseClassifiers = new ArrayList<Classifier>();
        
        int size = originalTSet.getSize();

        /*
         * Weights that define sample selection
         */
        double[] w = new double[size];

        /*
         * Number of times instance was misclassified by classifiers that are 
         * currently in ensemble.
         */
        int[] m = new int[size];
        
        double w0 = 1.0 / size;
        
        Arrays.fill(w, w0);
        Arrays.fill(m, 0);
        
        for(int i = 0; i < classifierPopulation; i++) {
            if( verbose ) {
                System.out.println("Instance weights: " + Arrays.toString(w));
                System.out.println("Instance misclassifications: " + Arrays.toString(m));
            }
            
            TrainingSet tSet = buildTSet(originalTSet, w);
            
            Classifier baseClassifier = getClassifierForTraining(tSet);
            
            baseClassifier.train();
            
            updateWeights(originalTSet, w, m, baseClassifier);
            
            baseClassifiers.add(baseClassifier);
        }
        
        return true;
    }
    
    public TrainingSet buildTSet(TrainingSet tSet, double[] w) {
        
        WeightBasedRandom wRnd = new WeightBasedRandom(w);
        
        int n = w.length;
        
        Instance[] sample = new Instance[n];
        
        Map<Integer, Instance> instances = tSet.getInstances();
        
        for(int i = 0; i < n; i++) {
            int instanceIndex = wRnd.nextInt();
            sample[i] = instances.get(instanceIndex);
        }
        
        return new TrainingSet(sample);
    }
    
    public void updateWeights(TrainingSet tSet, double[] w, int[] m, 
            Classifier baseClassifier) {

        int n = w.length;
        
        // update misclassification counts with results from latest classifier
        for(int i = 0; i < n; i++) {
            Instance instance = tSet.getInstance(i);
            Concept actualConcept = baseClassifier.classify(instance);
            Concept expectedConcept = instance.getConcept();
            if( actualConcept == null || 
               !(actualConcept.getName().equals(expectedConcept.getName()) ) ) {
                m[i]++;
            }
        }

        // update weights
        double sum = 0.0;
        for( int i = 0; i < n; i++) {
            sum += ( 1.0 + Math.pow(m[i], 4) );
        }
        
        for( int i = 0; i < n; i++) {
            w[i] = ( 1.0 + Math.pow(m[i], 4) ) / sum; 
        }
        
    }

    public boolean isVerbose() {
        return verbose;
    }

    @Override
	public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

	/**
	 * @return the classifierPopulation
	 */
	public int getClassifierPopulation() {
		return classifierPopulation;
	}

	/**
	 * @param classifierPopulation the classifierPopulation to set
	 */
	public void setClassifierPopulation(int classifierPopulation) {
		this.classifierPopulation = classifierPopulation;
	}
}
