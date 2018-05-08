package wowsome.com.wowsome.ui.show_images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import wowsome.com.wowsome.R;
import wowsome.com.wowsome.model.QuotesPojo;
import wowsome.com.wowsome.presenter.loadImage.FetchData;
import wowsome.com.wowsome.presenter.loadImage.LoadImagePresenter;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class LoadImagesActivity extends AppCompatActivity implements FetchData {

    LoadImagePresenter presenter;
    ArrayList<String> ids = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_images);
         presenter = new LoadImgImpl(this);
        presenter.fetchPresenter(this);

    }

    @Override
    public void getResponse(String response) {
        Log.e("response is ","<><>>"+response);
        try {
           Document document = Jsoup.parse(response);

            Elements text = document.select("div").select("div.new-msnry-grid.bqcpx").select("div.m-brick.grid-item.boxy.bqQt");
            Log.e("elements", "" + text.size());
            for (Element e : text) {
                QuotesPojo pojo = new QuotesPojo();

                if (null != e.getElementsByTag("img") && e.getElementsByTag("img").attr("alt").length() != 0) {
//                        Log.e("title", "val " + e.attr("id"));
//                        Log.e("image", "val " + e.getElementsByTag("img").attr("src"));
//                        Log.e("image", "val " + e.getElementsByTag("img").attr("alt"));
//                        arl_image.add(e.getElementsByTag("img").attr("src"));
                    if (ids.contains(e.attr("id")))
                        pojo.setFav("yes");
                    else
                        pojo.setFav("no");
                    pojo.setId(e.attr("id"));
                    pojo.setMessage(e.getElementsByTag("img").attr("alt"));
                    pojo.setImage(e.getElementsByTag("img").attr("src"));
                    Log.e("image", "val " + e.getElementsByTag("img").attr("src"));


                } else {
//                    Log.e("title", "val " + e.getElementsByTag("a").text());
//                    Log.e("title", "val " + e.attr("id"));

                    if (ids.contains(e.attr("id")))
                        pojo.setFav("yes");
                    else
                        pojo.setFav("no");
                    pojo.setId(e.attr("id"));
                    pojo.setMessage(e.getElementsByTag("a").text());
                    pojo.setImage("");
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
