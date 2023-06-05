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

import io.reactivex.*;

import com.metamagic.fusioncold.rx.movies.core.MovieRepository;
import com.metamagic.fusioncold.rx.movies.pojos.MovieAction;
import com.metamagic.fusioncold.rx.movies.pojos.MovieDrama;
import com.metamagic.fusioncold.rx.movies.pojos.MovieRomantic;
import com.metamagic.fusioncold.rx.movies.pojos.MovieSciFi;
import com.metamagic.fusioncold.rx.movies.pojos.MovieTitle;

/**
 * Movie Recommendation Engine. 
 * 
 * Recommendation Engine randomly creates movie list based on movie rating.
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class RecommendationEngineObservable {
	
	private MovieRepository<MovieAction> actionMovies;
	private MovieRepository<MovieDrama> dramaMovies;
	private MovieRepository<MovieRomantic> romanticMovies;
	private MovieRepository<MovieSciFi> sciFiMovies;
	
	private ArrayList<MovieTitle> suggestedMovies;
	
	public RecommendationEngineObservable() {
	}
	
	/**
	 * Initialize the Factory with Simulated Data
	 */
	public  RecommendationEngineObservable initialize() {
			getActionMovies();
			getDramaMovies();
			getRomanticMovies();
			getSciFiMovies();
		return this;
	}
	
	/**
	 * Returns the Action Movie Repository
	 * 
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieAction> getActionMovies() {
		return getActionMovies(MovieFactory.MAX_ACTION_MOVIES);
	}
	
	/**
	 * Retrieve Action Movies
	 * 
	 * @param _limit Limit the no: of Action Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieAction> getActionMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(actionMovies == null) {
			actionMovies = new MovieFactory().createMovieAction(_limit);
		}
		return actionMovies;
	}
	
	/**
	 * Return Drama Movies
	 * 
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieDrama> getDramaMovies() {
		return getDramaMovies(MovieFactory.MAX_DRAMA_MOVIES);
	}
	
	/**
	 * Retrieve Drama Movies
	 * 
	 * @param _limit Limit the no: of Drama Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieDrama> getDramaMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(dramaMovies == null) {
			dramaMovies = new MovieFactory().createMovieDrama(_limit);
		}
		return dramaMovies;
	}
	
	/**
	 * Return Romantic Movies
	 * 
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieRomantic> getRomanticMovies() {
		return getRomanticMovies(MovieFactory.MAX_ROMANTIC_MOVIES);
	}
	
	/**
	 * Retrieve Romantic Movies
	 * 
	 * @param _limit Limit the no: of Romantic Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieRomantic> getRomanticMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(romanticMovies == null) {
			romanticMovies = new MovieFactory().createMovieRomantic(_limit);
		}
		return romanticMovies;
	}
	
	/**
	 * Retrieve SciFi Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieSciFi> getSciFiMovies() {
		return getSciFiMovies(MovieFactory.MAX_SCIFI_MOVIES);
	}
	
	/**
	 * Retrieve Sci-Fi Movies
	 * 
	 * @param _limit Limit the no: of Sci-Fi Movies
	 * @return MovieRepository Returns the Movie Repository
	 */
	private MovieRepository<MovieSciFi> getSciFiMovies(int _limit) {
		_limit = checkLimit(_limit);
		if(sciFiMovies == null) {
			sciFiMovies = new MovieFactory().createMovieSciFi(_limit);
		}
		return sciFiMovies;
	}
	
	/**
	 * Check if the limit is valid
	 * 
	 * @param _limit Checks a valid limit
	 * @return int Returns a valid limit
	 */
	private static int checkLimit(int _limit) {
		return (_limit < 1) ? 20 : _limit;
	}
	
	/**
	 * Find Movies from the Movie Database
	 * 
	 * @return
	 */
	private ArrayList<MovieTitle> findMovies() {
		return findMovies(1);
	}
	
	/**
	 * Find Movies from Movie Database
	 * 
	 * @return ArrayList Returns a list movies from various categories
	 */
	private ArrayList<MovieTitle> findMovies(int _multiplier) {
		if(suggestedMovies == null) {
			suggestedMovies = new ArrayList<MovieTitle>();
			int totalMovies = (int) (Math.random() * (15 + 1)) * _multiplier;
			if(totalMovies < 5) {
				totalMovies = 5;
			}
			for(int x=0; x<totalMovies; x++) {
				suggestedMovies.add(actionMovies.getRandomMovie());
				suggestedMovies.add(dramaMovies.getRandomMovie());
				suggestedMovies.add(romanticMovies.getRandomMovie());
				suggestedMovies.add(sciFiMovies.getRandomMovie());
			}
		}
		return suggestedMovies;
	}
	
	/**
	 * Returns Movie Observable
	 * 
	 * @return Observable Returns the Movie Observable
	 */
	public Observable<MovieTitle> createMovieObservable() {
		return createMovieObservable(5);
	}
	
	/**
	 * Returns Movie Observable
	 * 
	 * @return Observable Returns the Movie Observable
	 */
	public Observable<MovieTitle> createMovieObservable (int _multiplier) {

		// Movie Repository Call
		final ArrayList<MovieTitle> movies = findMovies(_multiplier);
		
		// Create Observable
		Observable<MovieTitle> obs = Observable.create(
								new ObservableOnSubscribe<MovieTitle>() {
									
		    @Override
		    public void subscribe(ObservableEmitter<MovieTitle> observer) {
		        try {
		            if (!observer.isDisposed()) {
		            	
		            		movies
			            		.forEach( movie -> observer.onNext(movie) );
		            	
		                observer.onComplete();
		            }
		        } catch (Exception e) {
		            observer.onError(e);
		        } finally {
		        		// observer.unsubscribe();
		        }
		    }

		 } );
		return obs;
	}
	
	/**
	 * Returns a Flowable<NovieTitle> with Backpressure Strategy as Buffer.
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
	 * @return
	 */
	public Flowable<MovieTitle> createMovieFlowable () {

		// Movie Repository Call
		final ArrayList<MovieTitle> movies = findMovies();
		
		// Create Flowable
		Flowable<MovieTitle> flowable = createMovieObservable(15).toFlowable(BackpressureStrategy.BUFFER);
		return flowable;
	}
}
