package wowsome.com.wowsome.presenter.loadImage;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public interface LoadImagePresenter extends FetchData {

    void fetchPresenter(FetchData fetchData,int spage,String nextpagetoken);

}
