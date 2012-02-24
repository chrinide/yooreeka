package org.yooreeka.util.internet.crawling.parser.common;

import org.yooreeka.util.internet.crawling.model.FetchedDocument;
import org.yooreeka.util.internet.crawling.model.ProcessedDocument;

/**
 * Interface for parsing document that was retrieved/fetched during
 * collection phase.  
 */
public interface DocumentParser {
    public ProcessedDocument parse(FetchedDocument doc) 
        throws DocumentParserException;
}
