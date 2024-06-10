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
import com.vedruna.simbad.API.DTO.AnimalDTO;
import com.vedruna.simbad.AnimalInfoUserFragment;
import com.vedruna.simbad.R;

import java.util.ArrayList;

public class ListAnimalsUserAdapter extends ArrayAdapter<AnimalDTO> {
    private Context mContext;
    private ArrayList<AnimalDTO> mDataArrayList;

    public ListAnimalsUserAdapter(Context context, ArrayList<AnimalDTO> dataArrayList) {
        super(context, R.layout.animal_list_user, dataArrayList);
        mContext = context;
        mDataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        AnimalDTO listAnimalDTO = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.animal_list_user, parent, false);
        }

        ImageView animalListPhotoUser = view.findViewById(R.id.animalListPhotoUser);
        TextView animalListNameUser = view.findViewById(R.id.animalListNameUser);
        TextView animalListProvinceUser = view.findViewById(R.id.animalListProvinceUser);

        if (listAnimalDTO != null) {
            // Load image using Glide library
            Glide.with(mContext)
                    .load(Constans.BASE_URL + listAnimalDTO.getPhoto())
                    .placeholder(R.drawable.animal_image_placeholder)
                    .error(R.drawable.error_image)
                    .into(animalListPhotoUser);

            // Set name and province
            animalListNameUser.setText(listAnimalDTO.getName());
            animalListProvinceUser.setText(listAnimalDTO.getProvince());

            // Set click listener
            view.setOnClickListener(v -> navigateToAnimalInfoListUserFragment(listAnimalDTO));
        }

        return view;
    }

    private void navigateToAnimalInfoListUserFragment(AnimalDTO animalDTO) {
        // Crear un Bundle para pasar datos al nuevo fragmento
        Bundle bundle = new Bundle();
        bundle.putSerializable("animalDTO", animalDTO); // Puedes usar otro método según el tipo de datos de AnimalDTO
        bundle.putLong("animalId", animalDTO.getId()); // Aquí agregamos el ID del animal al Bundle

        // Crear una instancia del fragmento de información del animal para usuarios y establecer los argumentos
        AnimalInfoUserFragment fragment = new AnimalInfoUserFragment();
        fragment.setArguments(bundle);

        // Obtener el FragmentManager de la actividad
        FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();

        // Iniciar una transacción de fragmentos
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Reemplazar el fragmento actual con el nuevo fragmento
        transaction.replace(R.id.fragment_container, fragment);

        // Agregar la transacción a la pila de retroceso
        transaction.addToBackStack(null);

        // Confirmar la transacción
        transaction.commit();
    }
}
