package org.yooreeka.algos.clustering.hierarchical;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.yooreeka.algos.clustering.model.Cluster;
import org.yooreeka.algos.clustering.model.DataPoint;


/**
 * Set of clusters.
 */
public class ClusterSet {

    private Set<Cluster> allClusters = new HashSet<Cluster>();
    
    public Cluster findClusterByElement(DataPoint e) {
        Cluster cluster = null;
        for(Cluster c : allClusters) {
            if( c.contains(e) ) {
                cluster = c;
                break;
            }
        }
        return cluster;
    }

    public List<Cluster> getAllClusters() {
        return new ArrayList<Cluster>(allClusters);
    }
    
    public boolean add(Cluster c) {
        return allClusters.add(c);
    }
    
    public boolean remove(Cluster c) {
        return allClusters.remove(c);
    }
    
    public int size() {
        return allClusters.size();
    }
    
//    public ClusterSet copy() {
//        ClusterSet clusterSet = new ClusterSet();
//        for(Cluster c : this.allClusters ) {
//            Cluster clusterCopy = c.copy();
//            clusterSet.add(clusterCopy);
//        }
//        return clusterSet;
//    }
}
