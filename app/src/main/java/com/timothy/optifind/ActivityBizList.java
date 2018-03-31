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

public class ActivityBizList extends AppCompatActivity {

    private RecyclerView mBizList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biz_list);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Business_details");
        mDatabase.keepSynced(true);

        mBizList = findViewById(R.id.biz_list);
        mBizList.setHasFixedSize(true);
        mBizList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Biz, ActivityBizList.BizListHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Biz, ActivityBizList.BizListHolder>(

                Biz.class,
                R.layout.biz_row,
                BizListHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(ActivityBizList.BizListHolder viewHolder, Biz model, int position) {

                viewHolder.setBizName(model.getBizName());
                viewHolder.setBizLocation(model.getBizLocation());
                viewHolder.setBizCategory(model.getBizCategory());
                viewHolder.setBizSubcategory(model.getBizSubcategory());
            }
        };

        mBizList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BizListHolder extends RecyclerView.ViewHolder{

        View mView;
        public BizListHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setBizName(String bizName){

            TextView post_name = mView.findViewById(R.id.nameOfBiz);
            post_name.setText(bizName);
        }

        public void setBizLocation(String bizLocation){

            TextView post_location = mView.findViewById(R.id.LocationOfBiz);
            post_location.setText(bizLocation);
        }

        public void setBizCategory(String bizCategory){

            TextView post_category = mView.findViewById(R.id.categoryOfBiz);
            post_category.setText(bizCategory);
        }

        public void setBizSubcategory(String bizSubcategory){

            TextView post_subcategory = mView.findViewById(R.id.subcategoryOfBiz);
            post_subcategory.setText(bizSubcategory);
        }
    }
}
