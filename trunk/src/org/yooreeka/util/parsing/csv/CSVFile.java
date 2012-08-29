/**
 * 
 */
package org.yooreeka.util.parsing.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.yooreeka.util.parsing.common.DataField;
import org.yooreeka.util.parsing.common.DataType;

/**
 * 
 * 
 * @author bmarmanis
 * 
 */
public class CSVFile {

	private File file;
	private ArrayList<CSVEntry> csvData = new ArrayList<CSVEntry>();

	// By default a CSV file has Headers
	private boolean hasHeaders;

	public CSVFile(String fileName, boolean hasHeaders, CSVSchema schema) {

		this.hasHeaders = hasHeaders;

		file = new File(fileName);
	}

	public void read() throws IOException {
		
		FileReader fReader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(fReader);

		CSVParser csvParser = new CSVParser();
		csvParser.parse(bReader);

		// Sanity check
		csvParser.print(10);

		bReader.close();
	}

	public CSVEntry getHeaders() {
		
		CSVEntry e = null;
		
		if (hasHeaders) {
			e = csvData.get(0);	
		} 
		
		return e;
	}

	public boolean hasHeaders() {
		return hasHeaders;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		CSVSchema s = new CSVSchema();
		
		DataField f1 = new DataField("Customer Id", DataType.LONG);		
		s.addColumn(f1);
		
		DataField f2 = new DataField("Customer Status",DataType.STRING);
		s.addColumn(f2);
		
		DataField f3 = new DataField("Total Order  amt, USD",DataType.DOUBLE);
		s.addColumn(f3);
		
		DataField f4 = new DataField("Content Id",DataType.STRING);
		s.addColumn(f4);
		
		DataField f5 = new DataField("Title/Journal Id",DataType.LONG);
		s.addColumn(f5);
		
		DataField f6 = new DataField("Title/Journal Name",DataType.STRING);
		s.addColumn(f6);
		
		DataField f7 = new DataField("Title/Journal Publisher",DataType.STRING);
		s.addColumn(f7);

		
//		s.addColumn(DataType.STRING_DATA_TYPE);
//		s.addColumn(DataType.DOUBLE_DATA_TYPE);
//		s.addColumn(DataType.STRING_DATA_TYPE);
		
		CSVFile f = new CSVFile(args[0], true, s);
		f.read();
	}
}
