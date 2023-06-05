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
 * Movie Title Interface
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public interface MovieTitle extends Comparable<MovieTitle> {

	/**
	 * Returns Unique Movie ID
	 * @return
	 */
	public int id();
	
	/**
	 * Returns Movie Title
	 * @return
	 */
	public String title();
	
	/**
	 * Returns Movie Rating
	 * @return
	 */
	public double rating();
	
	/**
	 * Returns Movie released Year.
	 * @return
	 */
	public int year();
	
	/**
	 * Returns Movie Director
	 * @return
	 */
	public String director();
	
	/**
	 * Returns Movie Actors
	 * @return
	 */
	public String[] actors();
	
	/**
	 * Returns Movie Script Writer
	 * @return
	 */
	public String writer();
	
	/**
	 * Returns Movie Type, ID, Rating and Release year
	 * @return
	 */
	public String getMovieTag();
}
