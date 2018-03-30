package com.timothy.optifind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BusinessList extends AppCompatActivity {

    private RecyclerView mBizList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Business_list");
        mDatabase.keepSynced(true);

        mBizList = (RecyclerView) findViewById(R.id.display_list);
        mBizList.setHasFixedSize(true);
        mBizList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Biz, BizListView> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Biz, BizListView>(

                Biz.class,
                R.layout.biz_layout,
                BizListView.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BizListView viewHolder, Biz model, int position) {

                viewHolder.setBizName(model.getBizName());
                viewHolder.setBizLocation(model.getBizLocation());
                viewHolder.setBizCategory(model.getBizCategory());
            }
        };

        mBizList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BizListView extends  RecyclerView.ViewHolder{

        View mView;

        public BizListView(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setBizName(String bizName){

            TextView post_name = (TextView) mView.findViewById(R.id.name_post);
            post_name.setText(bizName);
        }

        public void setBizLocation(String bizLocation){

            TextView post_location = (TextView) mView.findViewById(R.id.location_post);
            post_location.setText(bizLocation);
        }

        public void setBizCategory(String bizCategory){

            TextView post_category = (TextView) mView.findViewById(R.id.category_post);
            post_category.setText(bizCategory);
        }
    }
}
