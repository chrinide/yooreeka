/**
 * 
 */
package org.yooreeka.util.parsing.csv;

import org.yooreeka.util.parsing.common.DataEntry;

/**
 * @author bmarmanis
 *
 */
public class CSVEntry extends DataEntry {

	private String[] data;

	public CSVEntry(String csvLine) {
	
		data = csvLine.split(",");
	}
	
	public String[] getData() {
		return data;
	}
	
	@Override
	public DataEntry getDataEntry() {
		
		return this;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		for (String s: data) {
			sb.append(s).append(", ");
		}
		return sb.toString();
	}
}
