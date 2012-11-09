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
 *   Copyright (c) 2009-2012 Marmanis Group LLC and individual contributors as indicated by the @author tags.  
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
package org.yooreeka.util.text;

import java.util.logging.Logger;

import org.yooreeka.config.YooreekaConfigurator;
import org.yooreeka.util.C;
import org.yooreeka.util.P;
import org.yooreeka.util.metrics.EuclideanDistance;

import com.wcohen.ss.NeedlemanWunsch;
import com.wcohen.ss.api.StringDistance;

/**
 * 
 * @author <a href="mailto:babis@marmanis.com">Babis Marmanis</a>
 * 
 */
public class AlphabetProjection {

	private static final Logger LOG = Logger.getLogger(AlphabetProjection.class.getName());

	/**
	 * <tt>dimensionality</tt> determines the number of <tt>String</tt> vectors
	 * that we will use.
	 */
	private static final int dimensionality = 10;

	/**
	 * <tt>baseLength</tt> determines the length of the <TT>String</TT> vectors
	 * that we will use.
	 */
	private static final int baseLength = 10;

	private char[] defaultCharBasis = { 'e', 't', 'a', 'o', 'n', 'r', 'i', 's',
			'h', 'd', 'l', 'f', 'c', 'm', 'u', 'g', 'y', 'p', 'w', 'b', 'v',
			'k', 'x', 'j', 'q', 'z' };

	private String[] projectionBasis = null;

	private NeedlemanWunsch needlemanWunch;
	private StringDistance needlemanWunchDistance = null;

	public AlphabetProjection() {

		LOG.setLevel(YooreekaConfigurator.getLevel(AlphabetProjection.class.getName()));

		// Initialize the projection
		initProjection();

		needlemanWunch = new NeedlemanWunsch();
	}

	/**
	 * 
	 * @param target
	 *            the String that we want to project onto the base vectors
	 * @param projections
	 *            of the <CODE>target</CODE> onto each one of the base vectors.
	 * 
	 * 
	 */
	public double[] project(String target) throws IllegalArgumentException {

		double[] projections = new double[dimensionality];

		if (target == null) {
			target = C.EMPTY_STRING;
		}

		target.toLowerCase();
		
		needlemanWunchDistance = needlemanWunch.getDistance();

		for (int i = 0; i < dimensionality; i++) {

			projections[i] = needlemanWunchDistance.score(projectionBasis[i], target);

		}

		return projections;
	}

	public double distance(String val1, String val2) {

		EuclideanDistance euclid = new EuclideanDistance();

		return euclid.getDistance(project(val1), project(val2));
	}

	/** Retrieves the dimensionality of the projection space */
	public int getDimensionality() {
		return dimensionality;
	}

	/**
	 * Retrieves the length of the String vectors that form the projection basis
	 */
	public int getEigenLength() {
		return baseLength;
	}

	/**
	 * @param val
	 *            the single character of the base vector
	 * 
	 * @return the base vector for the <tt>val</tt> character.
	 */
	public String getEigenvector(char val) {

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < baseLength; i++) {

			buf.append(val);
		}

		return buf.toString();
	}

	/**
	 * Initialize the configuration space.
	 */
	private void initProjection() {

		projectionBasis = new String[dimensionality];

		// First define the String basis onto which we will project a given
		// String
		for (int i = 0; i < dimensionality; i++) {
			projectionBasis[i] = getEigenvector(defaultCharBasis[i]);
		}

	}

	// --------------------------------------------------------------------------------
	public static void main(String[] args) throws Exception {

		AlphabetProjection aProjection = new AlphabetProjection();

		final String TEST_STRING_1 = "NiKoLaos";
		final String TEST_STRING_2 = "Nikolitsa";
		final String TEST_STRING_3 = "Mqpuyrvx";

		P.println("d[T1,T2] = "
				+ aProjection.distance(TEST_STRING_1, TEST_STRING_2));
		P.println("d[T1,T3] = "
				+ aProjection.distance(TEST_STRING_1, TEST_STRING_3));
	}

	public static int[] getProjectionProperties() {
		return new int[] { dimensionality, baseLength };
	}
}
