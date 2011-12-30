package org.yooreeka.examples.search;

import org.yooreeka.internet.crawling.core.CrawlData;
import org.yooreeka.search.ranking.PageRankMatrixBuilder;
import org.yooreeka.search.ranking.PageRankMatrixH;
import org.yooreeka.search.ranking.Rank;

public class PageRank extends Rank {
    
	PageRankMatrixBuilder pageRankBuilder;
	
	public PageRank(CrawlData crawlData) {
        pageRankBuilder = new PageRankMatrixBuilder(crawlData);
        pageRankBuilder.run();     
	}
	
	@Override
	public PageRankMatrixH getH() {
		return pageRankBuilder.getH();
	}
	
}
