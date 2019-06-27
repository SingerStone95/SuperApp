package singerstone.com.superapp.upcoming;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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
        Glide.with(holder.itemView.getContext()).load(mData.get(position % mData.size())).into(holder.mImageView);
        holder.mTvIndex.setText("" + position % mData.size());
    /*    if (position % 2 == 0) {
            holder.itemView.getLayoutParams().width = dp2px(holder.itemView.getContext(), 400);
            holder.mImageView.getLayoutParams().width = dp2px(holder.itemView.getContext(), 400);
        }*/
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

        private ImageView mImageView;
        private TextView mTvIndex;

        public ComingViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_poster);
            mTvIndex = itemView.findViewById(R.id.tv_index);
        }
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
