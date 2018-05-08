package wowsome.com.wowsome.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.schedulers.Schedulers;
import wowsome.com.wowsome.network.APIResponse;
import wowsome.com.wowsome.network.APIS;
import wowsome.com.wowsome.network.ApiService;
import wowsome.com.wowsome.network.RetrofitClient;
import wowsome.com.wowsome.presenter.whether.FetchDataImp;
import wowsome.com.wowsome.presenter.whether.WhetherPresenter;
import wowsome.com.wowsome.ui.whether.WhetherActivity;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class FetchCitiesList {

    public static FetchCitiesList fetchCitiesList;
    String respose = "";

    public static FetchCitiesList getInstance() {
        if (null == fetchCitiesList) {
            fetchCitiesList = new FetchCitiesList();

        }
        return fetchCitiesList;
    }

    public String fetchCitiesList(final WhetherPresenter fetchDataImp, String keyword) {

        getObserable(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("subcribe calling","<><");

                    }

                    @Override
                    public void onNext(String s) {
                        fetchDataImp.updateUi(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError calling","<><");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete calling","<><");
                    }
                });
        return respose;
    }






    @SuppressLint("CheckResult")
    private Observable<String > getObserable(String keyword) {
        return   (new RetrofitClient().getClient(APIS.BASEURL_WHETHER).create(ApiService.class))
                .getData(APIS.BASEURL_WHETHER, getParams(keyword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    private Map<String, String> getParams(String keyword) {
        Map<String, String> params = new HashMap<>();
        params.put("q",keyword);
        params.put("APPID","dcad7fe82ff933033ad138463af52ae6");
        return params;
    }
}
