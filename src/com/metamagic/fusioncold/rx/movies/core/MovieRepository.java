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
package com.metamagic.fusioncold.rx.movies.core;

import java.util.ArrayList;
import java.util.Collection;

import com.metamagic.fusioncold.rx.movies.pojos.MovieAction;
import com.metamagic.fusioncold.rx.movies.pojos.MovieDrama;
import com.metamagic.fusioncold.rx.movies.pojos.MovieRomantic;
import com.metamagic.fusioncold.rx.movies.pojos.MovieSciFi;
import com.metamagic.fusioncold.rx.movies.pojos.MovieTitle;

/**
 * Movie Repository 
 * 
 * Repository creates various movie databases like Actiom, Comedy, Sci-Fi, Romance etc.
 * 
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 * @param <T>
 */
public class MovieRepository<T extends MovieTitle> {
	
	private final ArrayList<MovieTitle> movies;
	
	/**
	 * Constructor to be used by MovieFactory
	 * 
	 * @param _movies
	 */
	protected MovieRepository(ArrayList<MovieTitle> _movies) {
		movies = (_movies != null) ? _movies : new ArrayList<MovieTitle>();
	}
	
	/**
	 * Returns a Random Movie from the Database
	 * @return
	 */
	public MovieTitle getRandomMovie() {
		return movies.get((int) (Math.random() * (movies.size() - 1)));
	}
	
	
	/**
	 * Returns Movie Collection
	 * 
	 * @return Collection Returns a collection of Movies
	 */
	public Collection<MovieTitle> collection() {
		return movies;
	}
	
	/**
	 * Returns Movie List
	 * 
	 * @return ArrayList Returns an ArrayList of Movies
	 */
	public ArrayList<MovieTitle> list() {
		return movies;
	}
	
	/**
	 * Returns Movie Iterable
	 * 
	 * @return Iterable Returns an iterable of Movies
	 */
	public Iterable<MovieTitle> iterable() {
		return movies;
	}
}
