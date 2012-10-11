/**
 * 
 */
package org.yooreeka.util.parsing.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.yooreeka.util.parsing.common.AbstractDocument;
import org.yooreeka.util.parsing.common.DataEntry;
import org.yooreeka.util.parsing.common.DocumentParser;
import org.yooreeka.util.parsing.common.DocumentParserException;
import org.yooreeka.util.parsing.common.ProcessedDocument;

/**
 * @author bmarmanis
 * 
 */
public class CSVParser implements DocumentParser {

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
	public CSVDocument parse(BufferedReader bR) throws IOException {

		long linesParsed=0;
		
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
				linesParsed++;
			}
		}
		
		CSVDocument doc = new CSVDocument(csvData);
		return doc;
	}

	@Override
	public DataEntry getDataEntry(int i) {
		return csvData.get(i);
	}

	@Override
	public ProcessedDocument parse(AbstractDocument abstractDocument)
			throws DocumentParserException {
		ProcessedDocument processedDocument=null;
		String content = new String(abstractDocument.getDocumentContent(),Charset.forName(abstractDocument.getContentCharset()));
		BufferedReader reader = new BufferedReader(new StringReader(content));  
		try {
			abstractDocument = parse(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return processedDocument;
	}
}
