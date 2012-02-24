/**
 * 
 */
package org.yooreeka.algos.taxis.core.intf;

/**
 * @author babis
 *
 */
public interface Concept {

	public String getName();
	
	public Concept getParent();
	
	public Instance[] getInstances();
}
