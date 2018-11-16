package com.example;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CreatingObservables {
    public static void main(String[] args) {
//        create();
        just();
    }


    private static void create() {

        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
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
        //建立连接
        observable.subscribe(observer);


    }

    private static void just() {
        Observable<String> observer = Observable.just("Hello"); // provides datea
        observer.subscribe(System.out::println);

    }
}
