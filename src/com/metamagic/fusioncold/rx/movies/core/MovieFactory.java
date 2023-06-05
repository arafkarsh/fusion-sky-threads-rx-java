/**
 * Copyright (c) 2018 Araf Karsh Hamid

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

import com.metamagic.fusioncold.rx.movies.pojos.MovieAction;
import com.metamagic.fusioncold.rx.movies.pojos.MovieDrama;
import com.metamagic.fusioncold.rx.movies.pojos.MovieRomantic;
import com.metamagic.fusioncold.rx.movies.pojos.MovieSciFi;
import com.metamagic.fusioncold.rx.movies.pojos.MovieTitle;

/**
 * Builds the Movie Database
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public final class MovieFactory<T extends MovieTitle> {
	
	public final static int MAX_ACTION_MOVIES 	= 365;
	public final static int MAX_DRAMA_MOVIES 		= 242;
	public final static int MAX_ROMANTIC_MOVIES 	= 279;
	public final static int MAX_SCIFI_MOVIES 		= 157;
	
	/**
	 * Movie Factory Creates different repositories for different types of movies.
	 */
	public MovieFactory() {
	}
	
	/**
	 * Creates Action Movies
	 * 
	 * @param _limit Limit the no: of Actions Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieAction(int _limit) {
		ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieAction(x));
		}
		System.out.println("Rx.2.Java|Repository> Total Action Movies = "+movies.size());
		return new MovieRepository<T>(movies);
	}
	
	/**
	 * Creates Drama Movies
	 * 
	 * @param _limit Limit the no: of Drama Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieDrama(int _limit) {
		ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieDrama(x));
		}
		System.out.println("Rx.2.Java|Repository> Total Drama Movies = "+movies.size());
		return new MovieRepository<T>(movies);
	}
	
	/**
	 * Creates SciFi Movies
	 * 
	 * @param _limit Limit the no: of Sci-Fi Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieSciFi(int _limit) {
		ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieSciFi(x));
		}
		System.out.println("Rx.2.Java|Repository> Total Sci-Fi Movies = "+movies.size());
		return new MovieRepository<T>(movies);
	}
	
	/**
	 * Creates Romantic Movies
	 * 
	 * @param _limit Limit the no: of Romantic Movies from the database
	 * @return MovieRepository Returns the Movie Repository supporting the Fluent API paradigm
	 */
	public MovieRepository<T> createMovieRomantic(int _limit) {
		ArrayList<MovieTitle> movies = new ArrayList<MovieTitle>();
		for(int x=1; x<=_limit; x++) {
			movies.add(new MovieRomantic(x));
		}
		System.out.println("Rx.2.Java|Repository> Total Romantic Movies = "+movies.size());
		return new MovieRepository<T>(movies);
	}
}
