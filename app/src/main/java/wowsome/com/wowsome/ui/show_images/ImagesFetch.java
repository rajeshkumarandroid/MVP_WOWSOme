package wowsome.com.wowsome.ui.show_images;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;

import wowsome.com.wowsome.R;
import wowsome.com.wowsome.model.QuotesPojo;

/**
 * Created by Admin on 07-Dec-17.
 */

public class ImagesFetch extends AppCompatActivity {


    ProgressDialog mProgressDialog;
    String url;
    //    Elements elements;
    ArrayList<QuotesPojo> arl_data, arl_data_db;
    //    ArrayList<QuotesPojo> ;
    int count = 1;


    ArrayList<String> ids = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.show_images);
        Log.e("method1", "onCreate");
        arl_data = new ArrayList();
        new Title("1").execute();

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.e("method1", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("method1", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("method1", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("method1", "onStop");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("method1", "onDestroy");
    }



    // Title AsyncTask
    private class Title extends AsyncTask<Void, Void, Void> {
        String title, val;

        Title(String val) {
            this.val = val;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            String[] data;
            url = "https://www.brainyquote.com/authors/a_p_j_abdul_kalam?SPvm=1";
            if (count != 1) {
                data = url.split("\\?");
                url = data[0] + val + "?" + data[1];
            }

            if (count == 1) {
                mProgressDialog = new ProgressDialog(ImagesFetch.this);
                mProgressDialog.setTitle("Abdul Kalam Quotes");
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.show();
            } else {
//                pbr.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("url", "val@@ " + url);
            try {
                Document document = Jsoup.connect(url).get();
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

                    } else {
                        Log.e("title", "val " + e.getElementsByTag("a").text());
                        Log.e("title", "val " + e.attr("id"));
                        Log.e("image", "val " + e.getElementsByTag("a").text());
                        if (ids.contains(e.attr("id")))
                            pojo.setFav("yes");
                        else
                            pojo.setFav("no");
                        pojo.setId(e.attr("id"));
                        pojo.setMessage(e.getElementsByTag("a").text());
                        pojo.setImage("");
                    }
                    arl_data.add(pojo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            Log.e("method", "onPostExecute"+arl_data);
            mProgressDialog.dismiss();
        }
    }

}
