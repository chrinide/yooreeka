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
package org.yooreeka.data;

import java.util.ArrayList;
import java.util.Random;

import org.yooreeka.util.C;
import org.yooreeka.util.metrics.EuclideanDistance;

/**
 * A set of points for an arbitrary n-dimensional space. The space is considered
 * continuous, not discrete, hence the coordinates are of type <tt>double</tt>.
 * 
 * @author <a href="mailto:babis@marmanis.com">Babis Marmanis</a>
 *
 */
public class VectorSet {

	// The dimensionality of the space
	private int n;
	
	//This is the list of all the points
	private ArrayList<ArrayList<Double>> points;
	
	// Normalizing constant
	private double c=C.ZERO_DOUBLE;
	
	private double[][] distances=null;
	
	/**
	 * 
	 */
	public VectorSet(int dimensionality) {
		this.n = dimensionality;
		points = new ArrayList<ArrayList<Double>>();
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @return the points
	 */
	public ArrayList<ArrayList<Double>> getPoints() {
		return points;
	}

	public double[][] getDistanceMatrix() {
		if (distances ==null) {
			distances=new double[points.size()][points.size()];
		
			for (int i=0; i < points.size(); i++) {
				for (int j=i; j<points.size(); j++) {
					if (j==i) {
						distances[i][j] = C.ZERO_DOUBLE;
					} else {
						distances[i][j] = d(points.get(i),points.get(j));
						c += distances[i][j];
					}
				}
			}
		}		
		return distances;
	}
	
	private double d(ArrayList<Double> x_i, ArrayList<Double> x_j) {
		double d=0;

		Double[] xi = new Double[x_i.size()];
		for (int i=0; i < x_i.size(); i++) {
			xi[i] = x_i.get(i);
		}
		
		Double[] xj = new Double[x_j.size()];
		for (int i=0; i < x_j.size(); i++) {
			xj[i] = x_j.get(i);
		}
		
		if (x_i.size() == n && x_j.size() == n) {
			
			d = EuclideanDistance.getDistance(xi, xj);
			
		} else if (x_i.size() == n && x_j.size() < n) {
			
			Double[] paddedXj = new Double[n];
			for (int k=0; k < x_j.size(); k++) {
				paddedXj[k] = xj[k];
			}
			for (int k=x_j.size(); k < n; k++) {
				paddedXj[k] = C.ZERO_DOUBLE;
			}
			d = EuclideanDistance.getDistance(xi, paddedXj);
			
		} else if (x_i.size() < n && x_j.size() == n) {
			
			Double[] paddedXi = new Double[n];
			for (int k=0; k < x_i.size(); k++) {
				paddedXi[k] = xi[k];
			}
			for (int k=x_i.size(); k < n; k++) {
				paddedXi[k] = C.ZERO_DOUBLE;
			}
			d = EuclideanDistance.getDistance(xi, paddedXi);
			
		} else 
			throw new IllegalArgumentException("Every vector should have dimensionality equal to or less than "+n);
		
		return d;
	}
	
	public double getNormalizingConstant() {
		return c;
	}
	
	public double diff(VectorSet z) {
		double delta=0;
		
		if (points.size() != z.getPoints().size())
			throw new IllegalArgumentException("You can't diff two sets that do not have the same number of points");
		
		for (int i=0; i < points.size(); i++) {
			
		}
		return delta;
	}
	
	public void add(ArrayList<Double> p) {
		points.add(p);
	}
	
	public double get(int i, int j) {
	
		return points.get(i).get(j);
	}
	
	public void replace(VectorSet vs) {

		points.clear();
		
		for (int i=0; i<vs.getPoints().size(); i++) {
			ArrayList<Double> v = new ArrayList<Double>(n);
			for (int j=0; j<n; j++) {
				v.add(vs.get(i, j));
			}
			points.add(v);
		}
	}
	
	public void populate(int numberOfPoints) {
		Random rand = new Random(99991); // this is a prime, just in case you wonder ...
		for (int i=0; i<numberOfPoints; i++) {
			ArrayList<Double> v = new ArrayList<Double>(n);
			for (int j=0; j<n; j++) {
				v.add(rand.nextDouble());
			}
			points.add(v);
		}
	}
	
	public double[] getDimData(int dim) {
		int n = getPoints().size();
		
		double[] x = new double[n];
		
		for (int i=0; i < n; i++) {
			x[i] = get(i,dim);
		}
		
		return x;
	}
}
