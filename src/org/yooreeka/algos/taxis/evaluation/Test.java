package org.yooreeka.algos.taxis.evaluation;

public abstract class Test {

	public Test() {
		super();
	}
	
	private String statisticSymbol;
	protected double statisticValue;
    private double threshold;

    public abstract void evaluate();

    protected abstract void calculate();

    protected void printResult() {
        
    	boolean btmp=isDifferent(statisticValue,threshold);
    	
        String tmp;
        
        if (btmp) {
        	tmp=" > ";
        } else {
        	tmp=" <= ";
        }

        print("________________________________________________________");
        
        print(statisticSymbol+ " value is " + statisticValue + "which is "+ tmp + threshold);
        
        print("The two classifiers are different: " + String.valueOf(btmp).toUpperCase());
    }
    
    protected boolean isDifferent(double statistic, double threshold) {
        if( statistic > threshold) {
            return true;
        }
        else {
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

	protected void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public String getStatisticSymbol() {
		return statisticSymbol;
	}

	protected void setStatisticSymbol(String statisticSymbol) {
		this.statisticSymbol = statisticSymbol;
	}

	public double getStatisticValue() {
		return statisticValue;
	}

	protected void setStatisticValue(double statisticValue) {
		this.statisticValue = statisticValue;
	}

}