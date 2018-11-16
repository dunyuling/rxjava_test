package com.example;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class CombingObservables {
    public static void main(String[] args) {
//        zip();
//        merge();
//        startWith();
        combineLatest();
    }

    private static Observer<Integer> newIntegerObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe()");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("onNext(): " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");
            }
        };
    }

    private static void zip() {
        Observable<Integer> observable1 = Observable.just(10, 20, 30, 40);
        Observable<Integer> observable2 = Observable.just(1, 2, 3);
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(newIntegerObserver());
    }

    private static void merge() {
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable.merge(odds, evens).subscribe(newIntegerObserver());
    }

    private static void startWith() {
        Observable<Integer> observable = Observable.just(2, 3);
        observable.startWith(1).subscribe(newIntegerObserver());
    }

    private static void combineLatest() {
        Observable<Integer> first = Observable.just(1, 3, 5);
        Observable<Integer> second = Observable.just(2, 4, 6, 8);

        Observable.combineLatest(first, second, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                System.out.println(integer + "," + integer2);
                return integer + integer2;
            }
        }).subscribe(newIntegerObserver());
    }
}