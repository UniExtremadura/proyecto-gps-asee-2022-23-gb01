package es.unex.asee_proyectoprueba.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.unex.asee_proyectoprueba.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileListener profileListener;

    // Campo para el acceso a las preferencias de usuario compartidas
    private SharedPreferences loginPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
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
        void onDeleteAccountButtonPressed();
    }
}
