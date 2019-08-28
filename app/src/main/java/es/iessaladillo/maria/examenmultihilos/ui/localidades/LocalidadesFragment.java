package es.iessaladillo.maria.examenmultihilos.ui.localidades;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.iessaladillo.maria.examenmultihilos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalidadesFragment extends Fragment {


    public LocalidadesFragment() {
        // Required empty public constructor
    }

    public static LocalidadesFragment newInstance() {
        Bundle args = new Bundle();
        LocalidadesFragment fragment = new LocalidadesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_localidades, container, false);
    }

}
