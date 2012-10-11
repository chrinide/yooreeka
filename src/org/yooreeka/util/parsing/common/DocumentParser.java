package org.yooreeka.util.parsing.common;


/**
 * Interface for parsing document that was retrieved/fetched during
 * collection phase.  
 */
public interface DocumentParser {

	public ProcessedDocument parse(AbstractDocument doc) throws DocumentParserException; 

	public DataEntry getDataEntry(int i);
	
}
