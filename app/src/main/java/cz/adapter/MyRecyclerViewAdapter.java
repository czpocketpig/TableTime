package cz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chenzhang.myapplication.R;

import java.util.List;

import cz.bean.courses;

/**
 * Created by chenzhang on 2017/3/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<String> mDatas;
    private LayoutInflater mInflater;
    private String[] num = {"一", "二", "三", "四", "五", "六", "七", "八"};
    private List<courses> mlist;


    public MyRecyclerViewAdapter(Context context, List<String> datas, List<courses> list) {
        mInflater = LayoutInflater.from(context);
        mlist = list;
        mDatas = datas;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(R.layout.item_1, parent, false));
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position == 0) {
            holder.tv1.setText("");
        } else {
            if (position <= 5) {
                holder.tv1.setText("周" + num[position - 1]);
            } else {
                if (position % 6 == 0) {
                    holder.tv1.setText("第" + num[(position / 6) - 1] + "节");
                } else {
                    setCourse(holder);
                    if (mOnItemClickLitener != null) {
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            int pos = holder.getLayoutPosition();

                            public void onClick(View v) {
                                mOnItemClickLitener.onItemClick(holder.itemView, pos);
                            }
                        });
                    }

                }

            }
        }

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv1;

        public MyViewHolder(View view) {

            super(view);
            tv1 = (TextView) view.findViewById(R.id.item1);

        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public void setCourse(MyViewHolder holder) {
        String pos = "" + holder.getLayoutPosition();
        for (int i = 0; i < mlist.size(); i++) {
            courses cr = mlist.get(i);
            if (pos.equals(cr.getPosition())) {
                holder.tv1.setText(cr.course_name);
            }


        }


    }


}
