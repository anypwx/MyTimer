package com.pwx.timer.mytimer;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcv_list;
    private Handler handler = new Handler();

    private static final String[] dd = {"","","s_1","s_2","s_3","s_4","s_5","s_6","s_7","s_8","s_9","s_10","s_11","s_12","s_13","s_14","s_15","s_16",
            "s_17","s_18","s_19","s_20","s_21","s_22","s_23","s_24","s_25","s_26","s_27","s_28","s_29","s_30","s_31","s_32",
            "s_33","s_34","s_35","s_36","s_37","s_38","s_39","s_40","s_41","s_42","s_43","s_44",
            "s_45","s_46","s_47","s_48","s_49","s_50","s_51","s_52","s_53","s_54","s_55","s_56","s_57","s_58","s_59","s_60","",""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rcv_list = (RecyclerView) findViewById(R.id.rcv_list);
        rcv_list.setLayoutManager(new LinearLayoutManager(this));
        rcv_list.setHasFixedSize(true);
        rcv_list.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dd,parent,false);
                ViewHolder vh = new ViewHolder(v);
                return vh;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder holderss = (ViewHolder)holder;
                holderss.tv_dd.setTextSize(14);
                holderss.tv_dd.setText(dd[position]);
                Log.i("pwx","position-- :   "+position);
            }

            @Override
            public int getItemCount() {
                return dd.length;
            }
        });

        rcv_list.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                int getAdapterPosition =  holder.getAdapterPosition();
                int getLayoutPosition =  holder.getLayoutPosition();
                int getOldPosition =  holder.getOldPosition();
                Log.i("pwx","getAdapterPosition: " + getAdapterPosition+",getLayoutPosition: "+getLayoutPosition
                +",getOldPosition"+getOldPosition);
            }
        });


        rcv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){ //停止滚动
                    //如果停止滚动，则定位listview的位置
                   final int firstVisibleItemPosition = ((LinearLayoutManager)rcv_list.getLayoutManager()).findFirstVisibleItemPosition();//获取每次在可见区域內的第一次坐标
                    int lastVisibleItemPosition = ((LinearLayoutManager)rcv_list.getLayoutManager()).findLastVisibleItemPosition();//获取每次在可见区域內的最后一次坐标
                    Log.i("pwx","firstVisibleItemPosition-- :   "+firstVisibleItemPosition+",lastVisibleItemPosition-- :   "+lastVisibleItemPosition);
                    final int kk = (firstVisibleItemPosition + lastVisibleItemPosition)/2; //取两个坐标的中间值
                    Log.i("pwx","最终位置: "+(kk+1)+"");
                    rcv_list.getAdapter().notifyDataSetChanged(); //重置list
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ViewHolder v = (ViewHolder) rcv_list.findViewHolderForAdapterPosition(kk); //获取对应坐标的ViewHolder
                            v.tv_dd.setTextSize(25);
                            Toast.makeText(getApplicationContext(),v.tv_dd.getText(),Toast.LENGTH_SHORT).show();
                            ///这里的定位需要解释一下
                            /// 因为当滑动listvview停止的时候需要固定某个item，有一种被选中的视感
                            /// 所以就拿firstVisibleItemPosition 作为定位点
                            /// 但实际取的值却是可视区域中间的那个坐标的值 也就是 kk
                            /// 再次说明一下，这里的定位纯粹是为了视觉上的假象的，然后也是为了
                            /// 固定一个item，不固定的话，就会有出现一半item的现象///
                            rcv_list.scrollToPosition(firstVisibleItemPosition);
                        }
                    },500);
//                    ViewHolder v = (ViewHolder) rcv_list.findViewHolderForAdapterPosition(kk);
//                    Toast.makeText(getApplicationContext(),v.tv_dd.getText(),Toast.LENGTH_SHORT).show();
//                    rcv_list.scrollToPosition(firstVisibleItemPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_dd;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_dd = (TextView) itemView.findViewById(R.id.tv_dd);

        }
    }
}
