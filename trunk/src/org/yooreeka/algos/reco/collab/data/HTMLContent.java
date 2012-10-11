package org.yooreeka.algos.reco.collab.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.yooreeka.algos.reco.collab.model.Content;
import org.yooreeka.util.parsing.html.HTMLDocumentParser;
import org.yooreeka.util.parsing.html.HTMLDocumentParserException;

public class HTMLContent extends Content {

    /**
	 * SVUID
	 */
	private static final long serialVersionUID = -354667863913509004L;

    public HTMLContent(String id, File htmlDocFile) {
        super(id, extractContentFromHtmlDoc(htmlDocFile)); 
    }

    public HTMLContent(String id, File htmlDocFile, int topNTerms) {
        super(id, extractContentFromHtmlDoc(htmlDocFile), topNTerms); 
    }
    
	public HTMLContent(String id, String htmlDocFilename) {
        super(id, extractContentFromHtmlDoc(new File(htmlDocFilename))); 
    }

	public HTMLContent(String id, String htmlDocFilename, int topNTerms) {
        super(id, extractContentFromHtmlDoc(new File(htmlDocFilename)), topNTerms); 
    }

	
	
    private static String extractContentFromHtmlDoc(File htmlFile) {
        
    	String htmlText=null;
    	FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(htmlFile);
            Reader reader = new InputStreamReader(new BufferedInputStream(fis));
            HTMLDocumentParser htmlParser = new HTMLDocumentParser(reader);
            
            htmlText = htmlParser.getHtmlDoc().getText();
        
        } catch(IOException e) {
            
        	throw new RuntimeException(e);
            
        } catch (HTMLDocumentParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if( fis != null ) {
                try {
                    fis.close();
                }
                catch(IOException e) { 
                    e.printStackTrace();
                }
            }
        }
        return htmlText;
    }
    
}
