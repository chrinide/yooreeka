package iweb2.ch6.evaluation;

import iweb2.ch6.usecase.credit.util.ClassifierResults;

public class Diff2PropTest extends StatisticalTest {

    private ClassifierResults c1;
    private ClassifierResults c2;

    /**
     * Confidence interval: 0.05
     * Null hypothesis - classifiers are the same
     * Null hypothesis is rejected if |z| > 1.96
     */ 
    public Diff2PropTest(ClassifierResults c1, ClassifierResults c2) {
    	
    	this.setThreshold(1.96d);
    	this.useAbsoluteValue=true;
    	
        this.c1 = c1;
        this.c2 = c2;
        
        calculate();
    }
    
    @Override
	protected void calculate() {
    	
        double n = c1.getN();
        double p = 0.5 * (c1.getAccuracy() + c2.getAccuracy());
        double a = c1.getAccuracy() - c2.getAccuracy();
        double b = ( 2.0 * p * ( 1 - p ) ) / n;
        statistic = a / Math.sqrt(b);
        
    }
    
    @Override
	public void evaluate() {
    	
    	print("_____________________________________________________");
        print("Evaluating classifiers " + c1.getClassifierId() + 
                " and " + c2.getClassifierId() + ":");

        print("_____________________________________________________");
        print(c1.getClassifierId() + " accuracy: " + c1.getAccuracy());
        print(c2.getClassifierId() + " accuracy: " + c2.getAccuracy());
        print("_____________________________________________________");
        
        print("Confidence Interval             : 0.05");
        print("Statistic threshold (Std Normal): 1.96");
        
        printResult();
    }
}
