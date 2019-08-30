package es.iessaladillo.maria.examenmultihilos.ui.localidades;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Objects;

import es.iessaladillo.maria.examenmultihilos.R;
import es.iessaladillo.maria.examenmultihilos.data.local.AppDatabase;
import es.iessaladillo.maria.examenmultihilos.data.local.RepositoryImpl;
import es.iessaladillo.maria.examenmultihilos.databinding.FragmentLocalidadesBinding;
import es.iessaladillo.maria.examenmultihilos.ui.MainActivityViewModel;
import es.iessaladillo.maria.examenmultihilos.ui.info.InfoFragment;
import es.iessaladillo.maria.examenmultihilos.utils.FragmentUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalidadesFragment extends Fragment {

    private LocalidadesFragmentViewModel viewModel;
    MainActivityViewModel viewModelActivity;
    private LocalidadesFragmentAdapter listAdapter;
    private FragmentLocalidadesBinding b;
    EditText input;
    LinearLayout.LayoutParams lp;

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
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_localidades, container, false);
        return b.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppDatabase appDatabase = AppDatabase.getInstance(requireContext().getApplicationContext());
        viewModel = ViewModelProviders.of(this, new LocalidadesFragmentViewModelFactory(requireActivity().getApplication(),
                new RepositoryImpl(appDatabase.localidadDao()))).get(LocalidadesFragmentViewModel.class);
        viewModelActivity = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        observeCompanies();
        setupViews();
    }

    private void setupViews() {
        b.fabBuscar.setOnClickListener(l -> showDialogSearch());
        //Creamos el edittext para introducir la localidad en el dialogFragment
        input = new EditText(requireContext());
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        setupRecyclerView();
    }

    private void navigateToInfoFragment() {
        //Comprobamos que el campo no está vacío
        if(checkField(Objects.requireNonNull(input.getText()).toString())){
            //Navegamos hacia InfoFragment
            FragmentUtils.replaceFragmentAddToBackstack(requireActivity().getSupportFragmentManager(),
                    R.id.flContent, InfoFragment.newInstance(),
                    InfoFragment.class.getSimpleName(), InfoFragment.class.getSimpleName(),
                    FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
    }

    private void observeCompanies() {
        viewModel.getLocalidades().observe(this, localidades -> {
            listAdapter.submitList(localidades);
            b.lblEmptyLocalidades.setVisibility(localidades.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void setupRecyclerView() {
        listAdapter = new LocalidadesFragmentAdapter();
        b.lstLocalidades.setHasFixedSize(true);
        b.lstLocalidades.setLayoutManager(new GridLayoutManager(getActivity(),
                getResources().getInteger(R.integer.lst_columns)));
        b.lstLocalidades.addItemDecoration(new DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL));
        b.lstLocalidades.setItemAnimator(new DefaultItemAnimator());
        b.lstLocalidades.setAdapter(listAdapter);
    }

    private void showDialogSearch(){
        //Creamos el edittext para poder introducir la localidad
        input.setLayoutParams(lp);
        //Construimos el alertDialog para visualizarlo
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Búsqueda");
        builder.setMessage("Introduce una localidad para buscar por favor");
        builder.setCancelable(true);
        builder.setView(input);
        builder.setNegativeButton("Cancelar", ((dialog, which) -> dialog.cancel()));
        builder.setPositiveButton("Buscar", (dialog, id) -> navigateToInfoFragment());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean checkField(String txt) {
        if(!TextUtils.isEmpty(txt)){
            viewModelActivity.setLocalidad(txt);
            return true;
        }else{
            input.setError(getString(R.string.no_string));
            return false;
        }

    }
}
