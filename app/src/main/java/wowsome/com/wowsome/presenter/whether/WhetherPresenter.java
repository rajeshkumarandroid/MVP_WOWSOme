package wowsome.com.wowsome.presenter.whether;

import wowsome.com.wowsome.model.WhetherReport;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public interface WhetherPresenter {
    void updateView(FetchDataImp fetchDataImp,String keyword);
    void updateUi(String response);
}
