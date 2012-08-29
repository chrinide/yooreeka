/**
 * 
 */
package org.yooreeka.util.parsing.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.yooreeka.util.internet.crawling.model.ProcessedDocument;
import org.yooreeka.util.parsing.common.AbstractDocument;
import org.yooreeka.util.parsing.common.DataEntry;
import org.yooreeka.util.parsing.common.DocumentParser;
import org.yooreeka.util.parsing.common.DocumentParserException;

/**
 * @author bmarmanis
 * 
 */
public class CSVParser extends DocumentParser {

	/**
	 * 
	 */
	private ArrayList<CSVEntry> csvData;

	/**
	 * 
	 */
	public CSVParser() {
		//NOTHING YET
	}
	
	/**
	 * 
	 * @param bR
	 * @return
	 * @throws IOException
	 */
	public ArrayList<CSVEntry> parse(BufferedReader bR) throws IOException {

		csvData = new ArrayList<CSVEntry>();

		boolean hasMoreLines = true;
		String line;
		
		while (hasMoreLines) {

			line = bR.readLine();

			if (line == null) {

				hasMoreLines = false;

			} else {

				CSVEntry csvEntry = new CSVEntry(line);
				csvData.add(csvEntry);
			}
		}
		
		return csvData;
	}

	@Override
	public ProcessedDocument parse(AbstractDocument doc)
			throws DocumentParserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataEntry getDataEntry(int i) {
		return csvData.get(i);
	}

}
