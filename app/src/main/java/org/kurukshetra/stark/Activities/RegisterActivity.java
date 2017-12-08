package org.kurukshetra.stark.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
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
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

public class RegisterActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private Button googleSignInButton,fbSignInButoon;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 200;
    private RelativeLayout rlButton,rlProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_register);
        googleSignInButton = findViewById(R.id.google_sign_in);
        rlButton = findViewById(R.id.rlButton);
        rlProfile = findViewById(R.id.rlProfile);
        fbSignInButoon = findViewById(R.id.fb_sign_in);
        loginButton = findViewById(R.id.fb_button);
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
                Toast.makeText(RegisterActivity.this,loginResult.getAccessToken().getToken(),Toast.LENGTH_SHORT).show();
                RESTClientImplementation.facebookLogin(loginResult.getAccessToken().getToken(), new SocialLoginInterface.RestClientInterface() {
                    @Override
                    public void onLogin(String token,int code,VolleyError error) {
                        if(code == 203) {
                            //do after signin
                            showProfileView();
                        }else {
                             Toast.makeText(RegisterActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                           // goToActivity(RegisterActivity.class);
                        }
                    }
                },RegisterActivity.this);
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
    }

    private void showProfileView() {
        rlButton.setVisibility(View.GONE);
        rlProfile.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            RESTClientImplementation.googleLogin(idToken, new SocialLoginInterface.RestClientInterface() {
                @Override
                public void onLogin(String token,int code,VolleyError error) {
                    if(code == 203) {
                        //do after sign in
                        showProfileView();
                    }else {
                         Toast.makeText(RegisterActivity.this,"Something went wrong :(",Toast.LENGTH_SHORT).show();
                        //goToActivity(RegisterActivity.class);
                    }
                }
            },RegisterActivity.this);

        } catch (ApiException e) {
            Log.w("Register Activity", "handleSignInResult:error", e);
        }
    }

    private void goToActivity(Class activity) {
        Intent intent;
        intent = new Intent(RegisterActivity.this, activity);
        startActivity(intent);
    }
}
