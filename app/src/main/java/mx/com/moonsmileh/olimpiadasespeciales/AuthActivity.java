package mx.com.moonsmileh.olimpiadasespeciales;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by moonsmileh on 01/11/16.
 */


public class AuthActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword  = (EditText) findViewById(R.id.password);
        Button btnLogin = (Button) findViewById(R.id.email_sign_in_button);

        auth = FirebaseAuth.getInstance();

        etEmail.setText("hs@moonsmileh.com");
        etPassword.setText("123456");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    private void attemptLogin(){
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        if(!email.isEmpty() && !password.isEmpty())
            registerUser(email,password);
    }

    private void registerUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    getCustomSnackbar("ACCESO CORRECTO").show();
                }
                else {
                    getCustomSnackbar("CREDENCIALES INVALIDAS").show();

                }
            }
        });
    }

    // TODO: PASS TO UTIL CLASS
    private Snackbar getCustomSnackbar(String message) {
        Snackbar snackbar =  Snackbar.make(getCurrentFocus(),message,Snackbar.LENGTH_SHORT);
        return snackbar;
    }
}
