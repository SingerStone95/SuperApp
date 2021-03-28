package singerstone.com.superapp.Dialog;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;

public class DialogItemAdapter extends RecyclerView.Adapter<DialogItemAdapter.DialogItemViewHolder> {


    private List<IDialogItem> mItems = new ArrayList<IDialogItem>();
    private Context mContext;
    private OnItemClickListener mListener;

    public DialogItemAdapter(@NonNull Context context) {
        this.mContext = context;
    }

    @Override
    public DialogItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flexdialog_item, parent, false);
        return new DialogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DialogItemViewHolder holder, final int position) {
        final IDialogItem item = mItems.get(position);
        holder.mTextView.setText(item.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position, item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class DialogItemViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public DialogItemViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
        }
    }

    public void setData(List<IDialogItem> data) {
        mItems.clear();
        mItems.addAll(data);
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, IDialogItem item);
    }
}
