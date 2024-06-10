package com.vedruna.simbad;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity {

    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userType = getIntent().getStringExtra("userType");

        // Cargar el fragmento adecuado basado en el tipo de usuario solo si es la primera vez que se crea la actividad
        if (savedInstanceState == null) {
            if ("user".equals(userType)) {
                navigateToFragment(new AnimalListUserFragment(), false);
            } else {
                navigateToFragment(new AnimalListRefugeFragment(), false);
            }
        }

        // Manejar el evento de retroceso usando OnBackPressedDispatcher
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    // Si no hay fragmentos en la pila, volver a LoginActivity
                    Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if ("user".equals(userType)) {
            getMenuInflater().inflate(R.menu.toolbar_menu_user, menu);
        } else if ("refuge".equals(userType)) {
            getMenuInflater().inflate(R.menu.toolbar_menu_refuge, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.animal_list_user) {
            navigateToFragment(new AnimalListUserFragment(), true);
            return true;
        } else if (id == R.id.account_user) {
            navigateToFragment(new AccountUserFragment(), true);
            return true;
        } else if (id == R.id.list_adoptions_user) {
            navigateToFragment(new ListAdoptionsUserFragment(), true);
            return true;
        } else if (id == R.id.animal_list_refuge) {
            navigateToFragment(new AnimalListRefugeFragment(), true);
            return true;
        } else if (id == R.id.account_refuge) {
            navigateToFragment(new AccountRefugeFragment(), true);
            return true;
        } else if (id == R.id.list_adoptions_refuge) {
            navigateToFragment(new ListAdoptionsRefugeFragment(), true);
            return true;
        } else if (id == R.id.exit) {
            // Obtener el nombre de la actividad anterior del intent
            String previousActivity = getIntent().getStringExtra("previousActivity");
            // Verificar si el nombre de la actividad anterior está presente
            if (previousActivity != null && !previousActivity.isEmpty()) {
                // Crear un intent para volver a la actividad anterior
                Intent intent = new Intent();
                try {
                    // Obtener la clase de la actividad anterior y configurarla en el intent
                    intent.setClass(this, Class.forName(previousActivity));
                    // Iniciar la actividad anterior
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar la actividad actual
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
