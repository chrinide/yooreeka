/*
 *   ________________________________________________________________________________________
 *   
 *   Y O O R E E K A
 *   A library for data mining, machine learning, soft computing, and mathematical analysis
 *   ________________________________________________________________________________________ 
 *    
 *   The Yooreeka project started with the code of the book "Algorithms of the Intelligent Web " 
 *   (Manning 2009). Although the term "Web" prevailed in the title, in essence, the algorithms 
 *   are valuable in any software application.
 *  
 *   Copyright (c) 2007-2009 Haralambos Marmanis & Dmitry Babenko
 *   Copyright (c) 2009-${year} Marmanis Group LLC and individual contributors as indicated by the @author tags.  
 * 
 *   Certain library functions depend on other Open Source software libraries, which are covered 
 *   by different license agreements. See the NOTICE file distributed with this work for additional 
 *   information regarding copyright ownership and licensing.
 * 
 *   Marmanis Group LLC licenses this file to You under the Apache License, Version 2.0 (the "License"); 
 *   you may not use this file except in compliance with the License.  
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software distributed under 
 *   the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 *   either express or implied. See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 */
package org.yooreeka.util.parsing.csv;

import java.io.Serializable;
import java.util.ArrayList;

import org.yooreeka.util.P;
import org.yooreeka.util.parsing.common.DataField;
import org.yooreeka.util.parsing.common.DataType;

/**
 * 
 * @author <a href="mailto:babis@marmanis.com">Babis Marmanis</a>
 * 
 */
public class CSVSchema implements Serializable {

	private static final long serialVersionUID = -8265277706414216835L;

	private String name;
	
	private ArrayList<DataField> fields;
	private DataField primaryKey;

	public CSVSchema() {
		fields = new ArrayList<DataField>();
	}

	public DataField getPrimaryKey() {
		return primaryKey;
	}
	
	public int getPrimaryKeyIndex() {
		int primaryKeyIndex=0;
		Object[] fs = fields.toArray();
		for (int i=0; i < fields.size(); i++) {
			DataField f = (DataField) fs[i];
			if (f.isPrimaryKey()) {
				primaryKeyIndex=i;
			}
		}
		return primaryKeyIndex;
	}
	
	public void addField(DataField field, boolean isPrimaryKey) {
		
		if (field.getDataType() == DataType.LONG) {
			fields.add(field);
			field.setAsPrimaryKey();
			primaryKey=field;
		} else {
			throw new IllegalArgumentException("The primary key can only be a long integer.");
		}
	}

	public void addField(DataField field) {
		
		fields.add(field);
	}

	/**
	 * The tacit assumption here is that the values of the fields in all CSV entries 
	 * are ordered in the same way that the schema fields are ordered, which must be true 
	 * for well defined data. However, you should remember that the schema ordering is "hardcoded",
	 * not inferred, in the present implementation.  
	 * 
	 * @param fieldName
	 * 
	 * @return the index of the <tt>DataField</tt> in the schema and therefore
	 * the proper index for retrieving the value of the field from any <tt>CSVEntry</tt>
	 * that conforms with the schema.
	 * 
	 */
	public int getIndex(String fieldName) {
		
		int index=-1;
		
		for (int i=0; i< fields.size(); i++) {
			DataField f = fields.get(i);
			if (f.getName().equalsIgnoreCase(fieldName)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public int getNumberOfFields() {
		return fields.size();
	}
			
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
