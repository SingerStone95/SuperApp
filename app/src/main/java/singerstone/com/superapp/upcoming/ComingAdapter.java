package singerstone.com.superapp.upcoming;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.DimentionUtils;

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
        View rootView = new UpComingItemView(parent.getContext());
        return new ComingViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingViewHolder holder, int position) {
        holder.upComingItemView.resetSize();
        holder.upComingItemView.setData(mData.get(position % mData.size()),"" + position % mData.size());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class ComingViewHolder extends RecyclerView.ViewHolder {
        UpComingItemView upComingItemView;

        public ComingViewHolder(@NonNull View itemView) {
            super(itemView);
            upComingItemView = (UpComingItemView) itemView;
        }
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onViewRecycled(ComingViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
