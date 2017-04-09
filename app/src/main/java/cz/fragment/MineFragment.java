package cz.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenzhang.myapplication.R;

/**
 * Created by chenzhang on 2017/3/13.
 */

public class MineFragment extends android.support.v4.app.Fragment {
    @Nullable
    private TextView textView;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        Log.d("TEST","3");

        return inflater.inflate(R.layout.mine,container,false);

    }
}
