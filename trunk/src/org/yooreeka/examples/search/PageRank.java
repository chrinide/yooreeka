package org.yooreeka.examples.search;

import org.yooreeka.algos.search.ranking.PageRankMatrixBuilder;
import org.yooreeka.algos.search.ranking.PageRankMatrixH;
import org.yooreeka.algos.search.ranking.Rank;
import org.yooreeka.util.internet.crawling.core.CrawlData;

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
