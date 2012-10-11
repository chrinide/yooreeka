package org.yooreeka.util.parsing.common;

import org.yooreeka.util.parsing.html.HTMLDocumentParser;
import org.yooreeka.util.parsing.msword.MSWordDocumentParser;

public class DocumentParserFactory {
    
    private static DocumentParserFactory instance = new DocumentParserFactory();
    
    public static DocumentParserFactory getInstance() {
        return instance;
    }
    
    private DocumentParserFactory() {
        // empty
    }
    
    /**
     * Returns an instance of the <code>DocumentParser</code> based 
     * on the document type.
     * 
     * @param type document type.
     * @return
     * @throws DocumentParserException
     */
    public DocumentParser getDocumentParser(String type) 
        throws DocumentParserException {
        if( ProcessedDocument.TYPE_HTML.equalsIgnoreCase(type) ) {
            return new HTMLDocumentParser();
        } else if( ProcessedDocument.TYPE_MSWORD.equalsIgnoreCase(type) ) {
            return new MSWordDocumentParser();
        } else {
            throw new DocumentParserException("Unsupported document type: '"+ type + "'.");            
        }
    }
}
