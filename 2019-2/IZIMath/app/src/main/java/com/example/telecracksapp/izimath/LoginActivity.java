package com.example.telecracksapp.izimath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.telecracksapp.izimath.PrincipalActivity;
import com.example.telecracksapp.izimath.RegisterOptionsActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView olvida_tv;
    private TextView registra_tv;
    private EditText usuario_et;
    private EditText password_et;
    private Button inicia_bt;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        olvida_tv.findViewById(R.id.olvida_tv);
        registra_tv.findViewById(R.id.registrar_tv);
        usuario_et.findViewById(R.id.user_et);
        password_et.findViewById(R.id.password_et);
        inicia_bt.findViewById(R.id.inicia_bt);
        auth = FirebaseAuth.getInstance();


        inicia_bt.setOnClickListener(
                (v) -> {
                    auth.signInWithEmailAndPassword(
                            usuario_et.getText().toString().trim(),
                            password_et.getText().toString().trim()
                    ).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String usuario = auth.getCurrentUser().getUid();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("usuario", usuario);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }
                    );


                }
        );


        registra_tv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this, RegisterOptionsActivity.class);
                        LoginActivity.this.startActivity(intent);
                        LoginActivity.this.finish();
                    }
                }
        );


    }
}


