package com.vedruna.simbad.ListAdapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AdoptionDTO;
import com.vedruna.simbad.AdoptionInfoUserFragment;
import com.vedruna.simbad.R;

import java.util.ArrayList;

public class ListAdoptionsUserAdapter extends ArrayAdapter<AdoptionDTO> {
    private Context mContext;
    private ArrayList<AdoptionDTO> mDataArrayList;

    public ListAdoptionsUserAdapter(@NonNull Context context, ArrayList<AdoptionDTO> dataArrayList) {
        super(context, R.layout.adoption_list_user, dataArrayList);
        mContext = context;
        mDataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        AdoptionDTO listAdoptionDTO = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adoption_list_user, parent, false);
        }

        ImageView adoptionAnimalListPhotoUser = view.findViewById(R.id.adoptionAnimalListPhotoUser);
        TextView adoptionAnimalListNameUser = view.findViewById(R.id.adoptionAnimalListNameUser);
        TextView adoptionAnimalListStatusAdoptionUser = view.findViewById(R.id.adoptionAnimalListStatusAdoptionUser);

        if (listAdoptionDTO != null) {
            // Load image using Glide library
            Glide.with(mContext)
                    .load(Constans.BASE_URL + listAdoptionDTO.getAnimalPhoto())
                    .placeholder(R.drawable.animal_image_placeholder)
                    .error(R.drawable.error_image)
                    .into(adoptionAnimalListPhotoUser);

            // Set name and adoption status
            adoptionAnimalListNameUser.setText(listAdoptionDTO.getUserName());
            adoptionAnimalListStatusAdoptionUser.setText(listAdoptionDTO.getStatusAdoption());
        }

        // Set click listener to navigate to AdoptionInfoUserFragment
        view.setOnClickListener(v -> {
            // Create a Bundle to pass data to the new fragment
            Bundle bundle = new Bundle();
            bundle.putSerializable("adoptionDTO", listAdoptionDTO); // You can use another method according to the data type of AdoptionDTO

            // Create an instance of AdoptionInfoUserFragment and set arguments
            AdoptionInfoUserFragment fragment = new AdoptionInfoUserFragment();
            fragment.setArguments(bundle);

            // Get the FragmentManager from the activity
            FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();

            // Start a fragment transaction
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            // Replace the current fragment with the new fragment
            transaction.replace(R.id.fragment_container, fragment);

            // Add the transaction to the back stack
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        });

        return view;
    }
}
