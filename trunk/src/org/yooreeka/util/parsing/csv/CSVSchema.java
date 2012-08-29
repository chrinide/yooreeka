/**
 * 
 */
package org.yooreeka.util.parsing.csv;

import java.util.HashMap;

import org.yooreeka.util.parsing.common.DataField;

/**
 * @author bmarmanis
 *
 */
public class CSVSchema {
	
	private int column=0;
	private HashMap<Integer, DataField> columnMap;

	public CSVSchema() {
		columnMap = new HashMap<>();
	}
	
	public void addColumn(DataField field) {
		columnMap.put(column, field);
		column++;
	}
	
	public int getNumberOfColumns() {
		return columnMap.size();
	}
}
