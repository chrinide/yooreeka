/**
 * 
 */
package org.yooreeka.util.parsing.common;

/**
 * @author bmarmanis
 *
 */
public interface AbstractDocument {

	public String getDocumentId();

	public byte[] getDocumentContent();

	public String getDocumentURL();

	public String getContentType();
	
	public String getContentCharset();
}
