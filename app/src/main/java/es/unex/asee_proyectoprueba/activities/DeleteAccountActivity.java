package es.unex.asee_proyectoprueba.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;
import es.unex.asee_proyectoprueba.support.AppExecutors;
import es.unex.asee_proyectoprueba.support.UserFilmsData;

public class DeleteAccountActivity extends AppCompatActivity {

    // Referencias a vistas de la UI
    private Button bDelete;
    private Button bCancelDelete;

    // Objeto de preferencias para almacenar el nombre de usuario y permitir un inicio de sesión automático
    SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        setTitle(R.string.delete_account_bar_title);

        getViewsReferences();

        // Se presiona en Cancelar
        bCancelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se finaliza la actividad para eliminarla de la pila de Back y volver a la actividad anterior sin eliminar la cuenta de la BD
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        // Se presiona en Borrar la cuenta
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {deleteAccount();}
        });
    }

    /**
     * Método para obtener las referencias a cada una de las vistas que forman parte de la UI de la actividad.
     */
    private void getViewsReferences() {
        bDelete = findViewById(R.id.bDelete);
        bCancelDelete = findViewById(R.id.bCancelDelete);
    }

    /**
     * Elimina las preferencias de usuario actuales y borra al usuario de la BD, devolviendo el resultado a la actividad que lo invoca (HomeActivity)
     */
    private void deleteAccount() {
        // En caso de que se cierre sesión, la información viva del usuario debe reiniciarse a la espera de la nueva cuenta que sea loggeada
        UserFilmsData userFilmsData = UserFilmsData.getInstance();
        userFilmsData.userPendingFilms.clear();
        userFilmsData.userFavoriteFilms.clear();
        userFilmsData.userRatedFilms.clear();
        // Se recupera el nombre de usuario loggeado
        loginPreferences = getSharedPreferences(getPackageName() + "_preferences", Context.MODE_PRIVATE);
        String username = loginPreferences.getString("USERNAME", "");

        // Se elimina el usuario de la BD dado su nombre
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(DeleteAccountActivity.this);
                db.userDAO().deleteUserByID(username);
            }
        });

        // Se eliminan las preferencias para evitar un login automático
        removePreferences();

        // Se devuelve el control a la actividad anterior tras elimanar el usuario de la BD
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Elimina las preferencias compartidas asociadas al usuario que tiene la sesión abierta.
     */
    private void removePreferences() {
        SharedPreferences.Editor editPreferences = loginPreferences.edit();
        editPreferences.remove("USERNAME");
        editPreferences.apply();
    }

}