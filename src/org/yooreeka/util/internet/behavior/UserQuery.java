package org.yooreeka.util.internet.behavior;

import java.io.IOException;
import java.util.Arrays;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;

/**
 * This is a class that encapsulates a personalized query
 * 
 * @author babis
 *
 */
public class UserQuery {

	private String uid;
	private String queryString;
	private String[] queryTerms;
	private Query query;
	
	public UserQuery(String uid, String q) throws IOException {
		
		setUid(uid);
		setQueryString(q);
		
		PhraseQuery query = new PhraseQuery();	
		query.add(new Term("content",q));
		
		Term[] terms = query.getTerms(); 
		queryTerms = new String[terms.length];
		
		for (int i=0; i < terms.length; i++) {
			
			queryTerms[i] = terms[i].text();
		}
	}
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the query
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param query the query to set
	 */
	public void setQueryString(String query) {
		this.queryString = query;
	}
	
	public String getName() {
		return UserQuery.class.getCanonicalName();
	}

	public UserQuery getValue() {
		
		return this;
	}

	/**
	 * @return the queryTerms
	 */
	public String[] getQueryTerms() {
		return queryTerms;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((queryString == null) ? 0 : queryString.hashCode());
        result = prime * result + Arrays.hashCode(queryTerms);
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserQuery other = (UserQuery) obj;
        if (queryString == null) {
            if (other.queryString != null)
                return false;
        } else if (!queryString.equals(other.queryString))
            return false;
        if (!Arrays.equals(queryTerms, other.queryTerms))
            return false;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        return true;
    }

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

}
