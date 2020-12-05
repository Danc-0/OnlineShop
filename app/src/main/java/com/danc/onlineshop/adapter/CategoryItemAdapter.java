package com.danc.onlineshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.onlineshop.R;
import com.danc.onlineshop.model.AlcoholItemModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ItemViewHolder> {

    private static final String TAG = "CategoryItemAdapter";
    Context context;
    List<AlcoholItemModel> itemModels = new ArrayList<>();
    List<String> itemModels1 = new ArrayList<>();
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener mChildEventListener;

    public CategoryItemAdapter(Context context, final List<String> itemModels) {
        this.context = context;
        this.itemModels1 = itemModels;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Alcohol").child("Category");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                String itemModel = snapshot.getValue(String.class);
//                itemModels1.add(itemModel);
//                Log.d(TAG, "onChildAdded: Items Category " + itemModel);

                Log.d(TAG, "onChildAdded: Data " + snapshot.getKey());
                for (DataSnapshot ds : snapshot.getChildren()){
                    List<String> key = Collections.singletonList(ds.getKey());
                    Log.d(TAG, "onChildAdded: +++ " + key);
//                    Log.d(TAG, "onChildAdded: Hello hello " + itemModels1.add(key));
//                    itemModels1.add(key);
                }

                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String alcoholItemModel = itemModels1.get(position);
        holder.bindData(alcoholItemModel);
        holder.productCategory.setText(alcoholItemModel);
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView productCategory;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productCategory = itemView.findViewById(R.id.cat_name);
        }

        public void bindData(String alcoholItemModel) {
            productCategory.setText(alcoholItemModel);

        }
    }


}
