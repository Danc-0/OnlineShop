package com.danc.onlineshop.adapter.listItemsAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.danc.onlineshop.R;
import com.danc.onlineshop.Utils.FirebaseUtil;
import com.danc.onlineshop.model.AlcoholItemModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WhiskeyAdapter extends RecyclerView.Adapter<WhiskeyAdapter.WhiskeyViewHolder> {
        private static final String TAG = "SingleProductAdapter";
        Context context;
        List<AlcoholItemModel> alcoholItemModels = new ArrayList<>();
        FirebaseDatabase mFirebaseDatabase;
        DatabaseReference mDatabaseReference;
        ChildEventListener mChildEventListener;

        public WhiskeyAdapter(Context context, final List<AlcoholItemModel> alcoholItemModels) {
            this.context = context;
            this.alcoholItemModels = alcoholItemModels;

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseReference = FirebaseUtil.getDatabaseReference().child("Category").child("Vodka");

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    AlcoholItemModel alcoholItemModel = snapshot.getValue(AlcoholItemModel.class);
                    alcoholItemModel.setId(snapshot.getKey());
                    alcoholItemModels.add(alcoholItemModel);

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
        public WhiskeyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_product_design, parent, false);
            return new WhiskeyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull WhiskeyViewHolder holder, int position) {
            AlcoholItemModel alcoholItemModel = alcoholItemModels.get(position);
            holder.bindData(alcoholItemModel);
        }

        @Override
        public int getItemCount() {
            return alcoholItemModels.size();
        }

        public static class WhiskeyViewHolder extends RecyclerView.ViewHolder {

            ImageView productImage, favouriteImage;
            TextView prodQuantity, prodPrice, prodTitle;

            public WhiskeyViewHolder(@NonNull View itemView) {
                super(itemView);

                productImage = itemView.findViewById(R.id.prod_image);
                favouriteImage = itemView.findViewById(R.id.favouriteImg);
                prodQuantity = itemView.findViewById(R.id.prod_qty);
                prodPrice = itemView.findViewById(R.id.prod_price);
                prodTitle = itemView.findViewById(R.id.prod_name);
            }

            public void bindData(AlcoholItemModel alcoholItemModel) {
                prodQuantity.setText(alcoholItemModel.getSize());
                prodPrice.setText(alcoholItemModel.getPrice());
                prodTitle.setText(alcoholItemModel.getName());
            }
        }

    }
