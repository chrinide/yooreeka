/**
 * 
 */
package org.yooreeka.algos.search.lucene.analyzer;

import java.util.HashMap;

/**
 * @author haris
 *
 */
public class TextDocumentTerms {
	
	HashMap<String,Integer> tf;
	
	public TextDocumentTerms(String text) {
		
		String[] terms = text.split("\\s");
		
		tf = new HashMap<String,Integer>(terms.length);
		
		for (String s : terms) {
			
			Integer f = tf.get(s); 
			
			if (f == null) {
				// This string has not been added yet
				tf.put(s, Integer.valueOf(1));
			} else {
				tf.put(s, ++f);
			}
		}
	}

	public String[] getTerms() {
		
		String[] terms = new String[tf.size()];
		
		int i=0;
		
		for (String s : tf.keySet()) {
			terms[i] = s;
			i++;
		}
		return terms;
	}

	public HashMap<String, Integer> getTf() {
		return tf;
	}
}
