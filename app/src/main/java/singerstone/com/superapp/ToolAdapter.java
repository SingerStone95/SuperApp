package singerstone.com.superapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chenbinhao on 2017/7/5.
 * YY:909075276
 */

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ToolHolder> {
    private Context mContext;
    private ArrayList<ToolItem> items;
    private OnItemClickListener listener;

    public ToolAdapter(Context context, ArrayList<ToolItem> items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public ToolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ToolHolder toolHolder = new ToolHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_tool_item, null));
        return toolHolder;
    }

    @Override
    public void onBindViewHolder(final ToolHolder holder, final int position) {
        holder.tv_tool.setText(items.get(position).getText());
        holder.iv_tool.setBackgroundResource(items.get(position).getResourseId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolAdapter.this.listener != null) {
                    listener.onItemClick(position, holder.itemView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(ArrayList<ToolItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ToolHolder extends RecyclerView.ViewHolder {
        public ImageView iv_tool;
        public TextView tv_tool;
        public View itemView;

        public ToolHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.iv_tool = (ImageView) itemView.findViewById(R.id.iv_tool);
            this.tv_tool = (TextView) itemView.findViewById(R.id.tv_tool);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(int position, View itemView);
    }


}
