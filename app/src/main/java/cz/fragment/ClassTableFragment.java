package cz.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.chenzhang.myapplication.R;
import com.wrbug.editspinner.EditSpinner;
import com.wrbug.editspinner.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import cz.adapter.MyRecyclerViewAdapter;
import cz.utils.RecyclerDivider;

import static android.content.ContentValues.TAG;


/**
 * Created by chenzhang on 2017/3/13.
 */

public class ClassTableFragment extends android.support.v4.app.Fragment {
    @Nullable
//    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
//    private final int FP = ViewGroup.LayoutParams.MATCH_PARENT;
    private View view;
    private RecyclerView recyclerView;
    private List<String> mDatas;
    private  MyRecyclerViewAdapter mAdapter;
    private  EditSpinner editSpinner;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.classtable, container, false);
        initData();
        initView();
        initEvent();

        return view;

    }

       public void initView(){

           recyclerView= (RecyclerView) view.findViewById(R.id.id_recyclerview);
           mAdapter = new MyRecyclerViewAdapter(getActivity(), mDatas);
           recyclerView.setLayoutManager(new StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL));
           recyclerView.setAdapter(mAdapter);
           recyclerView.addItemDecoration(new RecyclerDivider(getActivity()));

       }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 54; i++)
        {
            mDatas.add("" + i);
        }
    }
    private void initEvent()
    {
        mAdapter.setOnItemClickLitener(new MyRecyclerViewAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " click",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onItemClick: " + position);
                doDailog();

            }
        });
    }


     public  void doDailog(){

         AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
         View view = View.inflate(getActivity(), R.layout.dailog, null);
         editSpinner = (EditSpinner) view.findViewById(R.id.editSpinner1);
         SimpleAdapter simpAdapter=new SimpleAdapter(getContext(),mDatas);
         editSpinner.setAdapter(simpAdapter);



         builder.setView(view);
         builder.setIcon(R.drawable.book);
         builder.setTitle("选课");





         //确认按钮
         builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface arg0, int arg1) {
                 // TODO Auto-generated method stub
                 Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
             }
         });
         //取消
         builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

             @Override
             public void onClick(DialogInterface arg0, int arg1) {
                 // TODO Auto-generated method stub

             }
         });
         final AlertDialog dialog = builder.create();
         dialog.show();

     }
















    private void initView2() {
        Log.d("TAG", "initView: ");
//        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table);
//        tableLayout.setStretchAllColumns(true);
//        tableLayout.setDividerDrawable(getResources().getDrawable(R.drawable.item_divider));//这个就是中间的虚线
//        tableLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);//设置分割线为中间显示
//
//        for (int row = 0; row < 9; row++) {
//            TableRow tableRow = new TableRow(getActivity());
//
//
//            for (int col = 0; col < 6; col++) {
//                //tv用于显示
//                TextView tv = new TextView(getActivity());
//                tv.setHeight(100);
//
//
//                if (col == 0 && row == 0) {
//                    tv.setText("1");
//
//                    tableRow.addView(tv);
//                }
//                 else{
//                    if (col != 0 && row != 0)
//                    {
//                        tv.setText("2");
//                        tv.setBackgroundColor(Color.BLUE);
//                        tv.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(getContext(),"TEST",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        tableRow.addView(tv);
//
//                    }
//                    else{
//                        if (col == 0 && row != 0)
//                        {
//                            tv.setText("第" +num[row-1]+"节");
//                           tv.setTop(10);
//                            tableRow.addView(tv);
//
//
//                        }
//                        else{
//
//                            tv.setText("周"+num[col-1]);
//                            tableRow.addView(tv);
//                        }
//                    }
//
//
//
//                }
//
//
//            }
//            //新建的TableRow添加到TableLayout
//            tableLayout.addView(tableRow, new TableLayout.LayoutParams(FP, WC,1));
//        }


    }

    public void addClasses(TextView tv) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "" + v.getId(), Toast.LENGTH_LONG).show();
            }


        });


    }


}
