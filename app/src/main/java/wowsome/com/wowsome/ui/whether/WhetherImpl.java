package wowsome.com.wowsome.ui.whether;

import com.google.gson.Gson;

import wowsome.com.wowsome.model.FetchCitiesList;
import wowsome.com.wowsome.model.WhetherReport;
import wowsome.com.wowsome.presenter.whether.FetchDataImp;
import wowsome.com.wowsome.presenter.whether.WhetherPresenter;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class WhetherImpl implements WhetherPresenter {
    FetchDataImp fetchDataImp;

    public WhetherImpl(FetchDataImp fetchDataImp){
        this.fetchDataImp = fetchDataImp;
    }
    @Override
    public void updateView(FetchDataImp fetchDataImp,String keyword) {
       FetchCitiesList.getInstance().fetchCitiesList( this,keyword);
    }

    @Override
    public void updateUi(String s) {
        fetchDataImp.updateUi( new Gson().fromJson(s,WhetherReport.class));
    }


}
