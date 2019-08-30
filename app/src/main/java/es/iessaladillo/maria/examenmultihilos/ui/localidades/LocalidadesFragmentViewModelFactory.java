package es.iessaladillo.maria.examenmultihilos.ui.localidades;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.iessaladillo.maria.examenmultihilos.data.local.Repository;

public class LocalidadesFragmentViewModelFactory implements ViewModelProvider.Factory{

    private final Application application;
    private final Repository repository;

    LocalidadesFragmentViewModelFactory(@NonNull Application application, @NonNull Repository repository) {
        this.application = application;
        this.repository = repository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LocalidadesFragmentViewModel.class)) {
            return (T) new LocalidadesFragmentViewModel(application, repository);
        } else {
            throw new IllegalArgumentException("Wrong viewModel class");
        }
    }
}
