package com.sivin.learnndk;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sivin.learnndk.lesson01.Lesson01Operator;
import com.sivin.learnndk.lesson02.Lesson02Operator;
import com.sivin.learnndk.lesson03.Lesson03Operator;
import com.sivin.learnndk.lesson04.Lesson04Operator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    static {
        System.loadLibrary("learnNdk");
    }


    private Context mContext;
    private List<String> mDataList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<MyHolder> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        mContext = this;
        mDataList = new ArrayList<>();
        mDataList.add("less01->获取java对象属性");
        mDataList.add("less02->JNI回调java层的函数");
        mDataList.add("less03->JNI数组和异常操作");
        mDataList.add("less04->JNI局部引用全局引用和释放");

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

        mRecyclerView.addItemDecoration(
                new DefaultItemDecoration(this, DefaultItemDecoration.VERTICAL_LIST,0));


        mAdapter = new RecyclerView.Adapter<MyHolder>() {
            @Override
            public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(MyHolder holder, final int position) {

                final int adapterPosition = holder.getAdapterPosition();

                holder.title.setText(mDataList.get(adapterPosition));

                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick(adapterPosition);
                    }
                });

            }
            @Override
            public int getItemCount() {
                return mDataList.size();
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }



    private void initEvent() {

    }


    class MyHolder extends RecyclerView.ViewHolder {
        private TextView title;
        MyHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.id_item_text);
        }
    }


    private void onItemClick(int position) {
        switch (position){
            case 0: new Lesson01Operator().performClick();break;
            case 1: new Lesson02Operator().performClick();break;
            case 2: new Lesson03Operator().performClick();break;
            case 3: new Lesson04Operator().performClick();break;
        }
    }
}
