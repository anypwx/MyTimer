# MyTimer
是一个简单仿时间滑动选择器

# 温馨提示
此demo是用于实现原理的简单演示，需要者可以clone源码查看。
核心代码：控制item 显示在中间（实现的方式比较取巧，大家勿喷）

```
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
```




#效果图

