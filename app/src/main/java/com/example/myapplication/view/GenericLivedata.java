package com.example.myapplication.view;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public class GenericLivedata<T> extends MutableLiveData<T> {

    int property;
    public GenericLivedata(int property) {
        this.property = property;
    }
    @Override
    protected void onInactive() {
        super.onInactive();
    }

    @Override
    protected void onActive() {
        super.onActive();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Integer index = 0; index < 100; index++) {
                    try {
                        Thread.sleep(5000);
                        postValue((T)index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();


    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        super.observe(owner, observer);
    }

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }
}
