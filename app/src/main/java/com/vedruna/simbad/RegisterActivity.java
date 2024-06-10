package com.vedruna.simbad;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button userButton = findViewById(R.id.registerUserButton);
        Button refugeButton = findViewById(R.id.registerRefugeButton);

        userButton.setOnClickListener(v -> navigateToUserRegister());
        refugeButton.setOnClickListener(v -> navigateToRefugeRegister());
    }

    private void navigateToUserRegister() {
        // Reemplaza con el fragment específico para el registro de usuario
        Fragment userRegisterFragment = new UserRegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, userRegisterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateToRefugeRegister() {
        // Reemplaza con el fragment específico para el registro de refugio
        Fragment refugeRegisterFragment = new RefugeRegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, refugeRegisterFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goBack(View view) {
        // Vuelve a la actividad de login
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }
}