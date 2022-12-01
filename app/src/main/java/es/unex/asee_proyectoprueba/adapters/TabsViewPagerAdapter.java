package es.unex.asee_proyectoprueba.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import es.unex.asee_proyectoprueba.fragments.ItemDetailInfoFragment;
import es.unex.asee_proyectoprueba.fragments.ItemDetailSocialFragment;

public class TabsViewPagerAdapter extends FragmentStateAdapter {

    public TabsViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new ItemDetailInfoFragment();
        else return new ItemDetailSocialFragment();
    }

    @Override
    public int getItemCount() {
        return 2; // Siempre se van a tener 2 tabs en el detalle de una obra
    }
}
