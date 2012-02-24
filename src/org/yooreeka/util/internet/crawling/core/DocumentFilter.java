package org.yooreeka.util.internet.crawling.core;

import org.yooreeka.util.internet.crawling.model.FetchedDocument;


public class DocumentFilter {

    /*
     * Supposed to detect if we've already processed document with the same
     * content through some other url.
     */
    public boolean duplicateContentExists(FetchedDocument doc) {
        return false;
    }
}
