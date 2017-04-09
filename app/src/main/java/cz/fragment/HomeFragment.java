package cz.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.example.chenzhang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cz.adapter.MyFragmentAdapter;

/**
 * Created by chenzhang on 2017/3/13.
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    @Nullable

    private ViewPager viewpage;
    private TabLayout tap;
    private List<String> titlelist;
    private List<Fragment> fragList;
    private View view;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

         view=inflater.inflate(R.layout.home,container,false);

        initView();

        return  view;

    }

    public  void initView(){
        viewpage=(ViewPager) view.findViewById(R.id.viewpager);
        tap=(TabLayout)view.findViewById(R.id.tab);
        titlelist = new ArrayList<String>();
        titlelist.add("新鲜事");
        titlelist.add("跳蚤市场");
        fragList = new ArrayList<Fragment>();
        fragList.add(new NewsFragmment());
        fragList.add(new ShopFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), fragList, titlelist);
        viewpage.setAdapter(adapter);
        tap.setupWithViewPager(viewpage);



    }




}
