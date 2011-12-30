package org.yooreeka.internet.crawling.transport.common;

import org.yooreeka.internet.crawling.model.FetchedDocument;


public interface Transport {
    public FetchedDocument fetch(String url) throws TransportException;
    public void init();
    public void clear();
    public boolean pauseRequired();
}
