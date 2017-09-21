package com.xl.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xl.test.Adapter.RecyleViewAdapter;
import com.xl.test.Base.BaseActivity;
import com.xl.test.Engin.TYPE_HOME;
import com.xl.test.Interface.Visitable;
import com.xl.test.Listener.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        Log.d(TAG, "initView: "+10/2*5);


    }

    @Override
    protected void loadData() {
        List<Visitable> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new TYPE_HOME(""));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyleViewAdapter(list));
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder v) {
                startActivity();
            }

            @Override
            protected void onItemLongClick(RecyclerView.ViewHolder v) {

            }


        });

    }


    @Override
    protected void startActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void BackPressed() {
        super.BackPressed();
        moveTaskToBack(true);
    }
}
