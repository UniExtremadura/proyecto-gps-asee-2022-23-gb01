package es.unex.asee_proyectoprueba.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.unex.asee_proyectoprueba.support.AppExecutors;
import es.unex.asee_proyectoprueba.R;
import es.unex.asee_proyectoprueba.support.UserFilmsData;
import es.unex.asee_proyectoprueba.databinding.FragmentProfileBinding;
import es.unex.asee_proyectoprueba.model.User;
import es.unex.asee_proyectoprueba.room.FilmsDatabase;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileListener profileListener;

    // Campo para el acceso a las preferencias de usuario compartidas
    private SharedPreferences loginPreferences;

    // Referencias a vistas de la UI
    private ImageButton ibAppInfo;
    private Button bLogOut;
    private Button bDeleteAccount;
    private TextView tvUsernameValueProfile;
    private TextView tvEmailValueProfile;
    private TextView tvPasswordValueProfile;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getViewsReferences(root);

        updateUI();

        // Se hace click en el botón para mostrar la información de la App
        ibAppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileListener.onInfoButtonPressed();
            }
        });

        // Se hace click en el botón para Cerrar sesión
        bLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logOut(); }
        });

        // Se hace click en el botón para Eliminar cuenta
        bDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {profileListener.onDeleteAccountButtonPressed();}
        });

        return root;
    }

    /**
     * Método para obtener las referencias a cada una de las vistas que forman parte de la UI del fragmento.
     */
    private void getViewsReferences(View view) {
        ibAppInfo = view.findViewById(R.id.ibAppInfo);
        bLogOut = view.findViewById(R.id.bLogOut);
        bDeleteAccount = view.findViewById(R.id.bDeleteAccount);
        tvUsernameValueProfile = view.findViewById(R.id.tvUsernameValueProfile);
        tvEmailValueProfile = view.findViewById(R.id.tvEmailValueProfile);
        tvPasswordValueProfile = view.findViewById(R.id.tvPasswordValueProfile);
    }

    /**
     * Resetea las preferencias compartidas para eliminar la referencia al usuario y llama al método de la interfaz ProfileListener
     * definido para controlar el comportamiento del botón de cierre de sesión desde HomeActivity.
     */
    private void logOut() {
        // En caso de que se cierre sesión, la información viva del usuario debe reiniciarse a la espera de la nueva cuenta que sea loggeada
        UserFilmsData userFilmsData = UserFilmsData.getInstance();
        userFilmsData.userPendingFilms.clear();
        userFilmsData.userFavoriteFilms.clear();
        userFilmsData.userRatedFilms.clear();
        loginPreferences = getActivity().getSharedPreferences(getActivity().getPackageName()+"_preferences", Context.MODE_PRIVATE);
        removeUserPreference(loginPreferences);
        profileListener.onLogoutButtonPressed();
    }

    /**
     * Elimina la preferencia empleada para referenciar el nombre de usuario del que se mantiene la sesión.
     */
    private void removeUserPreference(SharedPreferences loginPreferences){
        SharedPreferences.Editor editPreferences = loginPreferences.edit();
        editPreferences.remove("USERNAME");
        editPreferences.apply();
    }

    private void updateUI(){
        loginPreferences = getActivity().getSharedPreferences(getActivity().getPackageName()+"_preferences", Context.MODE_PRIVATE);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                FilmsDatabase db = FilmsDatabase.getInstance(getContext());
                User user = db.userDAO().getUser(loginPreferences.getString("USERNAME", ""));
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvUsernameValueProfile.setText(user.getUsername());
                        tvEmailValueProfile.setText(user.getEmail());
                        tvPasswordValueProfile.setText(user.getPassword());
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            profileListener = (ProfileListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context + " must implement InfoButtonListener");
        }
    }

    /**
     * Interfaz para comunicar el fragmento con su actividad (HomeActivity)
     */
    public interface ProfileListener{
        void onInfoButtonPressed();
        void onLogoutButtonPressed();
        void onDeleteAccountButtonPressed();
    }
}
