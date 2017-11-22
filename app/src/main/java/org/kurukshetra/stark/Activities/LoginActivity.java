package org.kurukshetra.stark.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import org.kurukshetra.stark.Common.UserDetails;
import org.kurukshetra.stark.Entities.GoogleLoginEntity;
import org.kurukshetra.stark.Entities.LoginEntity;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RestClientImplementation;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 200;
    private EditText etEmail,etPassword;
    private Button bLogin;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        signInButton = findViewById(R.id.sign_in_button);
        loginButton = findViewById(R.id.fb_login_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this,loginResult.getAccessToken().toString(),Toast.LENGTH_SHORT).show();
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

        signInButton.setOnClickListener(new View.OnClickListener() {
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

        generateHash();


    }

    private void generateHash() {
        try {
            @SuppressLint("PackageManagerGetSignatures") PackageInfo info = getPackageManager().getPackageInfo(
                    "org.kurukshetra.stark",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException ignored) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK){
            Toast.makeText(LoginActivity.this,"came here",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(LoginActivity.this,idToken,Toast.LENGTH_SHORT).show();
            RestClientImplementation.googleLogin(idToken, new GoogleLoginEntity.RestClientInterface() {
                @Override
                public void onLogin(String token, VolleyError error) {
                    Toast.makeText(LoginActivity.this,"Token"+token,Toast.LENGTH_SHORT).show();
                    UserDetails.setUserLoggedIn(LoginActivity.this,true);
                    UserDetails.setUserToken(LoginActivity.this,token);
                    Toast.makeText(LoginActivity.this,"Google sign in successful",Toast.LENGTH_SHORT).show();
                }
            },LoginActivity.this);

        } catch (ApiException e) {
            Log.w("Login Activity", "handleSignInResult:error", e);
        }
    }
}
