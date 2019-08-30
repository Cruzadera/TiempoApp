package es.iessaladillo.maria.examenmultihilos.ui.main;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.Objects;

import es.iessaladillo.maria.examenmultihilos.R;
import es.iessaladillo.maria.examenmultihilos.databinding.FragmentMainBinding;
import es.iessaladillo.maria.examenmultihilos.ui.MainActivityViewModel;
import es.iessaladillo.maria.examenmultihilos.ui.info.InfoFragment;
import es.iessaladillo.maria.examenmultihilos.ui.localidades.LocalidadesFragment;
import es.iessaladillo.maria.examenmultihilos.utils.FragmentUtils;


public class MainFragment extends Fragment {

    FragmentMainBinding b;
    MainActivityViewModel viewModel;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        setupViews();
    }

    private void setupViews() {
        b.btnLocalidades.setOnClickListener(l -> navigateToLocalidadesFragment());
        b.btnBuscar.setOnClickListener(l -> navigateToInfoFragment());
        b.txtBuscarLocalidad.setOnEditorActionListener(this::onEditorAction);
    }

    private void navigateToInfoFragment() {
        //Comprobamos que el campo no está vacío
        if(checkField(Objects.requireNonNull(b.txtBuscarLocalidad.getText()).toString())){
            //Navegamos hacia el fragmento de info de la localidad
            FragmentUtils.replaceFragmentAddToBackstack(requireActivity().getSupportFragmentManager(),
                    R.id.flContent, InfoFragment.newInstance(),
                    InfoFragment.class.getSimpleName(), InfoFragment.class.getSimpleName(),
                    FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
    }

    private void navigateToLocalidadesFragment() {
        FragmentUtils.replaceFragmentAddToBackstack(requireActivity().getSupportFragmentManager(),
                R.id.flContent, LocalidadesFragment.newInstance(),
                LocalidadesFragment.class.getSimpleName(), LocalidadesFragment.class.getSimpleName(),
                FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    private boolean checkField(String txt) {
        if(!TextUtils.isEmpty(txt)){
            viewModel.setLocalidad(txt);
            return true;
        }else{
            b.txtBuscarLocalidad.setError(getString(R.string.no_string));
            return false;
        }

    }

    private boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            navigateToInfoFragment();
            return true;
        }
        return false;
    }
}
