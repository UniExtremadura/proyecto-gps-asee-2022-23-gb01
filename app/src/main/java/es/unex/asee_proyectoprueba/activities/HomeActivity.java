package es.unex.asee_proyectoprueba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.databinding.ActivityMainBinding;
import es.unex.asee_proyectoprueba.ui.profile.ProfileFragment;


public class HomeActivity extends AppCompatActivity implements ProfileFragment.ProfileListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //BottomNavigationView navView = findViewById(R.id.nav_view);

        //Passing each menu ID as a set of Ids because each
        //menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_explore, R.id.navigation_favorites, R.id.navigation_pendings, R.id.navigation_profile)
                .build();

        // Se centra la etiqueta (título) de la App Bar para cada pantalla
        centerAppBarTitle();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * Centra el título de la AppBar de cada una de las secciones principales de la App.
     */
    private void centerAppBarTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if(textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                appCompatTextView.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        }
    }

    /**
     * Implementación del método onDeleteAccountButtonPressed() de la interfaz ProfileListener definido para controlar el comportamiento del botón
     * para eliminar la cuenta de usuario en ProfileFragment.
     * Se lanza la actividad DeleteAccountActivity a la espera de si el usuario confirma la eliminación de la cuenta o no.
     * La respuesta es recogida en deleteAccountLauncher.
     */
    @Override
    public void onDeleteAccountButtonPressed() {
        Intent intent = new Intent(this, DeleteAccountActivity.class);
        deleteAccountLauncher.launch(intent);
    }

    /**
     * Recupera la respuesta devuelta por DeleteAccountActivity. Si el usuario no ha eliminado la cuenta, no se hace nada. En caso contrario
     * (RESULT_OK), se inicia la actividad de LoginActivity para acceder con una nueva cuenta y se finaliza la actividad actual para vaciar
     * la pila de Back.
     */
    ActivityResultLauncher<Intent> deleteAccountLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
}