package edu.co.icesi.appmoviles.izmath25;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton google_btn;

    private GoogleApiClient googleApiClient;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener fireAuthStateListener;


    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(String.valueOf(R.string.default_web_client_id))
                .requestEmail()
                .requestId()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)

                .build();

        google_btn = findViewById(R.id.google_register);

        google_btn.setSize(SignInButton.SIZE_WIDE);
        google_btn.setColorScheme(SignInButton.COLOR_DARK);

        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE){





            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Toast.makeText(this,""+ result.getStatus().toString(), Toast.LENGTH_LONG).show();
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if(result.isSuccess()){

            Toast.makeText(this,"Se logeo" , Toast.LENGTH_LONG).show();

            goMainScreen();
        }else{
            //Toast.makeText(this,"No se pudo logear con google" , Toast.LENGTH_LONG).show();
        }
    }

    private void goMainScreen() {
    //    Intent i = new Intent(MainActivity.this,PrincipalActivity.class);
      //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
       // startActivity(i);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
