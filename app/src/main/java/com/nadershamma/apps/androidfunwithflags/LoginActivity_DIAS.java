package com.nadershamma.apps.androidfunwithflags;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity_DIAS extends AppCompatActivity {

    Button btnLogin;
    EditText etUser, etPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_Login);
        etUser = findViewById(R.id.et_User);
        etPasswd = findViewById(R.id.et_Password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    public void login(){
        String user = etUser.getText().toString();
        String passwd = etPasswd.getText().toString();
        if(user.matches("admin") && passwd.matches("admin") ||
                user.matches("invitado") && passwd.matches("invitado") ){
            Intent intent = new Intent(getApplicationContext(), MainActivity_DIAS.class);
            intent.putExtra("k_user", user);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}