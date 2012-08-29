/**
 * 
 */
package org.yooreeka.util.parsing.common;

/**
 * @author bmarmanis
 *
 */
public class DataField {

	private String name;
	private DataType dataType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public DataField(String name, DataType dataType) {
		this.name = name;
		this.dataType = dataType;
	}
	
	public boolean validate(String s) {
		boolean isValid = true;
		
		
		return isValid;
	}
}
