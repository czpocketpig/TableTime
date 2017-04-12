package cz.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenzhang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cz.MyApplication;
import cz.adapter.MyRecyclerViewAdapter;
import cz.bean.Pingjia;
import cz.bean.courses;
import cz.utils.RecyclerDivider;


/**
 * Created by chenzhang on 2017/3/13.
 */

public class ClassTableFragment extends android.support.v4.app.Fragment {
    @Nullable

    private View view;
    private RecyclerView recyclerView;
    private List<String> mitems;
    private MyRecyclerViewAdapter mAdapter;
    private EditText pingjia;
    private String class_id;
    private List<courses> list;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classtable, container, false);
        initItemCount();
        initData();
        initView();
        return view;

    }

    public void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.id_timetable);
    }

    protected void initItemCount() {
        mitems = new ArrayList<String>();
        for (int i = 0; i < 54; i++) {
            mitems.add("" + i);
        }


    }

    public void initData() {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("stu_info", Context.MODE_PRIVATE);
        class_id = sharedPreferences.getString("classid", "");
        String bql = "select * from courses where class_id='" + class_id + "'";
        new BmobQuery<courses>().doSQLQuery(bql, new SQLQueryListener<courses>() {
            @Override

            public void done(BmobQueryResult<courses> result, BmobException e) {

                if (e == null) {
                    list = (List<courses>) result.getResults();
                    setAdapter();
                    Log.i("smile", "查询成功" + list.size());
                } else {
                    Log.i("smile", "查询成功，无数据返回");
                }
            }


        });


    }


    private void setAdapter() {
        mAdapter = new MyRecyclerViewAdapter(getActivity(), mitems, list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new RecyclerDivider(getActivity()));
        initEvent();
    }


    private void initEvent() {
        mAdapter.setOnItemClickLitener(new MyRecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), position + " click",
                        Toast.LENGTH_SHORT).show();
                doDailog();

            }
        });
    }


    public void doDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dailog, null);
        pingjia = (EditText) view.findViewById(R.id.PJ);

        builder.setView(view);
        builder.setIcon(R.drawable.book);
        builder.setTitle("让我们一起吐槽吧");
        //确认按钮
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                String content_pj = pingjia.getText().toString().trim();
                if (content_pj == null || "".equals(content_pj)) {
                    Toast.makeText(MyApplication.getContext(), "你还没留下什么呢…(⊙＿⊙；)…", Toast.LENGTH_SHORT).show();

                } else {
                    Pingjia pj = new Pingjia();
                    pj.setContent(pingjia.getText().toString());
                    pj.setStudent_id("12013054066");
                    pj.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                Toast.makeText(MyApplication.getContext(), "吐槽成功\\(^o^)/YES!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MyApplication.getContext(), "额，好像出现了点小问题(@_@;)" + e, Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });
        //取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                arg0.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }


}
