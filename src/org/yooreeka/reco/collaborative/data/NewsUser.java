/**
 * 
 */
package org.yooreeka.reco.collaborative.data;

import java.io.Serializable;
import java.util.List;

import org.yooreeka.reco.collaborative.model.Rating;
import org.yooreeka.reco.collaborative.model.User;

/**
 * @author bmarmanis
 *
 */
public class NewsUser extends User implements Serializable {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = 3415187707158663184L;

	/**
	 * @param id
	 */
	public NewsUser(int id) {
		super(id);
	}

	/**
	 * @param id
	 * @param name
	 */
	public NewsUser(int id, String name) {
		super(id, name);
	}

	/**
	 * @param id
	 * @param ratings
	 */
	public NewsUser(int id, List<Rating> ratings) {
		super(id, ratings);
	}

	/**
	 * @param id
	 * @param name
	 * @param ratings
	 */
	public NewsUser(int id, String name, List<Rating> ratings) {
		super(id, name, ratings);
	}

}
