package wowsome.com.wowsome.ui.whether;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import wowsome.com.wowsome.R;
import wowsome.com.wowsome.model.WhetherReport;
import wowsome.com.wowsome.presenter.whether.FetchDataImp;
import wowsome.com.wowsome.presenter.whether.WhetherPresenter;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class WhetherActivity extends AppCompatActivity implements FetchDataImp {

    @BindView(R.id.txt_city_name)
    TextView txt_city_name;
    @BindView(R.id.txt_description)
    TextView txt_description;
    @BindView(R.id.txt_wind)
    TextView txt_wind;
    @BindView(R.id.txt_country)
    TextView txt_country;
    @BindView(R.id.edttxt)
    EditText edttxt;
    @BindView(R.id.txt_temp)
    TextView txt_temp;
    @BindView(R.id.txt_humidity)
    TextView txt_humidity;
    WhetherPresenter whetherPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whether_activity);
        ButterKnife.bind(this);
        whetherPresenter = new WhetherImpl(this);

        edttxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 3) {
                    whetherPresenter.updateView(WhetherActivity.this, charSequence.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    public void updateUi(WhetherReport report) {
        Log.e("whether is ", "<><>>" + report.getWeather()[0].getDescription());
        txt_description.setText("Description: " + report.getWeather()[0].getDescription());
        txt_city_name.setText("City: " + report.getName());
        txt_wind.setText("Wind Speed: " + report.getWind().getSpeed());
        txt_country.setText("Country: " + report.getSys().getCountry());
        txt_temp.setText("Temp: " + report.getMain().getTemp());
        txt_humidity.setText("Humidity: " + report.getMain().getHumidity());
    }
}
