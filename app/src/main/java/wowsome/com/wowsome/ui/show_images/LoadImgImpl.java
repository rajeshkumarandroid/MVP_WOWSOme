package wowsome.com.wowsome.ui.show_images;

import wowsome.com.wowsome.model.FetchCitiesList;
import wowsome.com.wowsome.presenter.loadImage.FetchData;
import wowsome.com.wowsome.presenter.loadImage.LoadImagePresenter;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class LoadImgImpl implements LoadImagePresenter {
    FetchData fetchData;

    public LoadImgImpl(FetchData fetchData){
        this.fetchData = fetchData;
    }


    @Override
    public void fetchPresenter(FetchData fetchData) {
        FetchCitiesList.getInstance().getImageData(fetchData);
    }

    @Override
    public void getResponse(String response) {
        fetchData.getResponse(response);

    }
}
