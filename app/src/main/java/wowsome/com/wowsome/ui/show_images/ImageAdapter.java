package wowsome.com.wowsome.ui.show_images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import wowsome.com.wowsome.R;
import wowsome.com.wowsome.model.loadImages.Items;
import wowsome.com.wowsome.utils.Image_Fetch;

/**
 * Created by Rajesh Kumar on 09-05-2018.
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MyViewHolder holder;
    ArrayList<Items> data;
    Context context;
    public ImageAdapter(Context context,ArrayList<Items> data){
        this.data = data;
        this.context = context;


    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_view)
        ImageView img_view;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

          View  root = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
            return new MyViewHolder(root);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position) {

        Image_Fetch.getInstance().LoadImage(context,((MyViewHolder)holder).img_view,data.get(position).getSnippet().getThumbnails().getHigh().getUrl());



    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




}
