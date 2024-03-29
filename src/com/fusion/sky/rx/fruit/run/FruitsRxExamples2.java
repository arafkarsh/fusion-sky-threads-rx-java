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
package com.fusion.sky.rx.fruit.run;

import com.fusion.sky.rx.fruit.pojos.Fruit;
import com.fusion.sky.rx.fruit.core.FruitBasketObservableFactory;
import com.fusion.sky.rx.fruit.core.FruitProcessor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Complex Observable Examples with more Operators
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public class FruitsRxExamples2 {

	/**
	 * Rx.2.Java Complex Examples
	 * 
	 * @param args Arguments from the Command line
	 */
	public static void main(String[] args) {

		System.out.println("\nATS> Rx 2 Java Ex.2 Starting the Async Test Suite 2.......................");
		FruitsRxExamples2 rx = new FruitsRxExamples2();
		
		System.out.println("\nATS> Rx2 Merging Three Streams Async Test......................");
		rx.mergeThreeTeams();
		System.out.println("ATS> Rx2 Merging Three Streams Async Test Scheduled............ \n");
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		int weight = 51;
		System.out.println("\nATS> Rx2 Merging Three Streams & Filter Async Test......................");
		System.out.println("ATS> Rx2 Filter Criteria : Weight > "+weight);
		rx.filterFruitsOnWeight(weight);
		System.out.println("ATS> Rx2 Merging Three Streams & Filter Async Test Scheduled............ \n");
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int take = 5;
		// weight = 60;
		System.out.println("\nATS> Rx2 Merging Three Streams & Filter / Sort Price (Asc) Async Test........");
		System.out.println("ATS> Rx2 Filter Criteria : Weight > "+weight+ " Take = "+take);
		rx.filterSortAndTake(weight, take);
		System.out.println("ATS> Rx2 Merging Three Streams & Filter / Sort Async Test Scheduled............ \n");

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nATS> Rx2 Merging Three Streams & Filter / Sort Price (Asc) / Flat Map Async Test........");
		System.out.println("ATS> Rx2 Filter Criteria : Weight > "+weight+ " Take = "+take);
		rx.filterSortFlatMapTake(weight, take);
		System.out.println("ATS> Rx2 Merging Three Streams & Filter / Sort / Flat Map Async Test Scheduled............ \n");

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nATS> Rx2 Java Ex.2 Async Test Suite 2 Complete .....................");
	}
	
	/**
	 * Merge Three Data Streams Asynchronously
	 * 
	 * Rx 2 Java Merge Example - Asynchronous
	 */
	public void mergeThreeTeams() {
		
		Observer<Fruit> fp = fruitProcessorObserver("ME");
		Observable
			.merge( appleFruitBasketObservable(), 
					orangeFruitBasketObservable(),
					grapesFruitBasketObservable()
				)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
				fruit -> fp.onNext(fruit),
				throwable -> fp.onError(throwable),
				() -> fp.onComplete()
			);
	}
	
	/**
	 * Filter Fruits based on Weight - Rx 2 Java
	 * 
	 * @param weight Sets the base weight for the Fruits
	 */
	public void filterFruitsOnWeight(final int weight) {
		
		Observer<Fruit> fp = fruitProcessorObserver("FI");
		Observable
			.merge( appleFruitBasketObservable(), 
					orangeFruitBasketObservable(),
					grapesFruitBasketObservable()
				)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.filter((Predicate<? super Fruit>) new Predicate<Fruit>() {
					public boolean test(Fruit _fruit) {
						return( _fruit.weight()  > weight);
					}
			})
			.subscribe(
				fruit -> fp.onNext(fruit),
				throwable -> fp.onError(throwable),
				() -> fp.onComplete()
			);
	}
	
	/**
	 * Filter, Sort on Price (Ascending) and Take (Rx 2 Java)
	 * 
	 * @param _weight Sets the base weight for the Fruit
	 * @param _take Sets How many fruits needs to be collected
	 */
	
	public void filterSortAndTake(int _weight, int _take) {
		FruitProcessor<Fruit> fp = fruitProcessor("SO", _weight);
		
		Observable
			.fromIterable(Observable
				.merge( appleFruitBasketObservable(), 
						orangeFruitBasketObservable(),
						grapesFruitBasketObservable()
					)
				.filter(fp.weightFilter())
				.toSortedList()
				.blockingGet()
			).take(_take)
			.observeOn(Schedulers.computation())
			.subscribeOn(Schedulers.computation())
			.subscribe(
				fruit -> fp.onNext(fruit),
				throwable -> fp.onError(throwable),
				() -> fp.onComplete()
			);
	}
	
	/**
	 * Filter, Sort on Price (Ascending), Flat Map and Take
	 * FlatMap is used to Transform One Observable to another
	 * (Rx 2 Java)
	 * @param _weight Sets the base weight for the Fruit
	 * @param _take Sets How many fruits needs to be collected
	 */
	public void filterSortFlatMapTake(int _weight, int _take) {
		FruitProcessor<Fruit> fp = fruitProcessor("FM", _weight);
		
		Observable
		.merge( appleFruitBasketObservable(), 
				orangeFruitBasketObservable(),
				grapesFruitBasketObservable()
			)
		
		.filter(fp.weightFilter())
		.toSortedList()
		.flatMapObservable(list -> Observable.fromIterable(list)) 
		.take(_take)
		
		.observeOn(Schedulers.computation())
		.subscribeOn(Schedulers.computation())
		.subscribe(
			fruit -> fp.onNext(fruit),
			throwable -> fp.onError(throwable),
			() -> fp.onComplete()
		);
	}
	
  	/**
	 * Returns the Apple Processor Observer
	 * This Observer handles every Apple emitted from the 
	 * Observable
	 * 
	 * @param _id Sets a unique ID for the Fruit Observer
	 * @return Observer<Fruit> Returns the Fruit Observer
	 */
	private Observer<Fruit> fruitProcessorObserver(String _id) {
		return (Observer<Fruit>) new FruitProcessor<Fruit>(_id);
	}

	/**
	 * Returns the Fruit Processor Observer
	 * This Observer handles every Fruit emitted from the 
	 * Observable
	 * 
	 * @param _id Sets the unique ID for the Fruit Processor
	 * @param _weight Sets the base weight for the Fruit
	 * @return Observer<Fruit> Returns the Fruit Observer
	 */
	private FruitProcessor<Fruit> fruitProcessor(String _id, Integer _weight) {
		return new FruitProcessor<Fruit>(_id, _weight);
	}
	
	/**
	 * Returns the Apple Fruit Basket Observable
	 * 
	 * @return Observable<Fruit> Returns the Fruit Observable
	 */
	private Observable<Fruit> appleFruitBasketObservable() {
		return FruitBasketObservableFactory.createAppleBasketObservable(3);
	}
	
	/**
	 * Returns the Orange Fruit Basket Observable
	 * 
	 * @return Observable<Fruit> Returns the Fruit Observable
	 */
	private Observable<Fruit> orangeFruitBasketObservable() {
		return FruitBasketObservableFactory.createOrangeBasketObservable(4);
	}
	
	/**
	 * Returns the Grapes Fruit Basket Observable
	 * 
	 * @return Observable<Fruit> Returns the Fruit Observable
	 */
	private Observable<Fruit> grapesFruitBasketObservable() {
		return FruitBasketObservableFactory.createGrapesBasketObservable(5);
	}
}
