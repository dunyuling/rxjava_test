package com.example;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class FilteringObservables {
    public static void main(String[] args) {
//        debounce();
        sample();
    }


    public static void debounce() {

        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i);
            }
            emitter.onComplete();

        }).debounce(1, TimeUnit.SECONDS).subscribe(new Observer<Integer>() {
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
        });
    }

    public static void sample() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            for (int i = 0; i < 11; i++) {
                Thread.sleep(1000);
                emitter.onNext(i);
            }
            emitter.onComplete();
        }).sample(3, TimeUnit.SECONDS).subscribe(new Observer<Integer>() {
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
        });
    }
}
