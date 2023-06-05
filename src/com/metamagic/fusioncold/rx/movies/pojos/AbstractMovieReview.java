/**
 * Copyright (c) 2017 Araf Karsh Hamid

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.

 * This program and the accompanying materials are licensed based on Apache 2 License.
*/
package com.metamagic.fusioncold.rx.movies.pojos;

/**
 * Abstract Movie Review
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public abstract class AbstractMovieReview implements MovieReview {

	private int rating = 0;
	
	/**
	 * Calculates the Movie Rating
	 * 
	 * @param _base	Set the base rating for the movie
	 * @return double Returns the rating of the Movie
	 */
	protected int calculateRating(int _base) {
		rating = (int) (Math.random() * (100 + 1));
		if(rating < _base) {
			rating += _base;
		}
		rating = rating / 10;
		return rating;
	}
	
	/**
	 * Returns Movie Review Rating
	 * @return int
	 */
	public int rating() {
		return rating;
	}
	
	/**
	 * Movie Review Rating Sorting
	 */
	public int compareTo(MovieReview _review) {
		return Integer.compare(_review.rating(), this.rating);
	}
	
	/**
	 * Review Tag
	 * @return String
	 */
	public String getReviewTag() {
		StringBuilder d = new StringBuilder();
		d.append(this).append(" [");
		d.append(id()).append(":");
		d.append(rating()).append("]");
		return d.toString();
	}
}
