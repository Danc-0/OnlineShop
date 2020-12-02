package com.danc.onlineshop.Fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.danc.onlineshop.MainActivity;
import com.danc.onlineshop.R;

public class LoginFragment extends Fragment {

    TextInputLayout email, password;
    TextView forgotPwd;
    Button btnLogin;
    FirebaseAuth mAuth;
    private String mail;
    private String pwd;

    float v = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        forgotPwd = view.findViewById(R.id.tvForgot);
        btnLogin = view.findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });

        animate();

        return view;
    }

    private void animate() {
        email.setTranslationY(800);
        password.setTranslationY(800);
        forgotPwd.setTranslationY(800);
        btnLogin.setTranslationY(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgotPwd.setAlpha(v);
        btnLogin.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(400).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(600).start();
        forgotPwd.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(800).start();
        btnLogin.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(1000).start();
    }

    private void checkFields(){
        mail = email.getEditText().getText().toString();
        pwd = password.getEditText().getText().toString();

        if (isEmpty(mail) || isEmpty(pwd)){
            Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            signInUser();
        }
    }

    public boolean isEmpty(String s1){
        return s1.equals("");
    }

    public void signInUser(){
        mAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Unable to sign you in try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
