/*
 *   ________________________________________________________________________________________
 *   
 *   Y O O R E E K A
 *   A library for data mining, machine learning, soft computing, and mathematical analysis
 *   ________________________________________________________________________________________ 
 *    
 *   The Yooreeka project started with the code of the book "Algorithms of the Intelligent Web " 
 *   (Manning 2009). Although the term "Web" prevailed in the title, in essence, the algorithms 
 *   are valuable in any software application.
 *  
 *   Copyright (c) 2007-2009    Haralambos Marmanis & Dmitry Babenko
 *   Copyright (c) 2009-2013 Marmanis Group LLC and individual contributors as indicated by the @author tags.  
 * 
 *   Certain library functions depend on other Open Source software libraries, which are covered 
 *   by different license agreements. See the NOTICE file distributed with this work for additional 
 *   information regarding copyright ownership and licensing.
 * 
 *   Marmanis Group LLC licenses this file to You under the Apache License, Version 2.0 (the "License"); 
 *   you may not use this file except in compliance with the License.  
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software distributed under 
 *   the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 *   either express or implied. See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.yooreeka.algos.mappings;

import java.util.ArrayList;
import java.util.Random;

import org.yooreeka.data.VectorSet;
import org.yooreeka.util.C;
import org.yooreeka.util.P;
import org.yooreeka.util.gui.XyGui;

/**
 * @author <a href="mailto:babis@marmanis.com">Babis Marmanis</a>
 *
 */
public class SammonMap {

	public static final double ALPHA_LOWER_BOUND = 0.3d;
	public static final double ALPHA_UPPER_BOUND = 0.4d;
	
	public static final double EPSILON=0.000001d;
	
	// This is the same set of points whose dimensionality has been reduced
	private VectorSet y;
	
	private int newDimensionality;
	
	//The range of alpha is between 0.3 and 0.4
	private double alpha=0.3;
	
	// This is a cache of sorts. It holds the distance matrix of the original vector.
	private double[][] d;
	
	// This is a cache of sorts. It holds the normalizing constant of the original vector.
	private double c;

	@SuppressWarnings("unused")
	private SammonMap() {
		// FORCE the use of the constructor where we specify 
		// the number of new dimensions
	}
	
	/**
	 * The constructor specifies the new dimensionality
	 */
	public SammonMap(int newDimensionality) {
		this.newDimensionality = newDimensionality;
		y = new VectorSet(newDimensionality);		
	}

	public VectorSet map(VectorSet x) {
		
		//Initialize
		y.populate(x.getPoints().size());
		
		// The distance matrix of the original vector set
		d = x.getDistanceMatrix();

		// Get the normalizing constant of the original vector
		c = x.getNormalizingConstant();
		
		// we need two vector sets because we iterate
		// However, a more compact implementation is possible
		VectorSet y2 = new VectorSet(newDimensionality);
		y2.populate(x.getPoints().size());
		
		
		// Counter for the iterations
		int m=0;
		
		while (y.diff(y2) > EPSILON) {
		
			//Replace the old with the new (corrected) set of points
			y.replace(y2);
			
			//make sure we clean up the old values
			y2.getPoints().clear();
			
			//y2 = y - alpha* (num/denum);
			for(int p=0; p < y.getPoints().size(); p++) {
				ArrayList<Double> point = y.getPoints().get(p);
				ArrayList<Double> newPoint = new ArrayList<Double>(newDimensionality);
				for (int q=0; q<newDimensionality; q++) {
					
					newPoint.add(q, point.get(q) - alpha *(getNumerator(p,q)/getDenumerator(p,q)));					
				}
				y2.add(newPoint);
			}
			
			m++;
			if (m%100==0) {
				P.println(m+" iterations completed");
			}
		}
		
		return y;
	}
	
	private double getNumerator(int p, int q) {
		double num=0.0d;
		for(int j=0; j<y.getPoints().size(); j++) {			
			for(int k=0; k<y.getPoints().size(); k++) {
				if (k!=p) {
					double deltaD = d[k][j]-y.getDistanceMatrix()[k][j];
					double prodD = d[k][j]*y.getDistanceMatrix()[k][j];
					double deltaY = y.get(p, q) - y.get(j, q);
					
					num += (deltaD/prodD)*deltaY;
				}
			}	
		}
		return -(2.0d/c)*num;
	}
	
	private double getDenumerator(int p, int q) {
		double denum=0.0d;
		for(int j=0; j<y.getPoints().size(); j++) {			
			for(int k=0; k<y.getPoints().size(); k++) {
				if (k!=p) {
					double dkj = y.getDistanceMatrix()[k][j];
					double deltaD = d[k][j]-dkj;
					double prodD = d[k][j]*dkj;
					double deltaY = y.get(p, q) - y.get(j, q);
					double deltaY2 = deltaY*deltaY;
					
					denum += (deltaD-(deltaY2/dkj)*(C.ONE_DOUBLE + (deltaD/dkj)))/prodD;
				}
			}	
		}
		return -(2.0d/c)*denum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		VectorSet v = getTestData(100,4);
	
		SammonMap sMap = new SammonMap(2);
		VectorSet w = sMap.map(v);
		
		XyGui ui = new org.yooreeka.util.gui.XyGui ("The plot of the data in 2D",w.getDimData(0),w.getDimData(1));
		ui.plot();
		
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * This method does not allow you to set the value of alpha
	 * outside the designated range.
	 * 
	 * @param val the alpha to set
	 */
	public void setAlpha(double val) {
		
		if (val < ALPHA_LOWER_BOUND) {
			
			alpha = ALPHA_LOWER_BOUND;
			
		} else if (val > ALPHA_UPPER_BOUND) {
			
			alpha = ALPHA_UPPER_BOUND;
			
		} else 
			alpha = val;
	}

	/**
	 * @return the y
	 */
	public VectorSet getY() {
		return y;
	}

	/**
	 * @return the newDimensionality
	 */
	public int getNewDimensionality() {
		return newDimensionality;
	}

	private static VectorSet getTestData(int N, int dim) {

		VectorSet v = new VectorSet(dim);
		Random rand = new Random(99991);
		
		//Create two clusters of N points
		for (int i=0; i<N; i++) {
			ArrayList<Double> point = new ArrayList<Double>();

			if (i<N/2) {
				for (int j=0; j<dim; j++) {
					point.add(C.ONE_DOUBLE+rand.nextDouble()*0.0001d);
				}
			} else {
				for (int j=0; j<dim; j++) {
					point.add(-C.ONE_DOUBLE+rand.nextDouble()*0.0001d);
				}
			}
			v.add(point);
		}
		return v;
	}
}
