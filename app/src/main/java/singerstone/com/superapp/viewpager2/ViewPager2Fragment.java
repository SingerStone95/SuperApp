package singerstone.com.superapp.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.lang.reflect.Field;

import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.ReflectUtils;

/**
 * author : yogachen
 * date   : 2019-06-17
 * desc   :
 */
public class ViewPager2Fragment extends Fragment {

    private ViewPager2 viewPager2;
    private FragmentStateAdapter adapter;

    public static ViewPager2Fragment newInstance() {
        ViewPager2Fragment fragment = new ViewPager2Fragment();
        return fragment;
    }

    public ViewPager2Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager2, container, false);
        viewPager2 = view.findViewById(R.id.vp2);

        Field field = ReflectUtils.getField(ViewPager2.class, "mRecyclerView");
        field.setAccessible(true);
        try {
            RecyclerView recyclerView = (RecyclerView) field.get(viewPager2);
            recyclerView.setItemViewCacheSize(0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        initView();
        return view;
    }

    private void initView() {
        // viewPager2.setOffscreenPageLimit(-1);
        adapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                ViewPagerChildFragment fragment = ViewPagerChildFragment.newInstance();
                fragment.setPosition(position);
                return fragment;
            }

            @Override
            public int getItemCount() {
                return 8;
            }
        };
        viewPager2.setAdapter(adapter);
    }
}
