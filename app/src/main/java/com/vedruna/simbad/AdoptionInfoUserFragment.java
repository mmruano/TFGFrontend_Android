package com.vedruna.simbad;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vedruna.simbad.API.Constants.Constans;
import com.vedruna.simbad.API.DTO.AdoptionDTO;

public class AdoptionInfoUserFragment extends Fragment {

    public AdoptionInfoUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adoption_info_user, container, false);
        TextView adoptionAnimalNameInfoUser = view.findViewById(R.id.adoptionAnimalNameInfoUser);
        TextView adoptionAnimalProvinceInfoUser = view.findViewById(R.id.adoptionAnimalProvinceInfoUser);
        TextView adoptionAnimalGenderInfoUser = view.findViewById(R.id.adoptionAnimalGenderInfoUser);
        TextView adoptionAnimalSizeInfoUser = view.findViewById(R.id.adoptionAnimalSizeInfoUser);
        TextView adoptionAgeInfoUser = view.findViewById(R.id.adoptionAgeInfoUser);
        TextView adoptionAnimalBirtdateInfoUser = view.findViewById(R.id.adoptionAnimalBirtdateInfoUser);
        TextView adoptionAnimalEnergyLevelInfoUser = view.findViewById(R.id.adoptionAnimalEnergyLevelInfoUser);
        TextView adoptionAnimalSpeciesInfoUser = view.findViewById(R.id.adoptionAnimalSpeciesInfoUser);
        TextView adoptionAntryDateInfoUser = view.findViewById(R.id.adoptionAntryDateInfoUser);
        TextView adoptionAnimalHealthInfoUser = view.findViewById(R.id.adoptionAnimalHealthInfoUser);
        TextView adoptionAnimalDescriptionInfoUser = view.findViewById(R.id.adoptionAnimalDescriptionInfoUser);
        TextView adoptionAnimalInfoRefugeNameUser = view.findViewById(R.id.adoptionAnimalInfoRefugeNameUser);
        TextView adoptionAnimalInfoRefugeStreetUser = view.findViewById(R.id.adoptionAnimalInfoRefugeStreetUser);
        TextView adoptionAnimalInfoRefugeEmailUser = view.findViewById(R.id.adoptionAnimalInfoRefugeEmailUser);
        TextView adoptionAnimalInfoRefugeProvinceUser = view.findViewById(R.id.adoptionAnimalInfoRefugeProvinceUser);
        TextView adoptionAnfoRefugePhoneUser = view.findViewById(R.id.adoptionAnfoRefugePhoneUser);
        TextView adoptionAnimalInfoRefugePostCodeUser = view.findViewById(R.id.adoptionAnimalInfoRefugePostCodeUser);
        ImageView adoptionAnimalImageView = view.findViewById(R.id.adoptionAnimalInfoImageViewUser);
        Button goBackAdoptionInfoUserButton = view.findViewById(R.id.goBackAdoptionInfoUserButton);

        goBackAdoptionInfoUserButton.setOnClickListener( v -> goBackAdoptionInfoUser());

        // Retrieve data from bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            AdoptionDTO adoptionDTO = (AdoptionDTO) bundle.getSerializable("adoptionDTO");
            if (adoptionDTO != null) {
                adoptionAnimalNameInfoUser.setText(adoptionDTO.getUserName());
                adoptionAnimalProvinceInfoUser.setText(adoptionDTO.getProvince());
                adoptionAnimalGenderInfoUser.setText(adoptionDTO.getAnimalGender());
                adoptionAnimalSizeInfoUser.setText(adoptionDTO.getAnimalSize());
                adoptionAgeInfoUser.setText(adoptionDTO.getAnimalAge());
                adoptionAnimalBirtdateInfoUser.setText(adoptionDTO.getAnimalBirthDate());
                adoptionAnimalEnergyLevelInfoUser.setText(adoptionDTO.getAnimalEnergyLevel());
                adoptionAnimalSpeciesInfoUser.setText(adoptionDTO.getAnimalSpecies());
                adoptionAntryDateInfoUser.setText(adoptionDTO.getAnimalEntryDate());
                adoptionAnimalHealthInfoUser.setText(adoptionDTO.getAnimalHealth());
                adoptionAnimalDescriptionInfoUser.setText(adoptionDTO.getAnimalDescription());
                adoptionAnimalInfoRefugeNameUser.setText(adoptionDTO.getAnimalRefugeName());
                adoptionAnimalInfoRefugeStreetUser.setText(adoptionDTO.getAnimalRefugeStreet());
                adoptionAnimalInfoRefugeEmailUser.setText(adoptionDTO.getAnimalRefugeEmail());
                adoptionAnimalInfoRefugeProvinceUser.setText(adoptionDTO.getAnimalRefugeProvince());
                adoptionAnfoRefugePhoneUser.setText(adoptionDTO.getAnimalRefugePhone());
                adoptionAnimalInfoRefugePostCodeUser.setText(adoptionDTO.getAnimalRefugeCodePost());
                Glide.with(this)
                        .load(Constans.BASE_URL + adoptionDTO.getAnimalPhoto()) // Replace "getImageUrl()" with the actual method to get the image URL from adoptionDTO
                        .placeholder(R.drawable.animal_image_placeholder) // Placeholder image while loading (optional)
                        .error(R.drawable.error_image) // Error image if loading fails (optional)
                        .into(adoptionAnimalImageView);
            }
        }

        return view;
    }

    private void goBackAdoptionInfoUser() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
    }
}
