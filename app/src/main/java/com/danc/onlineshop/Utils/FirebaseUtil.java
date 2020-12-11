package com.danc.onlineshop.Utils;

import android.util.Log;

import com.danc.onlineshop.model.AlcoholItemModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FirebaseUtil {

    private static final String TAG = "FirebaseUtil";
    //Create an object of Firebase Database and Database Reference
    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mDatabaseReference;
    public static List<AlcoholItemModel> listData;

    //Create a class of that Firebase Object
    public static FirebaseDatabase getDatabase() {
        //Start Work here
        if (mFirebaseDatabase != null) {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        } else if (mFirebaseDatabase == null) {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
//            mFirebaseDatabase.setPersistenceEnabled(true);
        }
        return mFirebaseDatabase;
    }

    public static DatabaseReference getDatabaseReference() {
        if (mDatabaseReference == null) {
            mDatabaseReference = getDatabase().getReference().child("Alcohol");
            Log.d(TAG, "getDatabaseReference: Database is null");

//            mDatabaseReference = getDatabase().getReference().child("Alcohol");
//            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
//                            AlcoholItemModel l = npsnapshot.getValue(AlcoholItemModel.class);
//                            listData.add(l);
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

        }
        return mDatabaseReference;
    }
}
