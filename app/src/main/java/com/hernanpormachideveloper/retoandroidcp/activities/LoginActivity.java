    package com.hernanpormachideveloper.retoandroidcp.activities;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.app.AlertDialog;
    import android.content.Intent;
    import android.os.Bundle;

    import com.google.android.gms.auth.api.signin.GoogleSignIn;
    import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
    import com.google.android.gms.auth.api.signin.GoogleSignInClient;
    import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
    import com.google.android.gms.common.api.ApiException;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.hernanpormachideveloper.retoandroidcp.R;
    import com.hernanpormachideveloper.retoandroidcp.services.AuthProvider;

    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.LinearLayout;
    import android.widget.Toast;

    import dmax.dialog.SpotsDialog;

    public class LoginActivity extends AppCompatActivity {

        private Button btnGoToCandystoreAsGuest;

        private static final String TAG = "LoginActivity";
        private static final int RC_SIGN_IN = 9001;
        private GoogleSignInClient mGoogleSignInClient;
        LinearLayout signInWithGoogle;
        AuthProvider mAuthProvider;
        AlertDialog mDialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mDialog = new SpotsDialog.Builder()
                    .setContext(this)
                    .setMessage("Espere un momento")
                    .setCancelable(false)
                    .build();

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mAuthProvider = new AuthProvider();
            signInWithGoogle = findViewById(R.id.signInWithGoogle);

            signInWithGoogle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn();
                }
            });

            btnGoToCandystoreAsGuest = findViewById(R.id.btnEnterAsGuest);
            btnGoToCandystoreAsGuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent intent = new Intent(LoginActivity.this, CandystoreActivity.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e);
                }
            }
        }
        // [END onactivityresult]

        // [START auth_with_google]
        private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
           mDialog.show();
            mAuthProvider.googleLogin(acct).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        String name = mAuthProvider.getUsername();
                        System.out.println("-------------- | ----------------");
                        System.out.println("name "+name);
                        System.out.println("-------------- | ----------------");

                        Intent intent = new Intent(LoginActivity.this, CandystoreActivity.class);
                        startActivity(intent);
                    } else {
                        mDialog.dismiss();
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion con Google ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        // [END auth_with_google]

        // [START signin]
        private void signIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

