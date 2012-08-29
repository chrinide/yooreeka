package org.yooreeka.util.parsing.common;

import org.yooreeka.util.internet.crawling.model.ProcessedDocument;

/**
 * Interface for parsing document that was retrieved/fetched during
 * collection phase.  
 */
abstract public class DocumentParser {

	abstract public ProcessedDocument parse(AbstractDocument doc) throws DocumentParserException; 

	abstract public DataEntry getDataEntry(int i);
	
	public void print(int n) {
		for (int i=0; i<n; i++) {
			System.out.println(getDataEntry(i).toString());
		}
	}	
}
