package org.kurukshetra.stark.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.SocialLoginInterface;
import org.kurukshetra.stark.Entities.LoginEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 200;
    private EditText etEmail,etPassword;
    private Button bLogin;
    private GoogleSignInClient mGoogleSignInClient;
    private Button googleSignInButton,fbSignInButoon;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        googleSignInButton = findViewById(R.id.google_sign_in_button);
        fbSignInButoon = findViewById(R.id.fb_sign_in_button);
        loginButton = findViewById(R.id.fb_default_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();

        fbSignInButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this,loginResult.getAccessToken().getToken(),Toast.LENGTH_SHORT).show();
                RESTClientImplementation.facebookLogin(loginResult.getAccessToken().getToken(), new SocialLoginInterface.RestClientInterface() {
                    @Override
                    public void onLogin(String token, VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Token"+token,Toast.LENGTH_SHORT).show();
                        UserDetails.setUserLoggedIn(LoginActivity.this,true);
                        UserDetails.setUserToken(LoginActivity.this,token);
                        Toast.makeText(LoginActivity.this,"Facebook sign in successful",Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    }
                },LoginActivity.this);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_token))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEntity loginEntity = new LoginEntity(etEmail.getText().toString(),etPassword.getText().toString());
                RESTClientImplementation.normalLogin(loginEntity, new LoginEntity.RestClientInterface() {

                    @Override
                    public void onLogin(String token, VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Token"+token,Toast.LENGTH_SHORT).show();
                        UserDetails.setUserLoggedIn(LoginActivity.this,true);
                        UserDetails.setUserToken(LoginActivity.this,token);
                        goToMainActivity();
                    }

                },LoginActivity.this);
            }
        });


    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(UserDetails.isUserLoggedIn(LoginActivity.this)){
           // Toast.makeText(LoginActivity.this,"Already Logged in",Toast.LENGTH_SHORT).show();
            goToMainActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK){
           // Toast.makeText(LoginActivity.this,"came here",Toast.LENGTH_SHORT).show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            // TODO(developer): send ID Token to server and validate
         //   Toast.makeText(LoginActivity.this,idToken,Toast.LENGTH_SHORT).show();
            RESTClientImplementation.googleLogin(idToken, new SocialLoginInterface.RestClientInterface() {
                @Override
                public void onLogin(String token, VolleyError error) {
                 //   Toast.makeText(LoginActivity.this,"Token"+token,Toast.LENGTH_SHORT).show();
                    UserDetails.setUserLoggedIn(LoginActivity.this,true);
                    UserDetails.setUserToken(LoginActivity.this,token);
                   // Toast.makeText(LoginActivity.this,"Google sign in successful",Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                }
            },LoginActivity.this);

        } catch (ApiException e) {
            Log.w("Login Activity", "handleSignInResult:error", e);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
