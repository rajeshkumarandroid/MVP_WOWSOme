package wowsome.com.wowsome.ui.show_images;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wowsome.com.wowsome.R;
import wowsome.com.wowsome.model.loadImages.ImageRoot;
import wowsome.com.wowsome.model.loadImages.Items;
import wowsome.com.wowsome.presenter.loadImage.FetchData;
import wowsome.com.wowsome.presenter.loadImage.LoadImagePresenter;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class LoadImagesActivity extends AppCompatActivity implements FetchData {

    LoadImagePresenter presenter;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearLayoutManager mLayoutManager;
    boolean loading;
    ImageRoot data;
    private boolean scrollflag = true;
    int spage = 10;
    ImageAdapter adapter;
    ImageRoot temp_pojo;
    ArrayList<Items> items = new ArrayList<>();
    String nextPageToken = "";
    ProgressDialog progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_images);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(mLayoutManager);
        progressBar = new ProgressDialog(this);
        recyclerview.addOnScrollListener(new EndlessScrollListener(recyclerview));
        presenter = new LoadImgImpl(this);
        presenter.fetchPresenter(this, spage, nextPageToken);


    }

    @Override
    public void getResponse(String response) {
        Log.e("response is ", "<><>>" + response);
        try {
            if (!loading)

                data = new Gson().fromJson(response, ImageRoot.class);
            Log.e("images is ", "<>><" + data.getItems().get(0).getSnippet().getThumbnails().getHigh().getUrl());

            if (spage == 10) {
                temp_pojo = data;
            } else {
                items = temp_pojo.getItems();
                items.addAll(data.getItems());
                temp_pojo.setItems(items);
//                nextPageToken = data.getNextPageToken();
            }

            if (spage == 10) {
                adapter = new ImageAdapter(this, temp_pojo.getItems());
                recyclerview.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void showProgressbar() {
        progressBar.show();
    }

    @Override
    public void hideProgressbar() {
        progressBar.dismiss();
    }

    public class EndlessScrollListener extends RecyclerView.OnScrollListener {
        private RecyclerView listView;

        public EndlessScrollListener(RecyclerView listView) {
            this.listView = listView;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }

        @Override
        public void onScrollStateChanged(RecyclerView view, int scrollState) {
            //Log.e("onScrollStateChanged","<><><>");
            LinearLayoutManager layoutManager = ((LinearLayoutManager) view.getLayoutManager());
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
            if (null != data) {
                if (scrollState == 0 && scrollflag
                        && lastVisiblePosition == listView.getAdapter().getItemCount() - 1) {
                    if (!loading) {
                        loading = true;

                        spage += 10;

                        presenter.fetchPresenter(LoadImagesActivity.this, spage, nextPageToken);

                    }

                }
            }
        }
    }


}
