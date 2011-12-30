/**
 * 
 */
package org.yooreeka.examples.search;

import org.yooreeka.search.ranking.DocRankMatrixBuilder;
import org.yooreeka.search.ranking.PageRankMatrixH;
import org.yooreeka.search.ranking.Rank;


/**
 * @author babis
 *
 */
public class DocRank extends Rank {

    DocRankMatrixBuilder docRankBuilder;
        
    public DocRank(String luceneIndexDir, int termsToKeep) {
        docRankBuilder = new DocRankMatrixBuilder(luceneIndexDir);
        docRankBuilder.setTermsToKeep(termsToKeep);
        docRankBuilder.run();
    }
    
    @Override
	public PageRankMatrixH getH() {
        return docRankBuilder.getH();
    }
}
