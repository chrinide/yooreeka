package org.yooreeka.util.internet.crawling.model;

import java.nio.charset.Charset;
import java.util.Map;

import org.yooreeka.util.P;
import org.yooreeka.util.parsing.common.AbstractDocument;

/**
 * Collection of raw (unprocessed) data about crawled/fetched document.
 */ 
public class FetchedDocument implements AbstractDocument {
    
    /*
     * Document id that was assigned by the FetcherModule.
     */
    private String documentId;
    
    /*
     * Document URL. URL that was used to fetch the document.  
     */
    private String url;
    
    /*
     * MIME content type that was derived from transport protocol (HTTP headers), 
     * document content or document URL.  
     */
    private String contentType;

    /*
     * Character encoding that was derived from transport protocol (HTTP headers),
     * document content.
     */
    private String contentCharset;

    /*
     * Raw document content.
     */
    private byte[] documentContent;
    
    
    /*
     * Various optional meta data about the document that was extracted from the 
     * protocol. 
     */
    private Map<String, String> documentMetadata;
    
    public FetchedDocument() {
    }
    
    public String getDocumentId() {
        return documentId;
    }
    
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    
    public void setDocumentURL(String url) {
        this.url = url;
    }
    
    
    public String getContentCharset() {
        return contentCharset;
    }

    public void setContentCharset(String contentCharset) {
        this.contentCharset = contentCharset;
    }

    public String getDocumentURL() {
        return url;
    }
    
    public String getContentType() {
        return contentType;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setDocumentContent(byte[] data) {
        this.documentContent = data;
    }
    public byte[] getDocumentContent() {
        return documentContent;
    }

    public long getContentLength() {
        return documentContent.length;
    }
    
    public Map<String, String> getDocumentMetadata() {
        return documentMetadata;
    }

    public void setDocumentMetadata(Map<String, String> metadata) {
        this.documentMetadata = metadata;
    }
    
    public void print() {
    	P.println("Document ID    : "+this.documentId);
    	P.println("Content URL    : "+this.url);
    	P.println("Content Type   : "+this.contentType);
    	P.println("Content Charset: "+this.contentCharset);
    	P.hline();
    	P.println("CONTENT\n"+new String(this.getDocumentContent(),Charset.forName(contentCharset)));
    	P.hline();
    }
}
