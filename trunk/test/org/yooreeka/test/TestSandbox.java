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
 *   Copyright (c) 2007-2009 Haralambos Marmanis & Dmitry Babenko
 *   Copyright (c) 2009-${year} Marmanis Group LLC and individual contributors as indicated by the @author tags.  
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
package org.yooreeka.test;

import org.yooreeka.examples.spamfilter.EmailClassifier;
import org.yooreeka.examples.spamfilter.data.Email;
import org.yooreeka.examples.spamfilter.data.EmailData;
import org.yooreeka.examples.spamfilter.data.EmailDataset;


/**
 * 
 * @author <a href="mailto:babis@marmanis.com">Babis Marmanis</a>
 *
 */
public class TestSandbox {

	/**
	 * Throw your code in the main method for quick tests.
	 * This is useful when you are testing Beanshell scripts.
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// Create and train classifier
		EmailDataset trainEmailDS = EmailData.createTrainingDataset();
		EmailClassifier emailFilter = new EmailClassifier(trainEmailDS, 10);
		emailFilter.train();

		// Let's classify some emails from training set. If we can't get them right
		// then we are in trouble :-)
		Email email = null;
		email = trainEmailDS.findEmailById("biz-04.html");
		emailFilter.classify(email);

		email = trainEmailDS.findEmailById("usa-03.html");
		emailFilter.classify(email);

		// Now, let's classify previously unseen emails

		EmailDataset testEmailDS = EmailData.createTestDataset();
		email = testEmailDS.findEmailById("biz-01.html");
		emailFilter.classify(email);

		email = testEmailDS.findEmailById("sport-01.html");
		emailFilter.classify(email);

		email = testEmailDS.findEmailById("usa-01.html");
		emailFilter.classify(email);

		email = testEmailDS.findEmailById("world-01.html");
		emailFilter.classify(email);

		email = testEmailDS.findEmailById("spam-biz-01.html");
		emailFilter.classify(email);
	}
}
