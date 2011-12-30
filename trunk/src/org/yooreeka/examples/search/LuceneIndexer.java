package org.yooreeka.examples.search;

import java.io.File;

import org.yooreeka.internet.crawling.core.CrawlData;
import org.yooreeka.internet.crawling.core.CrawlDataProcessor;
import org.yooreeka.internet.crawling.util.FileUtils;
import org.yooreeka.search.lucene.LuceneIndexBuilder;

public class LuceneIndexer {
	
	private String baseDir;
	
	private String luceneIndexDir;
	
	public LuceneIndexer(String dir) {
		
		baseDir = dir;
		luceneIndexDir = baseDir+System.getProperty("file.separator")+"lucene-index";
	}
	
    public void run() {
    
        // load existing data
        CrawlData crawlData = new CrawlData(baseDir);
        crawlData.init(); 
        
        File luceneIndexRootDir = new File(getLuceneDir());
        
        // Delete the index directory, if it exists
        FileUtils.deleteDir(luceneIndexRootDir);
        luceneIndexRootDir.mkdirs();
        
        CrawlDataProcessor luceneIndexBuilder = new LuceneIndexBuilder(luceneIndexRootDir, crawlData);
        
        System.out.print("Starting the indexing ... ");
        
        luceneIndexBuilder.run();

        System.out.println("Indexing completed! \n");        
    }
    
    public String getLuceneDir() {
    	
    	return luceneIndexDir;
    }
    
}
