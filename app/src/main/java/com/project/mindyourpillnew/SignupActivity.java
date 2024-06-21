package com.project.mindyourpillnew;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.mindyourpillnew.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    private EditText username, email, password, confirmPassword;
    private TextView login;
    private Button signupButton;
    private CheckBox tnc;
    private UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = binding.txtUsername;
        email = binding.txtEmail;
        password = binding.txtPassword;
        confirmPassword = binding.txtConfirm;
        signupButton = binding.btnSignup;
        tnc = binding.checkAgree;
        login = binding.loginRedirect;
        userRepository = new UserRepository();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()) {
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateInput() {
        if (username.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
            email.setError("All fields must be filled");
            username.setError("All fields must be filled");
            password.setError("All fields must be filled");
            confirmPassword.setError("All fields must be filled");
            return false;
        }
        if(!email.getText().toString().contains("@") && !email.getText().toString().contains(".")){
            email.setError("Invalid email");
            email.requestFocus();
            return false;
        }
        if(password.getText().toString().length() < 8){
            password.setError("Password must be at least 8 characters long");
            password.requestFocus();
            return false;
        }
        if(!password.getText().toString().matches("[a-zA-Z0-9]+$")){
            password.setError("Password must only contain letters and numbers");
            password.requestFocus();
            return false;
        }
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Passwords do not match");
            confirmPassword.requestFocus();
            return false;
        }
        if (!tnc.isChecked()) {
            tnc.setError("Please agree to the terms and conditions");
            tnc.requestFocus();
            return false;
        }
        if(userRepository.checkUser(email.getText().toString(), password.getText().toString())){
            email.setError("User already exists");
            email.requestFocus();
            return false;
        }
        userRepository.addUser(username.getText().toString(), email.getText().toString(), password.getText().toString());
        return true;
    }
}