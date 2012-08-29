/**
 * 
 */
package org.yooreeka.util.parsing.common;

/**
 * @author bmarmanis
 *
 */
public abstract class AbstractDocument {

	abstract public String getDocumentId();

	abstract public byte[] getDocumentContent();

	abstract public String getDocumentURL();
}
