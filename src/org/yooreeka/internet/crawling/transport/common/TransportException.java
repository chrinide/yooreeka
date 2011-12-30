package org.yooreeka.internet.crawling.transport.common;


public class TransportException extends Exception {

    /**
	 * Distinct SVUID for the org.yooreeka classes
	 */
	private static final long serialVersionUID = -2821101482190551697L;

	public TransportException(String message, Throwable t) {
        super(message, t);
    }
    
    public TransportException(String message) {
        super(message);
    }
}
