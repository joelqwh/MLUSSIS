package com.logicuniv.mlussis;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.LoginController;

public class LoginActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;

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
        setContentView(R.layout.activity_login);

        // TODO : Remove This
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);

        usernameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.PasswordEditText);
        Button signIn = findViewById(R.id.signInButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill in Both Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!LoginController.AuthenticateCredentials(getApplicationContext(), username, password)) {
                    Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                } else {
                    // TODO : Redirect User to the first Page Here
                    Toast.makeText(getApplicationContext(), "Congrats!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
