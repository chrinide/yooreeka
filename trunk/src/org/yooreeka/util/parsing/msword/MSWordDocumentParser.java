package org.yooreeka.util.parsing.msword;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.textmining.extraction.TextExtractor;
import org.textmining.extraction.word.WordTextExtractorFactory;
import org.yooreeka.util.parsing.common.AbstractDocument;
import org.yooreeka.util.parsing.common.DataEntry;
import org.yooreeka.util.parsing.common.DocumentParser;
import org.yooreeka.util.parsing.common.DocumentParserException;
import org.yooreeka.util.parsing.common.ProcessedDocument;

public class MSWordDocumentParser implements DocumentParser {

    ProcessedDocument wordDoc = new ProcessedDocument();

    public ProcessedDocument parse(AbstractDocument doc)
            throws DocumentParserException {

        wordDoc.setDocumentType(ProcessedDocument.TYPE_MSWORD);
        wordDoc.setDocumentId(doc.getDocumentId());
        wordDoc.setDocumentURL(doc.getDocumentURL());
        
        InputStream contentData = new ByteArrayInputStream(doc.getDocumentContent());
        WordTextExtractorFactory wteFactory = new WordTextExtractorFactory();        

        try {
            TextExtractor txtExtractor = wteFactory.textExtractor(contentData);
            String text = txtExtractor.getText();
            wordDoc.setText(text);
            // using the same value as text
            wordDoc.setContent(text);
            wordDoc.setDocumentTitle(getTitle(text));
        }
        catch(Exception e) {
            throw new MSWordDocumentParserException("MSWord Document parsing error: ", e);
        }
        return wordDoc;
    }
    
    /*
     * Finds the first non-empty line in the document.
     */
    private String getTitle(String text) throws IOException {
        if( text == null ) {
            return null;
        }
        String title = "";
        
        StringReader sr = new StringReader(text);
        BufferedReader r = new BufferedReader(sr);
        String line = null;
        while( (line = r.readLine()) != null ) {
            if( line.trim().length() > 0) {
                title = line.trim();
                break;
            }
        }
        
        return title;
    }

	@Override
	public DataEntry getDataEntry(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
