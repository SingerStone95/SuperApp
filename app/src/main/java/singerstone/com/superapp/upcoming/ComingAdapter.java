package singerstone.com.superapp.upcoming;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;

/**
 * author : yogachen
 * date   : 2019-06-26
 * desc   :
 */
public class ComingAdapter extends RecyclerView.Adapter<ComingAdapter.ComingViewHolder> {


    List<String> mData = new ArrayList<>();

    @NonNull
    @Override
    public ComingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_upcoming_item, parent, false);
        return new ComingViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(mData.get(position)).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class ComingViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ComingViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_poster);
        }
    }
}
