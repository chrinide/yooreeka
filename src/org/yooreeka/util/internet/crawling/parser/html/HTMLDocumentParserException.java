package org.yooreeka.util.internet.crawling.parser.html;

import org.yooreeka.util.internet.crawling.parser.common.DocumentParserException;


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
