package es.unex.asee_proyectoprueba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.model.User;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;
import es.unex.asee_proyectoprueba.support.AppExecutors;

public class RegisterActivity extends AppCompatActivity {

    // Referencias a vistas de la UI
    private EditText etUsernameRegister;
    private EditText etEmailRegister;
    private EditText etPasswordRegister;
    private EditText etRepeatPasswordRegister;
    private Button bRegister;

    // Mapas para guardar los usuarios almacenados en la BD para el chequeo de credenciales
    Map<String, User> usersInDBByUsername;
    Map<String, User> usersInDBByEmail;

    FilmsDatabase db = FilmsDatabase.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.register_bar_title);

        getViewsReferences();

        Intent intent = getIntent();
        usersInDBByUsername = (HashMap<String, User>) intent.getSerializableExtra("MAP_BY_USERNAME");
        usersInDBByEmail = (HashMap<String, User>) intent.getSerializableExtra("MAP_BY_EMAIL");

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se procede a registrar al usuario
                bRegister.setEnabled(false);
                registerUserIfValid(view);
            }
        });
    }

    /**
     * Método para obtener las referencias a cada una de las vistas que forman parte de la UI de la actividad.
     */
    private void getViewsReferences() {
        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etEmailRegister = findViewById(R.id.etEmailRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        etRepeatPasswordRegister = findViewById(R.id.etRepeatPasswordRegister);
        bRegister = findViewById(R.id.bRegister);
    }

    /**
     * Comprueba si las credenciales son válidas. En caso afirmativo, se inserta el nuevo usuario en la BD y se devuelve el control a LoginActivity
     */
    private void registerUserIfValid(View view) {
        // Si los campos introducidos son válidos, se inserta el usuario en la BD y se devuelve el nombre de usuario a la actividad LoginActivity
        if (checkFields(view)) {
            // Se da de alta al usuario en el sistema (se añade a la BD)
            insertUserInDB();
            // Se devuelve el nombre de usuario a la actividad LoginActivity
            returnToLogin();
        } else {
            view.setEnabled(true);
        }
    }

    /**
     * Comprueba si el nombre de usuario introducido cumple con el patrón indicado.
     * Retorna True si el nombre es válido o False en caso contrario.
     */
    private boolean usernameIsValid() {
        Pattern pattern;
        Matcher matcher;

        // Un nombre de usuario válido debe contener al menos una letra y su longitud debe ser mayor o igual que 6 y menor o igual que 16. No se permiten espacios.
        final String USERNAME_PATTERN = "^(?=.*[a-zA-Z])(?=\\S+$).{6,12}$";

        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(etUsernameRegister.getText().toString());

        return matcher.matches();
    }

    /**
     * Comprueba si el nombre de usuario introducido existe ya en la BD.
     * Retorna True si ya existe o False en caso contrario.
     * Se hace uso de los mapas para ahorrar transacciones con la BD.
     */
    private boolean usernameInDB() {
        return usersInDBByUsername.get(etUsernameRegister.getText().toString()) != null;
    }

    /**
     * Comprueba si el correo electrónico introducido cumple con el patrón indicado.
     * Retorna True si es válido o False en caso contrario.
     */
    private boolean emailIsValid() {
        return (!TextUtils.isEmpty(etEmailRegister.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(etEmailRegister.getText().toString()).matches());
    }

    /**
     * Comprueba si el correo electrónico introducido existe ya en la BD.
     * Retorna True si ya existe o False en caso contrario.
     * Se hace uso de los mapas para ahorrar transacciones con la BD.
     */
    private boolean emailInDB() {
        return usersInDBByEmail.get(etEmailRegister.getText().toString()) != null;
    }

    /**
     * Comprueba si la contraseña introducida cumple con el patrón indicado.
     * Retorna True si es válida o False en caso contrario.
     */
    private boolean passwordIsValid() {
        Pattern pattern;
        Matcher matcher;

        // Una contraseña válida debe contener como mínimo un dígito, una letra minúscula y una letra mayúscula. Además, no se permiten espacios y la longitud debe ser mayor o igual que 6, pero menor o igual que 16
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,16}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(etPasswordRegister.getText().toString());

        return matcher.matches();
    }

    /**
     * Comprueba si las dos constraseñas introducidas coinciden.
     */
    private boolean passwordCoincidence() {
        return etPasswordRegister.getText().toString().equals(etRepeatPasswordRegister.getText().toString());
    }

    /**
     * Realiza todas las comprobaciones definidas en los métodos anteriores. Para que el Registro sea efectivo, todas ellas deben cumplirse, caso en el que se
     * retorna True. En caso contrario se retorna False.
     */
    private boolean checkFields(View view) {
        // Se comprueba cada opción en un IF independiente para no solapar los mensajes de la Snackbar
        // Además, una vez que una de las opciones no es correcta, el resto se obvian y no se comprueban
        // No se ha optado por IF anidados para mejorar la lectura del código. Para ello se para la ejecución del método con returns en cada uno de los IF.

        // Atributos para personalizar la Snackbar empleada al verificar los datos introducidos
        int SNACKBAR_MAX_LINES = 10; // Número máximo de líneas a mostrar en la Snackbar
        int SNACKBAR_TIME_LONG = 6000; // Duración larga en milisegundos de la Snackbar en pantalla
        int SNACKBAR_TIME_SHORT = 3500; // Duración corta en milisegundos de la Snackbar en pantalla

        if (!usernameIsValid()) {
            Snackbar.make(view, R.string.username_is_invalid, SNACKBAR_TIME_LONG)
                    .setTextMaxLines(SNACKBAR_MAX_LINES).show();
            return false;
        }

        if (usernameInDB()) {
            Snackbar.make(view, R.string.username_already_exists, SNACKBAR_TIME_SHORT)
                    .setTextMaxLines(SNACKBAR_MAX_LINES).show();
            return false;
        }

        if (emailInDB()) {
            Snackbar.make(view, R.string.email_already_exists, SNACKBAR_TIME_SHORT)
                    .setTextMaxLines(SNACKBAR_MAX_LINES).show();
            return false;
        }

        if (!emailIsValid()) {
            Snackbar.make(view, R.string.email_is_invalid, SNACKBAR_TIME_SHORT)
                    .setTextMaxLines(SNACKBAR_MAX_LINES).show();
            return false;
        }

        if (!passwordIsValid()) {
            Snackbar.make(view, R.string.password_is_invalid, SNACKBAR_TIME_LONG)
                    .setTextMaxLines(SNACKBAR_MAX_LINES).show();
            return false;
        }

        if (!passwordCoincidence()) {
            Snackbar.make(view, R.string.password_coincidence_missing, SNACKBAR_TIME_SHORT)
                    .setTextMaxLines(SNACKBAR_MAX_LINES).show();
            return false;
        }
        return true;
    }

    /**
     * Inserta un nuevo usuario en la BD con los datos obtenidos una vez validados.
     */
    private void insertUserInDB() {
        String username = etUsernameRegister.getText().toString();
        String email = etEmailRegister.getText().toString();
        String password = etPasswordRegister.getText().toString();
        User user = new User(username, email, password);
        // Se inserta el usuario en la DB
        AppExecutors.getInstance().diskIO().execute(() -> {
            db = FilmsDatabase.getInstance(RegisterActivity.this);
            db.userDAO().insertUser(user);
        });
    }

    /**
     * Devuelve el control a la actividad LoginActivity pasándole el nombre de usuario que será utilizado para actualizazr las preferencias compartidas.
     */
    private void returnToLogin() {
        Intent intent = new Intent();
        intent.putExtra("USERNAME", etUsernameRegister.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}