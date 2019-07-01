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


    List<String> mData = new ArrayList<>();

    @NonNull
    @Override
    public ComingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = new ComingSoonItemView(parent.getContext());
        return new ComingViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingViewHolder holder, int position) {
        holder.comingSoonItemView.resetSize();
        holder.comingSoonItemView.setData(mData.get(position % mData.size()),"" + position % mData.size());
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
}
