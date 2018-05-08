package wowsome.com.wowsome.model;

import android.content.Context;
import android.view.View;

import wowsome.com.wowsome.R;
import wowsome.com.wowsome.presenter.DashboardPresenter;
import wowsome.com.wowsome.presenter.Launcher;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class DashBoardPreImpl implements DashboardPresenter {

    private  Launcher launcher;

    public DashBoardPreImpl(Launcher launcher){
       this.launcher = launcher;
    }


    @Override
    public void launchScreen(View view) {

        switch (view.getId()){
            case R.id.btn_whether:
                launcher.launcher(1);
                break;
            case R.id.btn_images:
                launcher.launcher(2);
                break;
        }

    }
}
