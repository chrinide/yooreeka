package iweb2.ch6.evaluation;

public abstract class StatisticalTest {

	
	private double threshold=0.0;
	private String opSymbol;
	
	protected double statistic=0.0;
	protected boolean useAbsoluteValue=false;
	
	public StatisticalTest() {
		super();
	}
	
    public abstract void evaluate();

    protected abstract void calculate();

    protected void printResult() {
        
    	print("________________________________________________________");
        
        print("The two classifiers are different: " + String.valueOf(isDifferent()).toUpperCase());

        print("The statistic value is " + statistic + "which is "+ opSymbol +threshold);
        
    }
    
    protected boolean isDifferent() {
    	
    	if (useAbsoluteValue) {
    		this.statistic = Math.abs(statistic);
    	}
    	
        if( this.statistic > threshold) {
        	opSymbol = " > ";
            return true;
        } else {
        	opSymbol = " <= ";
            return false;
        }
    }
    
    protected void print(String val) {
		System.out.print("      ");
		System.out.println(val);
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

}