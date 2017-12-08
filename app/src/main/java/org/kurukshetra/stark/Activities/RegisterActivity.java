package org.kurukshetra.stark.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import org.kurukshetra.stark.Entities.SignupPostEntity;
import org.kurukshetra.stark.Entities.SocialLoginInterface;
import org.kurukshetra.stark.R;
import org.kurukshetra.stark.RESTclient.RESTClientImplementation;

public class RegisterActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private Button googleSignInButton,fbSignInButoon,signup;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 200;
    private RelativeLayout rlButton,rlProfile;
    private EditText email,name,regno,password,cpassword,phone,refcode;
    private RadioGroup sexgroup,studentgroup;
    private RadioButton sexbutton,studentButton;
    private Spinner department,college,year;
    GoogleSignInAccount googleSignInAccount;
    private boolean social = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_register);
        googleSignInButton = findViewById(R.id.google_sign_in);
        assignId();
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
                        if(code == 200){
                            UserDetails.setUserToken(RegisterActivity.this,token);
                            UserDetails.setUserLoggedIn(RegisterActivity.this,true);
                            goToActivity(HomeActivity.class);
                        }
                        else if(code == 203) {
                            UserDetails.setUserToken(RegisterActivity.this,token);
                            //do after signin
                            social = true;
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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        if(!email.getText().toString().trim().equals("")){
            if(!name.getText().toString().trim().equals("")){
                if(!regno.getText().toString().trim().equals("")){
                    if(!password.getText().toString().trim().equals("")){
                        if(!cpassword.getText().toString().trim().equals("")){
                            if(password.getText().toString().equals(cpassword.getText().toString())) {
                                if(!phone.getText().toString().trim().equals("")) {
                                    SignupPostEntity signupPostEntity = new SignupPostEntity();
                                    signupPostEntity.setName(email.getText().toString());
                                    signupPostEntity.setCid(regno.getText().toString());
                                    signupPostEntity.setPassword(password.getText().toString());
                                    signupPostEntity.setMobile(phone.getText().toString());
                                    signupPostEntity.setField(String.valueOf(department.getSelectedItem()));
                                    signupPostEntity.setOrganization(String.valueOf(college.getSelectedItem()));
                                    signupPostEntity.setYear(String.valueOf(year.getSelectedItem()));
                                    sexbutton = findViewById(sexgroup.getCheckedRadioButtonId());
                                    signupPostEntity.setGender(sexbutton.getText().toString());
                                    if(studentgroup.getCheckedRadioButtonId()  == R.id.rbs){
                                        signupPostEntity.setStudent(true);
                                    }else {
                                        signupPostEntity.setStudent(false);
                                    }
                                    signupPostEntity.setSa(true);

                                    signupUser(signupPostEntity);

                                }
                                else {
                                    phone.setError("Enter a valid Phone number");
                                }
                            }
                            else {
                                cpassword.setError("Passwords don't match");
                            }
                        }
                    }
                    else {
                        password.setError("Empty");
                    }
                }
                else {
                    regno.setError("Empty");
                }
            }
            else {
                name.setError("Empty");
            }
        }
        else {
            email.setError("Empty");
        }
    }

    private void signupUser(SignupPostEntity signupPostEntity) {
        if(social){
            //Social profile api update
            RESTClientImplementation.profile(signupPostEntity, UserDetails.getUserToken(RegisterActivity.this), new SignupPostEntity.ProfileUpdateInterface() {
                @Override
                public void onUpdate(int code, VolleyError error) {
                    if(error == null && code == 200){
                        UserDetails.setUserLoggedIn(RegisterActivity.this,true);
                        goToActivity(HomeActivity.class);
                    }else {
                        Toast.makeText(RegisterActivity.this,"Unauthorized",Toast.LENGTH_SHORT).show();
                    }
                }
            },RegisterActivity.this);
        }else {
            //normal signup api update
        }
    }

    private void assignId() {
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        regno = findViewById(R.id.regno);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        phone = findViewById(R.id.phone);
        refcode = findViewById(R.id.refcode);

        sexgroup = findViewById(R.id.sexgroup);
        studentgroup = findViewById(R.id.studentgroup);

        department = findViewById(R.id.department);
        college = findViewById(R.id.college);
        year = findViewById(R.id.year);

        signup = findViewById(R.id.signup);
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
            final GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            RESTClientImplementation.googleLogin(idToken, new SocialLoginInterface.RestClientInterface() {
                @Override
                public void onLogin(String token,int code,VolleyError error) {
                    if(code == 200){
                        UserDetails.setUserToken(RegisterActivity.this,token);
                        UserDetails.setUserLoggedIn(RegisterActivity.this,true);
                        goToActivity(HomeActivity.class);
                    }
                    else if(code == 203) {
                        UserDetails.setUserToken(RegisterActivity.this,token);
                        social = true;
                        showProfileViewGoogle(account);
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

    private void showProfileViewGoogle(GoogleSignInAccount account) {
        rlButton.setVisibility(View.GONE);
        rlProfile.setVisibility(View.VISIBLE);
        email.setText(account.getEmail());
        name.setText(account.getDisplayName());
    }

    private void goToActivity(Class activity) {
        Intent intent;
        intent = new Intent(RegisterActivity.this, activity);
        startActivity(intent);
    }
}
