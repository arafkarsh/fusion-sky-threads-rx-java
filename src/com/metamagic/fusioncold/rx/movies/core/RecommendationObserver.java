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

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

import com.metamagic.fusioncold.rx.fruit.pojos.Fruit;
import com.metamagic.fusioncold.rx.movies.pojos.MovieTitle;

/**
 * Movie Recommemndation Observer
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 * @param <T>
 */
	
public class RecommendationObserver<T extends MovieTitle> 
	extends DefaultSubscriber<MovieTitle> implements Predicate<T>, Observer<MovieTitle> {

	private boolean start = true;
	
	private long startTime = 0;
	private long endTime = 0;
	private long totalTime = 0;
	
	private int counter = 0;
	
	private String pid = "";
	
	// Filter Values
	private int rating = 1;
	
	/**
	 * User Movie Suggestion Initialized with Unique ID
	 * 
	 * @param _id Sets a unique ID for the Observer 
	 */
	public RecommendationObserver(String _id) {
		this(_id, 0);
	}
	
	/**
	 * User Movie Suggestion Initialized with Unique ID
	 * With Criteria to Filter
	 * 
	 * @param _id Sets a unique ID for the Observer 
	 * @param _rating Sets the base rating of the movie
	 */
	public RecommendationObserver(String _id, Integer _rating) {
		pid = _id;
		if(_rating > 0) {
			rating = _rating;
		}
		System.out.println("Rx.2.Java|Observer> User Suggestion (Observer) Initialized with ID = "+pid+" Rating="+rating);
	}

	
	/**
	 * on Next is called by Observable to Process the Movie
	 * 
	 * @param _movieTitle Process the Movie Title
	 */
	@Override
	public void onNext(MovieTitle _movieTitle) {
		if(start) {
			startTime = System.currentTimeMillis();
			start = false;
		}
		// Process the movie
		processMovie(_movieTitle);
	}
	
	/**
	 * Process the movie - it takes half a second to process the movie title.
	 * 
	 * @param _movieTitle Observable calls this method to Process the movie
	 */
	private void processMovie(final MovieTitle _movieTitle) {
		counter++;
		if(counter > 3) {
			counter = 1;
			System.out.println("");
		}
		System.err.print("["+pid+"]="+_movieTitle.getMovieTag()+" ");
		// Simulating Latency
		try { Thread.sleep(500); } 
		catch (InterruptedException e) {	e.printStackTrace(); }
	}

	/**
	 * Inform the Movie Observer if the Processing is done.
	 */
	@Override
	public void onComplete() {
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		double seconds = totalTime / 1000;
		System.out.println("\nRx.2.Java-"+pid+"|Observer> User Movie Suggestion Task Completed - Total Time in Seconds = "+seconds);
		start = true;
	}

	/**
	 * Throws the error while processing the movie
	 * 
	 * @param t Error while processing the movie
	 */
	@Override
	public void onError(Throwable t) {
		System.err.println("\nRx.2.Java-"+pid+"|Observer> User Movie Suggestion : Whoops Error!! = "+t.getMessage());
	}

	/**
	 * Returns true if the Movie Rating is greater
	 * 
	 * @param _movieTitle Check the movie rating
	 * @return Boolean Returns true if the Movie Rating is greater
	 */
	@Override
	public boolean test(T _movieTitle) {
		return (_movieTitle.rating() > rating);
	}
	
	/**
	 * Returns the Movie Rating Filter
	 * 
	 * @return Func1 Returns the Movie Rating Filter
	 */
	public Predicate<T> ratingFilter() {
		return this;
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
