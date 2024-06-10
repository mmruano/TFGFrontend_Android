package com.vedruna.simbad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.Security.AuthResponse;
import com.vedruna.simbad.API.Security.AuthService;
import com.vedruna.simbad.API.Security.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText, passwordText;

    // Configurar Retrofit
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    AuthService authService = retrofit.create(AuthService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
    }

    public void login(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "El correo electrónico y contraseña no pueden estar vacios.", Toast.LENGTH_LONG).show();
            return;
        }

        LoginRequest request = new LoginRequest(email, password);

        Call<AuthResponse> call = authService.login(request);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AuthResponse authResponse = response.body();
                    String token = authResponse.getToken();
                    String userType = authResponse.getUserType(); // Obtener el tipo de usuario
                    handleLoginSuccess(token, userType);
                    Toast.makeText(LoginActivity.this, "Acceso exitoso.", Toast.LENGTH_LONG).show();
                } else {
                    // Aquí puedes revisar el código de estado de la respuesta
                    if (response.code() == 400) {
                        Toast.makeText(LoginActivity.this, "Email o contraseña incorrectos.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "Error al conectar con el servidor.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleLoginSuccess(String token, String userType) {
        // Guardar el token en SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();

        // Navegar a la actividad principal con el tipo de usuario
        Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
        intent.putExtra("previousActivity", LoginActivity.class.getName());
        intent.putExtra("userType", userType);
        startActivity(intent);
        finish();
    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
