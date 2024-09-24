package com.example.groceryapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, emailInput, passwordInput;
    private Button signupButton;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        // Initialize inputs
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        signupButton = findViewById(R.id.signupButton);

        // Initialize Retrofit API service
        apiService = ApiClient.getClient().create(ApiService.class);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameInput.getText().toString();
                String lastName = lastNameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                        TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    // Call method to register user
                    registerUser(firstName, lastName, email, password);
                }
            }
        });
    }

    private void registerUser(String firstName, String lastName, String email, String password) {
        // Create API call
        Call<ApiResponse> call = apiService.registerUser(firstName, lastName, email, password);

        // Execute the call asynchronously
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Registration failed: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Error: Response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(Register.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}