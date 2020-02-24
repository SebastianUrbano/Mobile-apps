package com.example.telecracksapp.izimath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.telecracksapp.izimath.Modelo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUserActivity extends AppCompatActivity {

    private Button registrarse_bt;
    private Button atras_bt;
    private EditText usuario_et;
    private EditText correo;
    private EditText password_et;
    private EditText repass_et;

    FirebaseAuth auth;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user_activity);
        usuario_et.findViewById(R.id.user_et);
        correo.findViewById(R.id.email_et);
        password_et.findViewById(R.id.password_et);
        repass_et.findViewById(R.id.repassword_et);
        registrarse_bt.findViewById(R.id.register_btn);

        registrarse_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (correo.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterUserActivity.this, "El campo de email esta vacio", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password_et.getText().toString()
                        .equals(repass_et.getText().toString())) {
                    Toast.makeText(RegisterUserActivity.this, "Las contraseñas NO coinciden", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password_et.getText().toString().trim().length() < 5) {
                    Toast.makeText(RegisterUserActivity.this, "Las contraseñas debe tener mínimo 5 carácteres", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(
                        correo.getText().toString().trim(),
                        password_et.getText().toString().trim()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Ya estamos logeados


                            User user = new User(
                                    auth.getCurrentUser().getUid(),
                                    correo.getText().toString(),
                                    usuario_et.getText().toString(),
                                    password_et.getText().toString()

                            );

                            db.getReference().child("users").child(user.getUid())
                                    .setValue(user);



                            Intent i = new Intent(RegisterUserActivity.this, PrincipalActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(RegisterUserActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });



    }







}
