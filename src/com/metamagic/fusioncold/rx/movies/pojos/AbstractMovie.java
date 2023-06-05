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
 * Abstract Movie implements MovieTitle interface
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public abstract class AbstractMovie implements MovieTitle {
	
	private double rating = 0;
	private int year = 1970;
	private String[] actors = {"Actor.1", "Actor.2" };
	
	private final String movieType;
	private final int movieId;
	
	/**
	 * Set the basic movie Type and Id
	 * 
	 * @param _id
	 * @param _type
	 */
	public AbstractMovie(int _id, String _type) {
		movieId = _id;
		movieType = _type;
	}
	
	/**
	 * Calculates the Movie Rating
	 * 
	 * @param _base	Set the base rating for the movie
	 * @return double Returns the rating of the Movie
	 */
	protected double calculateRating(int _base) {
		rating = (int) (Math.random() * (100 + 1));
		if(rating < _base) {
			rating += _base;
		}
		year += (rating / 4);
		rating = rating / 10;
		return rating;
	}
	
	/**
	 * Returns the Movie ID
	 */
	public int id() {
		return movieId;
	}

	/**
	 * Returns the Movie released Year
	 * 
	 * @return int
	 */
	public int year() {
		return year;
	}
	
	/**
	 * Returns Movie Rating
	 * @return int
	 */
	public double rating() {
		return rating;
	}
	
	/**
	 * Returns the Lead Actors
	 * @return String[]
	 */
	public String[] actors() {
		return actors;
	}
	
	/**
	 * Compares the Movie Rating (Descending Order)
	 */
	@Override
	public int compareTo(MovieTitle _movie) {
		return Double.compare(_movie.rating(), this.rating);
	}
	
	/**
	 * HashCode Method. Returns ID
	 * 
	 * @return int
	 */
	public int hashCode() {
		return movieId;
	}
	
	/**
	 * To String Method. Prints ID
	 * 
	 * @return String
	 */
	public String toString() {
		return (movieId <10) ? movieType+":00"+movieId : 
			((movieId <100) ? movieType+":0"+movieId : movieType+":"+movieId);
	}
	
	/**
	 * Equals Method
	 */
	public boolean equals(Object o) {
		try {
			AbstractMovie a = (AbstractMovie)o;
			if(movieId == a.movieId) {
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
	/**
	 * Returns the Movie Tag
	 * 
	 * @return String
	 */
	public String getMovieTag() {
		StringBuilder d = new StringBuilder();
		d.append("{");
		d.append(this).append(",Year:");
		d.append(year()).append(",Rating:");
		d.append(rating()).append("}");
		return d.toString();
	}
}
