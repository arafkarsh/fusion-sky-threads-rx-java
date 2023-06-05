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
package com.metamagic.fusioncold.rx.movies.run;

import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import com.metamagic.fusioncold.rx.movies.core.RecommendationEngineObservable;
import com.metamagic.fusioncold.rx.movies.core.RecommendationObserver;
import com.metamagic.fusioncold.rx.movies.pojos.MovieTitle;

/**
 * Movie Example using Rx Java 2
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class MovieExample {

	private RecommendationEngineObservable recommEngine;
	
	/**
	 * Movie Example
	 */
	public MovieExample() {
		recommEngine = new RecommendationEngineObservable();
		recommEngine.initialize();
	}
	/**
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		MovieExample movie = new MovieExample();
		
		System.out.println("\nRx.2.Java> Starting Movies Async Test Suite...................");
		System.out.println("Movie Codes > AC=Action, SF=SciFi, DR=Drama, RO=Romantic");
		
		System.out.println("\nRx.2.Java> Starting Testing U1 - Observable Take(20) .....");
		movie.movieRecommendations();
		System.out.println("Rx.2.Java> Tests Scheduled for U1...............");
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
		}

		int rating = 8;
		int take = 5;
		System.out.println("\nRx.2.Java> Starting Testing U2 - Observable with Filter and Take");
		System.out.println("Rx.2.Java> User Suggestions Rating > "+rating+" Suggest "+take+" Movies");
		movie.filterSortFlatMap(rating, take);
		System.out.println("Rx.2.Java> Tests Scheduled for U2...............");
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
		}
		
		rating = 6;
		take = 9;
		System.out.println("\nRx.2.Java> Starting Testing U3 - Flowable with Backpressure strategy = BUFFER");
		System.out.println("Rx.2.Java> User Suggestions Rating > "+rating+" Suggest "+take+" Movies");
		movie.filterSortFlatMapFlowable(rating, take);
		System.out.println("Rx.2.Java> Tests Scheduled for U3...............");
		try {
			Thread.sleep(30000);
		} catch (Exception e) {
		}
		System.out.println("\nRx.2.Java> Movies Async Test Suite Complete..........");
	}

	/**
	 * Show Movie Recommendations
	 */
	public void movieRecommendations() {
		RecommendationObserver<MovieTitle> 
					user = createRecommendationObserver("U1", 3);
		
		Observable<MovieTitle> movies = createRecommendationObservable();
		
		movies
			.take(21)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
					movie -> user.onNext(movie),
					throwable -> user.onError(throwable),
					() -> user.onComplete()
				);
	}
	
	/**
	 * Get Suggested movies / Sort / Filter / Take (n)
	 * Sorts the Movie based user Rating
	 * @param <T>
	 * 
	 * @param _rating User movie Rating
	 * @param _take No: of movies the user wants at a time
	 */
	public <T> void filterSortFlatMap(int _rating, int _take) {
		RecommendationObserver<MovieTitle> 
					user = createRecommendationObserver("U2", _rating);
		
		Observable<MovieTitle> movies = createRecommendationObservable();
		
		movies
			.filter(user.ratingFilter())
			.toSortedList()
			.flatMapObservable(list -> Observable.fromIterable(list)) 
			.take(_take)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
					movie -> user.onNext(movie),
					throwable -> user.onError(throwable),
					() -> user.onComplete()
			);
	}
	
	/**
	 * Flowable<NovieTitle> with Backpressure Strategy as Buffer.
	 * 
	 * There are 5 Backpressure Strategies
	 * 
	 * 1. BUFFER
	 * 		The source will buffer all the events until the subscriber can consume them.
	 * 
	 * 2. DROP
	 * 		Is used to discard the events that cannot be consumed instead of buffering them. 
	 * 		
	 * 3. LATEST
	 * 		Will force the source to keep only the latest events, thus overwriting any previous 
	 * 		values if the consumer can’t keep up.
	 * 
	 * 4. ERROR
	 * 		Here the assumption is that we don’t expect backpressure to occur. Consequently, 
	 * 		a MissingBackpressureException should be thrown if the consumer can’t keep up with 
	 * 		the source.
	 * 
	 * 5. MISSING
	 * 		The source will push elements without discarding or buffering.
	 * 
	 * @param _rating
	 * @param _take
	 */
	public <T> void filterSortFlatMapFlowable(int _rating, int _take) {
		RecommendationObserver<MovieTitle> 
					user = createRecommendationObserver("U3", _rating);
		
		Flowable<MovieTitle> movies = createRecommendationFlowable();
		
		movies
			.filter(user.ratingFilter())
			.toSortedList()
			.flatMapObservable(list -> Observable.fromIterable(list)) 
			.take(_take)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
					movie -> user.onNext(movie),
					throwable -> user.onError(throwable),
					() -> user.onComplete()
			);
	}
	
	/**
	 * Returns Recommendation Observer
	 * 
	 * @param _id
	 * @param _rating
	 * @return
	 */
	private RecommendationObserver<MovieTitle> createRecommendationObserver(String _id, Integer _rating) {
		return new RecommendationObserver<MovieTitle>(_id, _rating);
	}	
	
	/**
	 * Returns Movie Observable
	 * @return
	 */
	private Observable<MovieTitle> createRecommendationObservable() {
		return recommEngine.createMovieObservable();
	}
	
	/**
	 * Returns Movie Flowable with Backpressure Strategy as BUFFER.
	 * 
	 * @return
	 */
	private Flowable<MovieTitle> createRecommendationFlowable() {
		return recommEngine.createMovieFlowable();
	}
}
