package org.yooreeka.reco.collaborative.data;

import org.yooreeka.reco.collaborative.model.Rating;

/**
 * Rating for music dataset.
 * 
 * @author babis
 */
public class MusicRating extends Rating {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4015578066768031191L;

	public MusicRating(int userId, int songId, int rating) {
		
		super(userId, songId, rating);
	}

}
