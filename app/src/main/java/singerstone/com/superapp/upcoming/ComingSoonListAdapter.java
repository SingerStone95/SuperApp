package singerstone.com.superapp.upcoming;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * author : yogachen
 * date   : 2019-06-26
 * desc   :
 */
public class ComingSoonListAdapter extends RecyclerView.Adapter<ComingSoonListAdapter.ComingViewHolder> {


    private List<String> mData = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    @NonNull
    @Override

    public ComingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = new ComingSoonItemView(parent.getContext());
        return new ComingViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingViewHolder holder, int position) {
        if (mData.size() == 0) {
            return;
        }
        holder.comingSoonItemView.resetSize();
        int realPosition = position % mData.size();
        holder.comingSoonItemView.setData(mData.get(realPosition), "" + position % mData.size());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position, realPosition);
                }
            }
        });
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
        ComingSoonItemView comingSoonItemView;

        public ComingViewHolder(@NonNull View itemView) {
            super(itemView);
            comingSoonItemView = (ComingSoonItemView) itemView;
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

    public void setItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int realPosition);
    }
}
