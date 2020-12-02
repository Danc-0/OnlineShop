package com.danc.onlineshop.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.danc.onlineshop.MainActivity;
import com.danc.onlineshop.R;

import java.util.HashMap;
import java.util.UUID;

public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";
    TextInputLayout fullName, emailAddress, userAge, firstPwd, secondPwd;
    Button btnRegister;
    TextView toLogin;
    float v = 0;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;

    String randomUUID = UUID.randomUUID().toString();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        fullName = view.findViewById(R.id.fullName);
        emailAddress = view.findViewById(R.id.emailAddress);
        userAge = view.findViewById(R.id.age);
        firstPwd = view.findViewById(R.id.firstPwd);
        secondPwd = view.findViewById(R.id.confirmPwd);
        btnRegister = view.findViewById(R.id.btnSignUp);
        toLogin = view.findViewById(R.id.tvToLogin);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("UserData");
        mAuth = FirebaseAuth.getInstance();

        animateViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEditFields();
            }
        });

        return view;
    }

    private void animateViews(){
        fullName.setTranslationY(800);
        emailAddress.setTranslationY(800);
        userAge.setTranslationY(800);
        firstPwd.setTranslationY(800);
        secondPwd.setTranslationY(800);
        btnRegister.setTranslationY(800);
        toLogin.setTranslationY(800);

        fullName.setAlpha(v);
        emailAddress.setAlpha(v);
        userAge.setAlpha(v);
        firstPwd.setAlpha(v);
        secondPwd.setAlpha(v);
        btnRegister.setAlpha(v);
        toLogin.setAlpha(v);

        fullName.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        emailAddress.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        userAge.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(800).start();
        firstPwd.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(1000).start();
        secondPwd.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(1200).start();
        btnRegister.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(1400).start();
        toLogin.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(1600).start();
    }

    private void checkEditFields(){
        if (TextUtils.isEmpty(fullName.getEditText().getText())
        || TextUtils.isEmpty(emailAddress.getEditText().getText())
        || TextUtils.isEmpty(userAge.getEditText().getText())
        || TextUtils.isEmpty(firstPwd.getEditText().getText())
        || TextUtils.isEmpty(secondPwd.getEditText().getText())) {

            Toast.makeText(getContext(), "All Fields are filled", Toast.LENGTH_SHORT).show();

        } else if (Integer.parseInt(userAge.getEditText().getText().toString()) >= 18){
            Log.d(TAG, "checkEditFields: Authorized User ");
            saveUserData();
        } else {
            Toast.makeText(getContext(), "Please check all your details", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserData(){
        String name = fullName.getEditText().getText().toString();
        String addressEmail = emailAddress.getEditText().getText().toString();
        String age = userAge.getEditText().getText().toString();

        HashMap<String, Object> userProfiles = new HashMap<>();
        userProfiles.put("name", name);
        userProfiles.put("email", addressEmail);
        userProfiles.put("age", age);

        mDatabaseReference.child(randomUUID).setValue(userProfiles).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "onComplete: User Data Saved");
                    registerUser();
                } else {
                    Toast.makeText(getContext(), "Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registerUser(){
        String email = emailAddress.getEditText().getText().toString();
        String password = firstPwd.getEditText().getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Unable to Register please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isEmpty(String s1){
        return s1.equals("");
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Register First", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
}
