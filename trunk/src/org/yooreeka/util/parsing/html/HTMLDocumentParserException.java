package org.yooreeka.util.parsing.html;

import org.yooreeka.util.parsing.common.DocumentParserException;


public class HTMLDocumentParserException extends DocumentParserException {


    /**
	 * 	Distinct SVUID for the org.yooreeka.* classes
	 */
	private static final long serialVersionUID = 3397930132653232196L;

	public HTMLDocumentParserException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public HTMLDocumentParserException(String msg) {
        super(msg);
    }
}
