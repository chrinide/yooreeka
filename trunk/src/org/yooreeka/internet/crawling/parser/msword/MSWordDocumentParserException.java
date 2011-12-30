package org.yooreeka.internet.crawling.parser.msword;

import org.yooreeka.internet.crawling.parser.common.DocumentParserException;

public class MSWordDocumentParserException extends DocumentParserException {

    /**
	 * Distinct SVUID for the org.yooreeka.* classes
	 */
	private static final long serialVersionUID = -3005082246637918030L;

	public MSWordDocumentParserException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public MSWordDocumentParserException(String msg) {
        super(msg);
    }
}
