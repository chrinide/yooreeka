package org.yooreeka.examples.fraud.data;

import java.util.List;

import org.yooreeka.config.YooreekaConfigurator;
import org.yooreeka.examples.fraud.util.FraudDataUtils;


public class TransactionLoader {

    public static final String TRAINING_TXNS_FILE = 
    	YooreekaConfigurator.getHome()+"/data/ch05/fraud/training-txns.txt";
    
    public static final String TEST_TXNS_FILE = 
    	YooreekaConfigurator.getHome()+"/data/ch05/fraud/test-txns.txt";
    
    public static List<Transaction> loadTxns(String filename) {
        return FraudDataUtils.loadTransactions(filename);
    }
    
    public static TransactionDataset loadTrainingDataset() {
        List<Transaction> allTxns = loadTxns(TRAINING_TXNS_FILE);
        return new TransactionDataset(allTxns);
    }

    public static TransactionDataset loadTestDataset() {
        List<Transaction> allTxns = loadTxns(TEST_TXNS_FILE);
        return new TransactionDataset(allTxns);
    }
}
