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
package com.fusion.sky.rx.fruit.core;


import com.fusion.sky.rx.fruit.pojos.Fruit;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Abstract Fruit Observable
 * 
 * @author Araf Karsh Hamid
 * @version 1.0
 * @date
 */
public abstract class AbstractFruitObservable implements ObservableOperator<Observer<Fruit>, Observer<Fruit>>  {

	/**
	 * 
	 * @param subscriber Observer for the Observable
	 * @return Subscriber Returns the Observer
	 */
	public DefaultSubscriber<Fruit> apply(DefaultSubscriber<? super Fruit> subscriber) {
		return new DefaultSubscriber<Fruit>() {
			public void onComplete() { subscriber.onComplete();  }
			public void onError(Throwable e) { subscriber.onError(e); }
			public void onNext(Fruit _fruit) { 						
				subscriber.onNext(_fruit);	
			}
		};

	}
}


