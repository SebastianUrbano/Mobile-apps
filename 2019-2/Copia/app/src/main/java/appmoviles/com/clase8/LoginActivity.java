package appmoviles.com.clase8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private Button btn_signup;
    private Button btn_signin;
    private Button btn_signup_facebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_signin = findViewById(R.id.btn_signin);
        btn_signup_facebook = findViewById(R.id.btn_signup_facebook);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter_from_bottom, R.anim.none);
            }
        });
    }
}
