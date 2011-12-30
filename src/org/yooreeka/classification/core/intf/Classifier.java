package org.yooreeka.classification.core.intf;

import org.yooreeka.ontology.intf.Concept;
import org.yooreeka.ontology.intf.Instance;


/** 
 * Every classifier must be:
 * <UL> 
 *   <LI> able to load a <CODE>TrainingSet</CODE>, and </LI>
 *   <LI> able to classify an <CODE>Instance</CODE></LI>
 * </UL>
 * 
 * This interface reflects these two elementary methods.
 * 
 * @author babis
 */
public interface Classifier {

    public String getName();
    
	public boolean train();

	public Concept classify(Instance instance);
}
