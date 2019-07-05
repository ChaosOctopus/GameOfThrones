package cn.zyc.gameofthrones;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.zyc.baratheon.release.BaratheonFragment;
import cn.zyc.lannister.release.LannisterFragment;
import cn.zyc.stark.release.StarkFragment;
import cn.zyc.targaryen.release.TargaryenFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_main);
        mTabLayout = findViewById(R.id.tb_main);

        titles.add("狼");
        titles.add("狮");
        titles.add("龙");
        titles.add("鹿");

        fragments.add(new StarkFragment());
        fragments.add(new LannisterFragment());
        fragments.add(new TargaryenFragment());
        fragments.add(new BaratheonFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

        });
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.removeAllTabs();
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)).setIcon(R.drawable.stark));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)).setIcon(R.drawable.lannister));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)).setIcon(R.drawable.targaryen));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(3)).setIcon(R.drawable.balathean));


    }
}
