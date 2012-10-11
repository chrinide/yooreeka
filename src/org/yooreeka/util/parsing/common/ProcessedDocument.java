package org.yooreeka.util.parsing.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.mozilla.universalchardet.UniversalDetector;
import org.yooreeka.util.P;
import org.yooreeka.util.internet.crawling.model.Outlink;

/**
 * Represents Processed document with attributes that we are interested in.
 */
public class ProcessedDocument implements AbstractDocument {
    
	public static final String TYPE_TEXT = "text/plain";
    public static final String TYPE_HTML = "text/html";
    public static final String TYPE_MSWORD = "application/msword";
    
    /*
     * Unique document id.
     */
    private String documentId;
    
    /*
     * All document outlinks (links that document has to other documents).
     */
    private List<Outlink> outlinks = new ArrayList<Outlink>();
    
    /*
     * URL that was used to retrieve the document.
     */
    private String documentURL;
    
    /*
     * Document title. 
     */
    private String title;
    
    /*
     * Processed document content. In case of HTML doc it can be HTML
     * with only relevant tags (<P>, <B>,..) preserved.
     */
    private String content;
    
    /*
     * Text extracted from the document with all formatting removed.
     */
    private String text;
    
    /*
     * Document type.
     */
    private String documentType;
    
    public ProcessedDocument() {
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    public String getDocumentURL() {
        return documentURL;
    }
   
    public List<Outlink> getOutlinks() {
        return outlinks;
    }

    public void setOutlinks(List<Outlink> outlinks) {
        this.outlinks = outlinks;
    }

    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }

    public void setDocumentTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }

    public String getDocumentTitle() {
        return this.title;
    }

    public void setDocumentId(String docId) {
        this.documentId = docId;
    }
    
    public String getDocumentId() {
        return documentId;
    }
    
    public String getDocumentType() {
        return documentType;
    }
    
    public void setDocumentType(String docType) {
        this.documentType = docType;
    }
    
    @Override
	public String toString() {
        return "[docId: " + documentId +
                ", type: " + documentType +
                ", url: " + documentURL + 
                "]"; 
    }

	@Override
	public String getContentType() {
		return getDocumentType();
	}

	@Override
	public String getContentCharset() {
		byte[] buf = new byte[4096];
	    
	    ByteArrayInputStream fis = new ByteArrayInputStream(getContent().getBytes());

	    // (1)
	    UniversalDetector detector = new UniversalDetector(null);

	    // (2)
	    int nread;
	    try {
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
			  detector.handleData(buf, 0, nread);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // (3)
	    detector.dataEnd();

	    // (4)
	    String encoding = detector.getDetectedCharset();
	    if (encoding != null) {
	      P.println("Detected encoding = " + encoding);
	    } else {
	      P.println("No encoding detected.");
	    }

	    // (5)
	    detector.reset();
		return encoding;
	}

	@Override
	public byte[] getDocumentContent() {
		return getContent().getBytes(Charset.forName(getContentCharset()));
	}
}
