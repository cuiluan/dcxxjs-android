package com.dcxxjs.core.networkinterface;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作    者:崔潞安
 * 时    间：2019/10/25
 * 功能描述：链式访问
 * 联系方式：
 */
public class BaseObservable extends Observable<SignBaseResult> {

    protected BaseObservable(OnSubscribe<SignBaseResult> f) {
        super(f);
    }

    /**
     * 访问网络
     *
     * @param observer
     * @param <T>
     */
    public <T extends SignBaseFrom> void access(SignBaseObserver observer) {
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//主线程更新数据
                .subscribe(observer);
    }
}
