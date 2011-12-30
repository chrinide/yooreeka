/**
 * 
 */
package org.yooreeka.ontology.intf;

/**
 * @author babis
 *
 */
public interface Concept {

	public String getName();
	
	public Concept getParent();
	
	public Instance[] getInstances();
}
