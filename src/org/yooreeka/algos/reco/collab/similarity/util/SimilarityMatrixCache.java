package org.yooreeka.algos.reco.collab.similarity.util;

import java.io.File;

import org.yooreeka.algos.reco.collab.cache.FileStore;
import org.yooreeka.algos.reco.collab.cache.Store;
import org.yooreeka.algos.reco.collab.similarity.naive.SimilarityMatrix;

public class SimilarityMatrixCache {

    private Store store;
    private String location;
    
    public SimilarityMatrixCache(File location) {
        store = new FileStore(location);
        this.location = location.getAbsolutePath();
    }
    
    public String getLocation() {
        return location;
    }
    
    public void put(String id, SimilarityMatrix similarityMatrix) {
        if( store.exists(id) ) {
            store.remove(id);
        }
        store.put(id, similarityMatrix);
    }
    
    public SimilarityMatrix get(String id) {
        SimilarityMatrix s = null;
        if( store.exists(id) ) {
            s = (SimilarityMatrix)store.get(id);
        }
        return s;
    }
    
    public void remove(String id) {
        store.remove(id);
    }
}
