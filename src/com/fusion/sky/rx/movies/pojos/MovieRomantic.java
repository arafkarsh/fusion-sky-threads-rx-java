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
package com.fusion.sky.rx.movies.pojos;

/**
 * Romantic Movie Entity
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class MovieRomantic extends AbstractMovie {

	/**
	 * Romantic Movie : Base = 21
	 * @param _id Unique ID for the Action Movie
	 */
	public MovieRomantic(int _id) {
		super(_id, "RO");
		calculateRating(21);
	}
	
	@Override
	public String title() {
		return "Movie Title.1";
	}

	@Override
	public String director() {
		return "Movie Director.1";
	}

	@Override
	public String writer() {
		return "Movie Writer";
	}
}
