package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.App;
import com.logicuniv.mlussis.Backend.LoginController;

public class LoginActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;
    ImageView imageView;
    ImageView login_imageView;
    Button signIn;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initial Setup
        setContentView(R.layout.activity_login);
        App.setAppContext(getApplicationContext());
        usernameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.PasswordEditText);
        imageView = findViewById(R.id.logo_loading);
        login_imageView = findViewById(R.id.logo_login);
        signIn = findViewById(R.id.signInButton);

        // Login Button Handler
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new AsyncTask<String, Void, Void>() {
                    boolean logintrue = false;

                    @Override
                    protected Void doInBackground(String... params) {
                        logintrue = LoginController.AuthenticateCredentials(getApplicationContext(), params[0], params[1]);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        if (!logintrue) {
                            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                        } else {
                            Class nextActivity = MainActivity.class;
                            Intent intent = new Intent(getApplicationContext(), nextActivity);
                            startActivityForResult(intent, 0);
                        }
                    }
                }.execute(username, password);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfAlreadyLoggedin();
    }

    private void checkIfAlreadyLoggedin()
    {
        // If already logged in, redirect to next activity
        if (!LoginController.getSessionID(getApplicationContext()).equals("0")) {
            // Now Show the login Button User won't have time to respond now
            login_imageView.setVisibility(View.GONE);
            signIn.setVisibility(View.GONE);
            usernameEditText.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);

            new AsyncTask<Void, Void, Void>() {
                boolean logintrue = false;

                @Override
                protected Void doInBackground(Void... params) {
                    logintrue = LoginController.IsCurrentSessionValid(getApplicationContext());
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    // If already logged in, goto next screen
                    if (logintrue) {
                        Class nextActivity = MainActivity.class;
                        Intent intent = new Intent(getApplicationContext(), nextActivity);
                        startActivity(intent);
                    } else {
                        // Now Show the login Button User won't have time to respond now
                        login_imageView.setVisibility(View.VISIBLE);
                        signIn.setVisibility(View.VISIBLE);
                        usernameEditText.setVisibility(View.VISIBLE);
                        passwordEditText.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                    }
                }
            }.execute();
        } else {
            login_imageView.setVisibility(View.VISIBLE);
            signIn.setVisibility(View.VISIBLE);
            usernameEditText.setVisibility(View.VISIBLE);
            passwordEditText.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }
    }
}
