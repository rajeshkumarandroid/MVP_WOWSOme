package wowsome.com.wowsome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wowsome.com.wowsome.model.DashBoardPreImpl;
import wowsome.com.wowsome.network.APIResponse;
import wowsome.com.wowsome.network.APIS;
import wowsome.com.wowsome.network.RetrofitClient;
import wowsome.com.wowsome.presenter.DashboardPresenter;
import wowsome.com.wowsome.presenter.Launcher;
import wowsome.com.wowsome.ui.show_images.LoadImagesActivity;
import wowsome.com.wowsome.ui.whether.WhetherActivity;

public class MainActivity extends AppCompatActivity implements Launcher {
    String lat = "16.989065", lng = "82.247465";
    @BindView(R.id.btn_images)
    Button btn_images;
    @BindView(R.id.btn_whether)
    Button btn_whether;


    DashboardPresenter dashboardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dashboardPresenter = new DashBoardPreImpl(MainActivity.this);


    }

    @OnClick(R.id.btn_images)
    public void fetchImages() {
        dashboardPresenter.launchScreen(btn_images);
    }
    @OnClick(R.id.btn_whether)
    public void fetchWhetherReport(){
        dashboardPresenter.launchScreen(btn_whether);
    }

    private void callingGet() {
        RetrofitClient.getInstance().getEndPoint(this, "").getResult(getParams(), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("response is ", "<>success<>><" + res);
            }

            @Override
            public void onFailure(String res) {
                Log.e("response is ", "<>failure<>><" + res);
            }
        });
    }

    private void callingPost() {
        RetrofitClient.getInstance().getEndPoint(this, "").postResult(postParams(), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("post response is ", "<><success><" + res);
            }

            @Override
            public void onFailure(String res) {
                Log.e("post response is ", "<><fail><" + res);
            }
        });
    }


    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("action", APIS.GettingResDataBasedOnLat);
        params.put("LatLng", lat + "," + lng);

        return params;
    }


    private Map<String, String> postParams() {
        Map<String, String> params = new HashMap<>();
        params.put("go", APIS.uccfm_app_common_dtls);
        params.put("action", APIS.uccfm_app_common_dtls);
        params.put("field_executive_id", "" + 227);
        params.put("api_id", "uccfm2018v1.0");

        return params;
    }

    @Override
    public void launcher(int screen_number) {
        switch (screen_number) {
            case 1:
//                Toast.makeText(this, "Whether Report Under consrtuction", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, WhetherActivity.class));
                break;
            case 2:
//                Toast.makeText(this, "Images Under consrtuction", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoadImagesActivity.class));
                break;

        }

    }
}
