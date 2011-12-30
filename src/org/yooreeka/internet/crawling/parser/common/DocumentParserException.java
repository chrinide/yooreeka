package org.yooreeka.internet.crawling.parser.common;

public class DocumentParserException extends Exception {

	//Distinct SVUID for the org.yooreeka.* classes
	private static final long serialVersionUID = 4938858042489090351L;

	public DocumentParserException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public DocumentParserException(String msg) {
        super(msg);
    }
}
