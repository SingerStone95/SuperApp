package singerstone.com.superapp.upcoming;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemHorDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemHorDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getLayoutManager().getPosition(view) == 0) {
            outRect.left = 0;
        } else {
            outRect.left = space;
        }
    }
}