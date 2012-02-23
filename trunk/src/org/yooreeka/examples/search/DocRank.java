/**
 * 
 */
package org.yooreeka.examples.search;

import org.yooreeka.algos.search.ranking.DocRankMatrixBuilder;
import org.yooreeka.algos.search.ranking.PageRankMatrixH;
import org.yooreeka.algos.search.ranking.Rank;


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
