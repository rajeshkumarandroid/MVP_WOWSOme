package wowsome.com.wowsome.model;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wowsome.com.wowsome.network.APIS;
import wowsome.com.wowsome.network.ApiService;
import wowsome.com.wowsome.network.RetrofitClient;
import wowsome.com.wowsome.presenter.loadImage.FetchData;
import wowsome.com.wowsome.presenter.whether.WhetherPresenter;
import wowsome.com.wowsome.ui.show_images.LoadImagesActivity;
import wowsome.com.wowsome.utils.ConnectivityManager;

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
                            Log.e("subcribe calling", "<><");

                        }

                        @Override
                        public void onNext(String s) {
                            fetchDataImp.updateUi(s);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("onError calling", "<><");
                        }

                        @Override
                        public void onComplete() {
                            Log.e("onComplete calling", "<><");
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



    public void getImageData(final FetchData fetchData,int spage,String nextpagetoken){


        if(ConnectivityManager.getInstance().isConnectingToInternet((LoadImagesActivity)fetchData)) {
            fetchData.showProgressbar();
            fetchImageData(spage, nextpagetoken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e("subcribe calling", "<><>");

                        }

                        @Override
                        public void onNext(String s) {
//                        Log.e("onNext calling","<><>"+s);
                            fetchData.getResponse(s);
                            fetchData.hideProgressbar();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("onError calling", "<><>");
                        }

                        @Override
                        public void onComplete() {
                            Log.e("onComplete calling", "<><>");
                        }
                    });
        }else{
            Toast.makeText((LoadImagesActivity)fetchData,"Please check your internet Connection",Toast.LENGTH_LONG).show();
        }



    }


    private Observable<String > fetchImageData(int spage,String nextpagetoken){
        return   (new RetrofitClient().getClient(APIS.YoutubeAPi).create(ApiService.class))
                .getData(APIS.YoutubeAPi, getImageParams(spage,nextpagetoken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }





    private Map<String ,String > getImageParams(int spage,String nextpagetoken){
        Map<String ,String > params = new HashMap<>();
//        params.put("SPvm",spage);
        params.put("part","snippet");
        params.put("q","News");
        params.put("type","video");
        params.put("key","AIzaSyANC7YXqT-P7JYbsFShIl_6dhAEdkfYIEo");
        params.put("maxResults",""+spage);
        params.put("pageToken","");

        return params;
    }

}
