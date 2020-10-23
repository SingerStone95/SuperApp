package singerstone.com.superapp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import singerstone.com.superapp.waveeffect.DebugTextView;

/**
 * Created by chenbinhao on 2017/7/5. YY:909075276
 */

public class ToolAdapter extends RecyclerView.Adapter<ToolAdapter.ToolHolder> {

    private Context mContext;
    private ArrayList<ToolItem> items;
    private OnItemClickListener listener;

    public ToolAdapter(Context context, ArrayList<ToolItem> items) {
        this.mContext = context;
        this.items = items;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
    }

    @Override
    public ToolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这种写法能保证xml中的layoutparam有效
        View rootView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_tool_item, parent, false);
        ToolHolder toolHolder = new ToolHolder(rootView);
        return toolHolder;
    }

    @Override
    public void onBindViewHolder(final ToolHolder holder, final int position) {
        holder.tv_tool.setText(items.get(position).getText());
        new Thread(() -> holder.iv_tool.setImageResource(items.get(position).getResourseId()))
                .start();

        holder.tv_tool.index = position;
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
        public DebugTextView tv_tool;
        public View itemView;

        public ToolHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.iv_tool = (ImageView) itemView.findViewById(R.id.iv_tool);
            this.tv_tool = (DebugTextView) itemView.findViewById(R.id.tv_tool);
        }
    }

    public interface OnItemClickListener {

        public void onItemClick(int position, View itemView);
    }


}
