package com.example.marvelapi.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.example.marvelapi.R;
import com.example.marvelapi.view.activities.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btnLogin.setOnClickListener(v -> {
            String newEmail = tilEmail.getEditText().getText().toString();
            String newPassword =tilPassword.getEditText().getText().toString();

            if (isEmptyEmail(newEmail) || isEmptyPassword(newPassword))
                notificationNullInputs();
            else if (!isValidEmail(newEmail) || !isValid(newPassword))
                notificationIncorret();
            else
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });
    }

    private void initViews (){
        tilEmail = findViewById(R.id.textInputLayoutEmail);
        tilPassword = findViewById(R.id.textInputLayoutPassword);
        btnLogin = findViewById(R.id.buttonLogin);
    }

    private boolean isValidEmail(String newEmail){
        if (newEmail.contains("@") && newEmail.contains(".com") )
            return true;
        else
            return false;
    }

    private boolean isEmptyEmail(String newEmail){
        if (newEmail.isEmpty())
            return true;
        else
            return false;
    }

    public static boolean isValid(String newPassword) {
        if (newPassword.length() < 6)
            return false;

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;

        for (char c : newPassword.toCharArray()) {
            if (c >= '0' && c <= '9')
                achouNumero = true;
            else if (c >= 'A' && c <= 'Z')
                achouMaiuscula = true;
            else if (c >= 'a' && c <= 'z')
                achouMinuscula = true;
            else
                achouSimbolo = true;
        }
        return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
    }

    private boolean isEmptyPassword(String newPassaword){
        if (newPassaword.isEmpty())
            return true;
        else
            return false;
    }

    protected void notificationNullInputs(){
        Context mContext = getApplicationContext();
        String notificationText = getString(R.string.null_input);
        int notificationTiming = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(mContext, notificationText, notificationTiming);
        toast.show();
    }

    protected void notificationIncorret(){
        Context mContext = getApplicationContext();
        String notificationText = getString(R.string.incorret_input);
        int notificationTiming = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(mContext, notificationText, notificationTiming);
        toast.show();
    }
}