/**
 * 
 */
package org.yooreeka.util.parsing.csv;

import java.util.ArrayList;

import org.yooreeka.util.parsing.common.ProcessedDocument;

/**
 * @author babis
 *
 */
public class CSVDocument extends ProcessedDocument {

	ArrayList<CSVEntry> csvData;
	
	public CSVDocument(ArrayList<CSVEntry> data) {
		csvData = data;
	}
	
}
