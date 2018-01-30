package com.logicuniv.mlussis;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.logicuniv.mlussis.Backend.LoginController;
import com.logicuniv.mlussis.StoreClerk.StoreClerk_MainActivity;

import java.util.ArrayList;

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

        usernameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.PasswordEditText);
        Button signIn = findViewById(R.id.signInButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new AsyncTask<String, Void, ArrayList<String>>() {
                    ArrayList<String> rolesAssigned;
                    boolean logintrue = false;

                    @Override
                    protected ArrayList<String> doInBackground(String... params) {
                        logintrue = LoginController.AuthenticateCredentials(getApplicationContext(), params[0], params[1]);
                        return LoginController.GetRolesFromCurrentSessionId(getApplicationContext());
                    }

                    @Override
                    protected void onPostExecute(ArrayList<String> result) {
                        rolesAssigned = result;
                        if (!logintrue) {
                            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                        } else

                        {
                            // Redirect User to the first Page Here
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                            // Assign based on the first role
                            Class nextActivity = null;
                            switch (rolesAssigned.get(0)) {
                                case "StoreManager":
                                case "StoreSupervisor":
                                case "StoreClerk":
                                    nextActivity = StoreClerk_MainActivity.class;
                                    break;
                                case "DepartmentHead":
                                case "DepartmentDeputy":
                                case "DepartmentRepresentative":
                                case "DepartmentEmployee":
                                    nextActivity = MainActivity.class;
                                    break;
                                default:
                                    // TODO : Finish this
                                    Log.e("LoginActivity", "Unknown Role : " + rolesAssigned.get(0));
                            }

                            if (nextActivity != null) {
                                Intent intent = new Intent(getApplicationContext(), nextActivity);
                                startActivity(intent);

                            }
                        }
                    }
                }.execute(username, password);

            }
        });
    }
}
