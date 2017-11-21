package org.kurukshetra.stark.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.LoginEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RestClientImplementation;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEntity loginEntity = new LoginEntity(etEmail.getText().toString(),etPassword.getText().toString());
                RestClientImplementation.normalLogin(loginEntity, new LoginEntity.RestClientInterface() {
                    @Override
                    public void onLogin(String token, VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Token"+token,Toast.LENGTH_SHORT).show();
                        UserDetails.setUserLoggedIn(LoginActivity.this,true);
                        UserDetails.setUserToken(LoginActivity.this,token);
                    }

                },LoginActivity.this);
            }
        });


    }
}
